package org.rubik.sandbox.fastdfs;

import java.io.File;
import java.io.IOException;

import org.rubik.sandbox.fastdfs.util.FastDfsUtils;

import com.google.common.io.Files;

public class App {

	private static final String FILE_NAME = "/Users/xiajinxin/Desktop/1.pic_hd.jpg";

	public static void main(String[] args) throws IOException {
		File file = new File(FILE_NAME);
		byte[] bytes = Files.toByteArray(file);
		String fileId = FastDfsUtils.uploadFileByName(bytes, FILE_NAME);
		System.out.println(fileId);
	}

}
