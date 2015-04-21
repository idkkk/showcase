package org.rubik.sandbox.jdk8;

import java.math.BigDecimal;
import java.util.List;

public abstract class PriceUtils {
	public static BigDecimal totalOfDiscountedPrices(List<BigDecimal> prices) {
		BigDecimal totalOfDiscountedPrices = BigDecimal.ZERO;
		for (BigDecimal price : prices) {
			if (price.compareTo(BigDecimal.valueOf(20)) > 0)
				totalOfDiscountedPrices = totalOfDiscountedPrices.add(price
						.multiply(BigDecimal.valueOf(0.9)));
		}
		return totalOfDiscountedPrices;
	}

	public static BigDecimal totalOfDiscountedPricesWithFP(List<BigDecimal> prices) {
		return prices.stream()
				.filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
				.map(price -> price.multiply(BigDecimal.valueOf(0.9)))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public static BigDecimal totalOfDiscountedPricesWithFPAndParallel(List<BigDecimal> prices) {
		return prices.stream().parallel()   //parallelStream()
				.filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
				.map(price -> price.multiply(BigDecimal.valueOf(0.9)))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}