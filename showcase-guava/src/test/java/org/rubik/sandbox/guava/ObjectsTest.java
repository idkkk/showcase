package org.rubik.sandbox.guava;

import junit.framework.TestCase;

import org.junit.Test;

import com.google.common.base.Objects;

/**
 * Objects测试用例.
 */
public class ObjectsTest extends TestCase {

	@Test
	public void testToString() {
		Product product = new Product();
		product.setId(10020L);
		product.setName("法式软面包");
		product.setInventory(100);
		product.setFrozen(true);
		assertEquals("Product{id=10020, name=法式软面包, inventory=100, frozen=true}", product.toString());

		product = new Product();
		product.setId(10010L);
		product.setInventory(100);
		product.setFrozen(true);
		assertEquals("Product{id=10010, inventory=100, frozen=true}", product.toString());
	}

	@Test
	public void testFirstNonNull() {
		String a = "abc";
		String value = Objects.firstNonNull(a, "Default Value");
		assertEquals("abc", value);

		String b = null;
		String defaultValue = Objects.firstNonNull(b, "Default Value");
		assertEquals("Default Value", defaultValue);
	}

	@Test
	public void testHashCode() {
		Product product = new Product();
		product.setId(10000L);
		product.setName("Iphone5S");
		product.setInventory(1000000);
		product.setFrozen(false);

		int hashCode = product.hashCode();
		assertEquals(2134263945, hashCode);
	}

	@Test
	public void testCompareTo() {
		Product a = new Product();
		a.setId(10000L);
		a.setName("Iphone5S");
		a.setInventory(1000000);
		a.setFrozen(false);

		Product b = new Product();
		b.setId(60000L);
		b.setName("RMBP 15''");
		b.setInventory(1000000);
		b.setFrozen(false);

		assertEquals(-1, a.compareTo(b));
	}
}