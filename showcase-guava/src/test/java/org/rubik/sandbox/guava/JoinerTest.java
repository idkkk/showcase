package org.rubik.sandbox.guava;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

/**
 * Joiner测试用例.
 */
public class JoinerTest extends TestCase {

	@Test
	public void testNormal() {
		Joiner joiner = Joiner.on(',');
		String persons = joiner.join("张三", "李四", "王五");  // 可变参数
		assertEquals("张三,李四,王五", persons);

		String[] langArray = new String[]{"Java", "PHP", "Javascript", "HTML5", "CSS3", "Scala", "Swift"};
		String languages = joiner.join(langArray);  // 数组
		assertEquals("Java,PHP,Javascript,HTML5,CSS3,Scala,Swift", languages);

		String[] fruitArray = new String[]{"苹果", "西瓜", "哈密瓜", "樱桃", "桔子"};
		List<String> fruitList = Arrays.asList(fruitArray);
		String fruits = joiner.join(fruitList);     // 集合
		assertEquals("苹果,西瓜,哈密瓜,樱桃,桔子", fruits);
	}

	@Test
	public void testCheckNull() {
		Joiner joiner = Joiner.on("##");
		String[] letterArray = new String[]{"A", "B", "C", "D", null, "", "E"};
		String letters = joiner.skipNulls().join(letterArray);
		assertEquals("A##B##C##D####E", letters);

		String lettersWithNull = joiner.useForNull("无值").join(letterArray);
		assertEquals("A##B##C##D##无值####E", lettersWithNull);
	}

	@Test
	public void testAppendTo() {
		Joiner joiner = Joiner.on(":");
		List<Integer> times = Arrays.asList(10,30);
		StringBuilder sb = new StringBuilder();
		sb.append("2014-06-30 ");
		sb = joiner.appendTo(sb, times);  // 任意追加对象
		assertEquals("2014-06-30 10:30", sb.toString());
	}

	@Test
	public void testMapJoiner() {
		Map<String, String> map = Maps.newHashMap();
		map.put("A", "001");
		map.put("B", "002");
		map.put("C", "003");
		String values = Joiner.on(",").withKeyValueSeparator("=>").join(map);
		assertEquals("A=>001,B=>002,C=>003", values);
	}
}
