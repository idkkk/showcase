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
		String string = "abc";
		String md5 = Hashing.md5().newHasher().putString(string, Charsets.UTF_8).hash().toString();
		assertEquals("900150983cd24fb0d6963f7d28e17f72", md5);
	}

	@Test
	public void testSha512() {
		String s = "12345";

		String sha1 = Hashing.sha1().newHasher().putString(s, Charsets.UTF_8).hash().toString();
		assertEquals("8cb2237d0679ca88db6464eac60da96345513964", sha1);

		String sha256 = Hashing.sha256().newHasher().putString(s, Charsets.UTF_8).hash().toString();
		assertEquals("5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5", sha256);

		String sha512 = Hashing.sha512().newHasher().putString(s, Charsets.UTF_8).hash().toString();
		assertEquals("3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79", sha512);
	}
}
