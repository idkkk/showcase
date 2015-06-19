package org.hashids;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HashidsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// 通过redis自增获取10进制数，用于产生唯一的短地址
//		HashSet<String> result = new HashSet<>();
		Hashids hashids = new Hashids();
		for (long num = 1; num <= 100000; num++) {
			// 编码产生短地址
			String hash = hashids.encode(num);
//			System.out.println(num + " -> " + hash);
//			result.add(hash);

			// TODO: 将10进制数 -> 长网址, 存入redis备查
			
			// 请求时短地址解码为10机制数，根据10机制获取对应的长地址
			long[] nums = hashids.decode(hash);
			assertEquals(1, nums.length);
			assertEquals(num, nums[0]);
		}

//		System.out.println(result.size());
	}

}
