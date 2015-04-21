package org.rubik.sandbox.guava;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Product product = new Product();
		product.setId(100L);
		product.setName("CCTV - 移动传媒");
		product.setInventory(10000000);
		product.setFrozen(true);
		System.out.println(product.toString());
	}

}
