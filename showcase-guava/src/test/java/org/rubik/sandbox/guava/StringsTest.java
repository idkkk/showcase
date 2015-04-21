package org.rubik.sandbox.guava;

import junit.framework.TestCase;

import org.junit.Test;

import com.google.common.base.Strings;

/**
 * Strings测试用例.
 */
public class StringsTest extends TestCase {

	@Test
	public void testCommonPrefix() {
		String commonPrefix = Strings.commonPrefix("Hello", "Hello world");
		assertEquals("Hello", commonPrefix);
	}

	@Test
	public void testCommonSuffix() {
		String commonSuffix = Strings.commonSuffix("世界您好", "中国您好");
		assertEquals("您好", commonSuffix);
	}

	@Test
	public void testPadStart() {
		String padStartWithZero = Strings.padStart("149", 5, '0');
		assertEquals("00149", padStartWithZero);

		String str = Strings.padStart("149361", 5, '0');
		assertEquals("149361", str);

		String padStartWithSpace = Strings.padStart("DEBUG", 5, ' ');
		assertEquals("DEBUG", padStartWithSpace);
	}

	@Test
	public void testPadEnd() {
		String padEndWithZero = Strings.padEnd("124.", 5, '0');
		assertEquals("124.0", padEndWithZero);

		String padEndWithSpace = Strings.padEnd("WARN", 5, ' ');
		assertEquals("WARN ", padEndWithSpace);
	}

	@Test
	public void testRepeat() {
		String repeatString = Strings.repeat("Abc", 2);
		assertEquals("AbcAbc", repeatString);
	}

	@Test
	public void testIsNullOrEmpty() {
		assertTrue(Strings.isNullOrEmpty(null));
		assertTrue(Strings.isNullOrEmpty(""));
		assertFalse(Strings.isNullOrEmpty(" "));
	}

	@Test
	public void testNullToEmpty() {
		assertEquals("", Strings.nullToEmpty(null));
	}

	@Test
	public void testEmptyToNull() {
		assertNull(Strings.emptyToNull(""));
	}
}
