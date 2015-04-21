package org.rubik.sandbox.guava;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

/**
 * Splitter测试用例.
 */
public class SplitterTest extends TestCase {

	@Test
	public void testNormal() {
		Splitter splitter = Splitter.on('|');
		List<String> personList = splitter.splitToList("张三|李四|王五");
		assertEquals(3, personList.size());
		assertEquals("李四", personList.get(1));
	}

	@Test
	public void testFixedLength() {
		List<String> strings = Splitter.fixedLength(2).splitToList("ABCDEFGH");
		assertEquals(4, strings.size());
		assertEquals("EF", strings.get(2));
	}

	@Test
	public void testCheckNull() {
		Splitter splitter = Splitter.on("@@");
		List<String> novelsWithNull = splitter.splitToList("倚天屠龙记@@天龙八部@@神雕侠侣@@@@@鹿鼎记@");
		assertEquals(5, novelsWithNull.size());
		assertEquals("@鹿鼎记@", novelsWithNull.get(4));

		List<String> novels = splitter.omitEmptyStrings().splitToList("倚天屠龙记@@天龙八部@@神雕侠侣@@@@@鹿鼎记@");
		assertEquals(4, novels.size());
		assertEquals("@鹿鼎记@", novels.get(3));
	}

	@Test
	public void testCharMatcher() {
		String mac = "28:cf:e9:19:a2:a7";
		String result = CharMatcher.JAVA_LETTER_OR_DIGIT.retainFrom(mac);
		assertEquals("28cfe919a2a7", result);
		
		Splitter splitter = Splitter.on(CharMatcher.anyOf(";,."));
		String str = "ab,cd,ef.gh;ij,km:n";
		List<String> strings = splitter.splitToList(str);
		assertEquals(6, strings.size());
		assertEquals("km:n", strings.get(5));

		List<String> limitStrings = splitter.limit(3).splitToList(str);
		assertEquals(3, limitStrings.size());
		assertEquals("ef.gh;ij,km:n", limitStrings.get(2));
	}

	@Test
	public void testMapSplitter() {
		Splitter splitter = Splitter.on('#');
		Map<String, String> map = splitter.withKeyValueSeparator("@").split("金庸@天龙八部,神雕侠侣#古龙@白玉老虎,多情剑客无情剑");
		assertEquals(2, map.size());
		assertEquals("白玉老虎,多情剑客无情剑", map.get("古龙"));
	}
}
