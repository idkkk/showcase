package org.rubik.sandbox.guava;

import junit.framework.TestCase;

import org.junit.Test;

import com.google.common.base.CharMatcher;

/**
 * CharMatcher测试用例.
 */
public class CharMatcherTest extends TestCase {

	@Test
	public void testCollapseFrom() {
		String before = "Hello		Jack, How 	do you    do?";
		String after = CharMatcher.WHITESPACE.collapseFrom(before, ' ');
		assertEquals("Hello Jack, How do you do?", after);
	}

	@Test
	public void testTrimFrom() {
		String before = "   Hello   	world  ";
		String afterTrimLeading = CharMatcher.WHITESPACE.trimLeadingFrom(before);
		assertEquals("Hello   	world  ", afterTrimLeading);

		String afterTrimTrailing = CharMatcher.WHITESPACE.trimTrailingFrom(afterTrimLeading);
		assertEquals("Hello   	world", afterTrimTrailing);

		String after = CharMatcher.WHITESPACE.trimFrom(before);
		assertEquals("Hello   	world", after);
	}

	@Test
	public void testTrimAndCollapseFrom() {
		String before = "   Hello   	world";
		String after = CharMatcher.WHITESPACE.trimAndCollapseFrom(before, ' ');
		assertEquals("Hello world", after);
	}

	@Test
	public void testRetainFrom() {
		String retainString = CharMatcher.JAVA_DIGIT.retainFrom("abc123xyz890kmn");
		assertEquals("123890", retainString);
	}

	@Test
	public void testRemoveFrom() {
		String removeLetter = CharMatcher.JAVA_LETTER.removeFrom("abc123xyz890kmn");
		assertEquals("123890", removeLetter);
	}

	@Test
	public void testReplaceFrom() {
		String replaceLowerCase = CharMatcher.JAVA_LOWER_CASE.replaceFrom("Abc123XyZ456OPQ890", "#");
		assertEquals("A##123X#Z456OPQ890", replaceLowerCase);

		String replaceZero = CharMatcher.is('0').replaceFrom("10020", '@');
		assertEquals("1@@2@", replaceZero);
	}

	@Test
	public void testCountIn() {
		int count = CharMatcher.is('a').countIn("aaabbccabc");
		assertEquals(4, count);
	}

	@Test
	public void testAnyOf() {
		String removeString = CharMatcher.anyOf("0123456789").removeFrom("A001289-B12300-G001");
		assertEquals("A-B-G", removeString);
	}

	@Test
	public void testInRange() {
		String removeString = CharMatcher.inRange('0', '9').removeFrom("A001289-B12300-G001");
		assertEquals("A-B-G", removeString);

		String retainString = CharMatcher.inRange('a', 'z').replaceFrom("Hello World!", '*');
		assertEquals("H**** W****!", retainString);
	}
}