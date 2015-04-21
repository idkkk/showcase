package org.rubik.sandbox.guava;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * FluentIterable测试用例.
 */
public class FluentIterableTest extends TestCase {

	private List<Product> products;

	@Before
	protected void setUp() throws Exception {
		Product a = new Product();
		a.setId(10000L);
		a.setName("Iphone5S");
		a.setInventory(1000000);
		a.setFrozen(false);

		Product b = new Product();
		b.setId(20000L);
		b.setName("Android HTC");
		b.setInventory(1000000);
		b.setFrozen(false);

		Product c = new Product();
		c.setId(30000L);
		c.setName("Samsung Note4");
		c.setInventory(1000000);
		c.setFrozen(false);

		Product d = new Product();
		d.setId(40000L);
		d.setName("Windows Phone8");
		d.setInventory(1000000);
		d.setFrozen(false);

		products = Lists.newArrayList(a, b, c, d);
	}

	@After
	protected void tearDown() throws Exception {
		products.clear();
	}

	@Test
	public void testFilter() {
		List<Product> matchedProducts = FluentIterable.from(products).filter(new Predicate<Product>() {
			public boolean apply(Product input) {
				return input.getId() < 40000;
			}
		}).toList();
		assertNotNull(matchedProducts);
		assertEquals(3, matchedProducts.size());
	}

	@Test
	public void testTransform() {
		List<String> result = FluentIterable.from(products).transform(product -> product.getName()).toList();
		assertEquals(4, Iterables.size(result));
	}
}