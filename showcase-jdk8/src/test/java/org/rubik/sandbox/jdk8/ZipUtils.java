package org.rubik.sandbox.jdk8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip utility class
 */
public class ZipUtils {
	private static final String EXTENSION_NAME = ".zip";

	private String packFilePath;
	private String packDirectoryPath;
	
	public void packFile() throws IOException {
		String outputFile = packFilePath + EXTENSION_NAME;
		
		FileOutputStream fos = new FileOutputStream(outputFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		ZipEntry ze= new ZipEntry(new File(packFilePath).getName());
		zos.putNextEntry(ze);

		FileInputStream fis = new FileInputStream(packFilePath);

		byte[] bytesRead = new byte[512];
		int bytesNum;
		while ((bytesNum = fis.read(bytesRead)) > 0) {
			zos.write(bytesRead, 0, bytesNum);
		}
		
		fis.close();
		zos.closeEntry();
		zos.close();
		fos.close();
	}
	
	public void packDirectory() throws IOException {
		String outputFile = packDirectoryPath + EXTENSION_NAME;
		FileOutputStream fos = new FileOutputStream(outputFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		packCurrentDirectoryContents(packDirectoryPath, zos);
		zos.closeEntry();
		zos.close();
		fos.close();
	}
	
	private void packCurrentDirectoryContents(String directoryPath, ZipOutputStream zos) throws IOException {
		for (String dirElement: new File(directoryPath).list()) {
			String dirElementPath = directoryPath + File.separator + dirElement;
			if (new File(dirElementPath).isDirectory()) {
				packCurrentDirectoryContents(dirElementPath, zos);
			} else {
				ZipEntry ze= new ZipEntry(dirElementPath.replaceAll(packDirectoryPath + File.separator, ""));
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(dirElementPath);
				byte[] bytesRead = new byte[512];
				int bytesNum;
				while ((bytesNum = fis.read(bytesRead)) > 0) {
					zos.write(bytesRead, 0, bytesNum);
				}
				fis.close();
			}
		}
	}
	
	// Setters
	public void setPackFilePath(String packFilePath) {
		this.packFilePath = packFilePath;
	}

	public void setPackDirectoryPath(String packDirectoryPath) {
		this.packDirectoryPath = packDirectoryPath;
	}

	public static void main(String[] args) {
		try {
			ZipUtils zipPack = new ZipUtils();
			
			System.out.println(System.getProperty("file.encoding"));
			zipPack.setPackDirectoryPath("/Users/xiajinxin/ABC");
			zipPack.packDirectory();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}