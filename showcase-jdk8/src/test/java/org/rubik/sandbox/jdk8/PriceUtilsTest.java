package org.rubik.sandbox.jdk8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class PriceUtilsTest extends TestCase {
	private final List<BigDecimal> allPrices = Arrays.asList(
			new BigDecimal(10), new BigDecimal(80), new BigDecimal(25),
			new BigDecimal(190), new BigDecimal(160), new BigDecimal(180),
			new BigDecimal(2000), new BigDecimal(9000), new BigDecimal(8000),
			new BigDecimal(20000), new BigDecimal(35000), new BigDecimal(50000));

	// Lambda
	@Test
	public void testFP() {
		BigDecimal result1 = PriceUtils.totalOfDiscountedPrices(allPrices);
		BigDecimal result2 = PriceUtils.totalOfDiscountedPricesWithFP(allPrices);
		assertEquals(BigDecimal.valueOf(112171.5), result1);
		assertTrue(result1.equals(result2));
	}

	@Test
	public void testParallel() {
		BigDecimal result = PriceUtils.totalOfDiscountedPricesWithFPAndParallel(allPrices);
		assertEquals(BigDecimal.valueOf(112171.5), result);
	}

}
