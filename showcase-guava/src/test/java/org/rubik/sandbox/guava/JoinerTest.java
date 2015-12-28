package org.rubik.sandbox.guava;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;

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

		Map<String, String> data = Maps.newTreeMap();
		data.put("username", "18585888324");
		data.put("token", "c00e96982f022fdf9d2f5c052c26f41b");
		data.put("infoid", "e5da0ead62194e8cbe6e77cb905bbdf4");
		data.put("id", "4028e4484d74f981014d7933e7aa004c");
		data.put("vote", "1");
		String result = Joiner.on("").withKeyValueSeparator("=").join(data);
		String signature = Hashing.md5().hashString(result, Charsets.UTF_8).toString();
		assertEquals("9f8e9d55eeb64309ff9316b1261379a9", signature);
	}

	@Test
	public void testParams() {
		Map<String, String> data = Maps.newLinkedHashMap();
		data.put("inputCharset", "1");
		data.put("pageUrl", "");
		data.put("bgUrl", "http://123.57.145.146:8080/showcase-payment/payment/callback");                      //反馈页面
		data.put("version", "v2.0");
		data.put("language", "1");
		data.put("signType", "4");
		data.put("merchantAcctId", "1001213884201");    // 商户号
		data.put("payerName", "");
		data.put("payerContactType", "1");
		data.put("payerContact", "2532987@qq.com");        // 邮件地址
		data.put("orderId", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));      // 订单号
		data.put("orderAmount", "1");    //订单金额
		data.put("orderTime", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));    // 下单时间
		data.put("productName", "苹果");        // 商品名称
		data.put("productNum", "5");           // 商品数量
		data.put("productId", "55558888");     // 商品编号
		data.put("productDesc", "");           // 商品描述
		data.put("payType", "00");

		String result = Joiner.on("&").withKeyValueSeparator("=").join(Maps.filterValues(data, new Predicate<String>() {
            public boolean apply(String value) {
	            return !Strings.isNullOrEmpty(value);
            }
		}));
		assertEquals("merchantAcctId=1001213884201&productId=55558888&orderId=20150727150240&payerContact=2532987@qq.com&language=1&payerContactType=1&productNum=5&version=v2.0&productName=苹果&orderAmount=1&orderTime=20150727150240&payType=00&signType=4&payerName=xiajinxin&inputCharset=1&bgUrl=http://123.57.145.146:8080/showcase-payment/payment/callback", result);
	}
}
