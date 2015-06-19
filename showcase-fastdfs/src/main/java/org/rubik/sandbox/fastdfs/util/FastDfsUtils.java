/**
 * Copyright ©2008-2012 Lafaso.com All Rights Reserved. 
 */
package org.rubik.sandbox.fastdfs.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Throwables.getStackTraceAsString;
import static com.google.common.io.Files.getFileExtension;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.rubik.sandbox.fastdfs.exception.IllegalFilenameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>FastDfs工具类，提供文件常用操作封装。</p>
 * 
 * @author xiajinxin
 *
 */
public abstract class FastDfsUtils {
	
	/**
	 * 日志。
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FastDfsUtils.class);

	/**
	 * FastDFS配置文件名常量。
	 */
	private static final String FDFS_CLIENT_CONFIG = "fdfs_client.conf";

	/**
	 * CLASSPATH根。
	 */
	private static final String CLASSPATH_ROOT = "/";

	/**
	 * 只做一次初始化工作。
	 */
	static {
        init();
	}

	/**
	 * 根据文件全路径名，上传文件到fastDFS。
	 *
	 * @param content 文件内容。
	 * @param fileName 文件全路径名。
	 * @return String fastDFS返回的文件ID。
	 */
	public static String uploadFileByName(byte[] content, String fileName) {
		validateArgs(fileName);

		String fileId = "";

		String fileExtName = getFileExtension(fileName);
		if (isNullOrEmpty(fileExtName)) {
			LOGGER.error("The format of filename is illegal, filename: {}", fileName);
			throw new IllegalFilenameException(fileName);
		}

		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;
		try {
			trackerServer = tracker.getConnection();
			StorageClient1 client = new StorageClient1(trackerServer, storageServer);

			// 文件的元信息
			NameValuePair[] metaDatas = new NameValuePair[2];
			metaDatas[0] = new NameValuePair("fileName", fileName);
			metaDatas[1] = new NameValuePair("fileExtName", fileExtName);

			//上传
			fileId = client.upload_file1(content, fileExtName, metaDatas);
		} catch (IOException e) {
			LOGGER.error("Upload file occur exception, stackTrace:{}", getStackTraceAsString(e));
		} catch (MyException e) {
			LOGGER.error("Upload file occur exception, stackTrace:{}", getStackTraceAsString(e));
		} finally {
			try {
				trackerServer.close();
			} catch (IOException e) {
				LOGGER.error("Tracker server close occur exception, stackTrace:{}", getStackTraceAsString(e));
			}
		}

		return fileId;
	}

	/**
	 * 载入FastDFS配置文件进行初始化操作。
	 */
	private static void init() {
		try {
			String classPath = new File(FastDfsUtils.class.getResource(CLASSPATH_ROOT).getFile()).getCanonicalPath();  
			String configFilePath = classPath + File.separator + FDFS_CLIENT_CONFIG;
			ClientGlobal.init(configFilePath);
		} catch (FileNotFoundException fe) {
			LOGGER.error("Init fastDFS occur exception, stackTrace:{}", getStackTraceAsString(fe));
		} catch (IOException ioe) {
			LOGGER.error("Init fastDFS occur exception, stackTrace:{}", getStackTraceAsString(ioe));
		} catch (MyException me) {
			LOGGER.error("Init fastDFS occur exception, stackTrace:{}", getStackTraceAsString(me));
		}
	}

	/**
	 * 验证文件名是否合法。
	 *
	 * @param fileName 文件名。
	 */
	private static void validateArgs(String fileName) {
		boolean isInvalidFileName = isNullOrEmpty(fileName);
		checkArgument(!isInvalidFileName, "不正确的文件名[%s]!", fileName);
	}
}
