package org.rubik.sandbox.jdk8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.google.common.base.Strings;


class KVStore<K, V>{
	private K key;
	private V value;
	public KVStore(K key, V value){
		this.key = key;
		this.value = value;
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
}

/**
 * zip压缩与解压助手
 * @author chaijunkun
 *
 */
public class ZipHelper {
	
	/**
	 * 保护压缩文件的加解密算法
	 */
	private static final String PROTECT_ALGORITHM= "AES";

	/**
	 * 保护压缩文件的加解块大小(普通JDK支持到128位, 安装替换JCE后可支持到256位)
	 * @see http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html
	 * @see http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html
	 */
	private static final int PROTECT_BIT_LENGTH= 128;

	/**
	 * 加密用的密钥(模式不同)
	 */
	private Cipher encryptCipher;

	/**
	 * 解密用的密钥(模式不同)
	 */
	private Cipher decryptCipher;

	/**
	 * 加密成的目标文件或者将要被解密的文件
	 */
	private File target;

	/**
	 * 存储压缩路径和被压缩文件对应关系的容器
	 */
	private List<KVStore<String, File>> container = new LinkedList<KVStore<String, File>>();

	/**
	 * 创建压缩输出流
	 * @return
	 * @throws FileNotFoundException 指定的目标文件异常 不是常规文件
	 */
	private ZipArchiveOutputStream createZipOut() throws FileNotFoundException{
		ZipArchiveOutputStream out = null;
		if (Objects.nonNull(this.encryptCipher)){
			CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(target), this.encryptCipher);
			//通过流管道串联方式实现压缩-加密-写入
			out= new ZipArchiveOutputStream(cos);
		}else{
			out= new ZipArchiveOutputStream(new FileOutputStream(target));
		}
		
		out.setUseZip64(Zip64Mode.Always);
		return out;
	}

	/**
	 * 创建压缩输入流
	 * @return
	 * @throws FileNotFoundException 指定的目标文件异常 不是常规文件
	 */
	private ZipArchiveInputStream createZipIn() throws FileNotFoundException{
		ZipArchiveInputStream in = null;
		if (Objects.nonNull(this.decryptCipher)){
			CipherInputStream cis= new CipherInputStream(new FileInputStream(target), this.decryptCipher);
			//通过流管道串联方式实现读取-解密-解压
			in = new ZipArchiveInputStream(cis);
		}else{
			in = new ZipArchiveInputStream(new FileInputStream(target));
		}
		return in;
	}

	/**
	 * 构造函数
	 * @param password 用于压缩或者解压的密码
	 */
	private ZipHelper(String password){
		if (!Strings.isNullOrEmpty(password)){
			try{
				//加密用相关组件加载
				KeyGenerator gen= KeyGenerator.getInstance(PROTECT_ALGORITHM);
				gen.init(PROTECT_BIT_LENGTH, new SecureRandom(password.getBytes()));
				SecretKey key= gen.generateKey();
				byte[] keyEnc= key.getEncoded();
				SecretKeySpec spec= new SecretKeySpec(keyEnc, PROTECT_ALGORITHM);
				encryptCipher= Cipher.getInstance(PROTECT_ALGORITHM);
				encryptCipher.init(Cipher.ENCRYPT_MODE, spec);
				decryptCipher= Cipher.getInstance(PROTECT_ALGORITHM);
				decryptCipher.init(Cipher.DECRYPT_MODE, spec);
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 创建不需要密码的zip助手
	 * @return
	 */
	public static ZipHelper createHelper(){
		return ZipHelper.createHelper(null);
	}

	/**
	 * 创建需要密码的zip助手
	 * @param password 压缩或解压缩密码 密码为空时不加密
	 * @return
	 */
	public static ZipHelper createHelper(String password){
		return new ZipHelper(password);
	}

	/**
	 * 添加将被压缩的文件
	 * @param entry
	 * @param file
	 * @return
	 */
	public ZipHelper addFile(String entry, File file) {
		container.add(new KVStore<String, File>(entry, file));
		return this;
	}

	/**
	 * 设置目标文件
	 * @param target
	 * @return
	 */
	public ZipHelper setTargetFile(File target){
		this.target = target;
		return this;
	}

	/**
	 * 开始压缩
	 * @throws IOException 
	 */
	public void zip() throws IOException{
		if (Objects.isNull(target)){
			throw new IOException("target file has not been set yet");
		}
		if (Objects.nonNull(container)){
			ArchiveOutputStream out = null;
			try{
				out = this.createZipOut();
				for (KVStore<String, File> store : container) {
					ArchiveEntry archiveEntry = new ZipArchiveEntry(store.getKey());
					out.putArchiveEntry(archiveEntry);
					if (Objects.nonNull(store.getValue())){
						FileUtils.copyFile(store.getValue(), out);
						out.flush();
					}
					out.closeArchiveEntry();
				}
			}finally{
				IOUtils.closeQuietly(out);
			}
		}

	}

	/**
	 * 开始解压
	 * @param path 解压到指定目录下
	 * @throws IOException
	 */
	public void unzip(String path) throws IOException{
		if (Objects.isNull(target)){
			throw new IOException("target file has not been set yet");
		}
		ArchiveInputStream in = null;
		try{
			in = this.createZipIn();
			ArchiveEntry entry= null;
			while((entry=in.getNextEntry())!=null){
				String entryName= String.format("%s%s%s", path, File.separator, entry.getName());
				//当遍历到的入口为目录时 强制创建相同的目录
				if (entry.isDirectory()){
					File tempDir= new File(entryName);
					FileUtils.forceMkdir(tempDir);
				}else{//当遍历到的入口为文件时 解压文件
					File entryFile= new File(entryName);
					//判断是否存在父目录,不存在则强制创建,防止解压失败
					if (!entryFile.getParentFile().exists()){
						FileUtils.forceMkdir(entryFile.getParentFile());
					}
					FileUtils.copyInputStreamToFile(in,entryFile);
				}
			}
		}finally{
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * 批量按原样压缩某路径下的所有文件
	 * @param path 被压缩路径 不能以文件分隔符结尾
	 * @throws IOException
	 */
	public void zipPath(String path) throws IOException {
		//枚举所有文件和目录 包含空文件夹
		Iterator<File> iterator = FileUtils.iterateFilesAndDirs(new File(path), TrueFileFilter.INSTANCE, DirectoryFileFilter.DIRECTORY);
		int reservIdx = path.length() + File.separator.length();
		String entryName = null;
		while(iterator.hasNext()){
			File file = iterator.next();
			//使用getPath能保证获取的路径开头与输入的path一致,从而安全地根据path长度获取相对路径
			entryName = file.getPath();
			if (entryName.length()<=reservIdx){
				continue;
			}
			entryName = entryName.substring(reservIdx);
			//计算相对目录
			if (file.isDirectory()){
				this.addFile(entryName.concat(File.separator), null);
			}else{
				this.addFile(entryName, file);
			}
		}
		this.zip();
	}

}
