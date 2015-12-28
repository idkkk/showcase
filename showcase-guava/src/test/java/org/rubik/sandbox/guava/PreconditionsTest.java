package org.rubik.sandbox.guava;

import static com.google.common.base.Preconditions.*;
import junit.framework.TestCase;

import org.junit.Test;

import com.google.common.base.Preconditions;

/**
 * Preconditions测试用例.
 */
public class PreconditionsTest extends TestCase {

	@SuppressWarnings("unused")
	@Test
	public void testCheckNotNull() {
		String str = checkNotNull("abc");
		assertEquals("abc", str);

		try {
			checkNotNull(null, "args can't be null");
			assertTrue(false);
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testCheckElementIndex() {
		int[] ints = new int[10];
		int index = checkElementIndex(5, ints.length);
		assertEquals(5, index);

		try {
			checkElementIndex(10, ints.length, "Index out of bounds for ints");
			assertTrue(false);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}
	}

@Test
public void testCheckArgument() {
	int age = 35;
	Preconditions.checkArgument((age > 0 && age < 150), "年龄必须是介于0～150之间！(%s)", age);

	age = 300;
	try {
		checkArgument((age > 0 && age < 150), "年龄必须是介于0～150之间！(%s)", age);
		assertTrue(false);
	} catch (IllegalArgumentException e) {
		assertTrue(true);
	}
}

	@Test
	public void testCheckState() {
		checkState(validateProductState(), "Product state is invalid");
	}

	private boolean validateProductState() {
		int inventory = 1000;  // 商品库存
		boolean isFrozen = false;  // 是否冻结

		return (inventory > 0) && !isFrozen;
	}

//	@Test
//	public void testCheckPositionIndex() {
//		List<String> lists = Arrays.asList("ab", "bc", "cd");
//		int index = checkPositionIndex(2, lists.size());
//		assertEquals(2, index);
//
//		try {
//			checkPositionIndex(3, lists.size(), "超出边界");
//			assertTrue(false);
//		} catch (IndexOutOfBoundsException e) {
//			assertTrue(true);
//		}
//	}
}
