package org.rubik.sandbox.guava;

import junit.framework.TestCase;

import org.junit.Test;

import com.google.common.base.Charsets;

/**
 * Charsets测试用例.
 */
public class CharsetsTest extends TestCase {

	@Test
	public void testNormal() {
		byte[] bytes = "Guava学习".getBytes(Charsets.UTF_8);
		assertNotNull(bytes);
		assertEquals("Guava学习", new String(bytes));
	}
}
