package org.rubik.sandbox.guava;

import junit.framework.TestCase;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * Hashing测试用例.
 */
public class HashingTest extends TestCase {

	@Test
	public void testMd5() {
		String params = "id=4028e4484d74f981014d7933e7aa004cinfoid=e5da0ead62194e8cbe6e77cb905bbdf4token=c00e96982f022fdf9d2f5c052c26f41busername=18585888324vote=1";
		String signature = Hashing.md5().hashString(params, Charsets.UTF_8).toString();
		assertEquals("9f8e9d55eeb64309ff9316b1261379a9", signature);

		String string = "abc";
		String md5 = Hashing.md5().hashString(string, Charsets.UTF_8).toString();
		assertEquals("900150983cd24fb0d6963f7d28e17f72", md5);
	}

	@Test
	public void testSha512() {
		String s = "12345";

		String sha1 = Hashing.sha1().hashString(s, Charsets.UTF_8).toString();
		assertEquals("8cb2237d0679ca88db6464eac60da96345513964", sha1);

		String sha256 = Hashing.sha256().hashString(s, Charsets.UTF_8).toString();
		assertEquals("5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5", sha256);

		String sha512 = Hashing.sha512().hashString(s, Charsets.UTF_8).toString();
		assertEquals("3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79", sha512);
	}

	@Test
	public void testCrc32() {
		int value = Hashing.crc32().hashString("BYS:1970010108:169971", Charsets.UTF_8).asInt();
		assertEquals(1530644774, value);
	}
}
