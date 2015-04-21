package org.rubik.sandbox.jdk8;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class ZipHelperTest {
	
	@Test
	public void doTest() throws IOException {
		String target= "/Users/xiajinxin/ABC.zip";
		ZipHelper helper = ZipHelper.createHelper(null);
		helper.setTargetFile(new File(target));
		helper.zipPath("/Users/xiajinxin/ABC");
	}
	
}
