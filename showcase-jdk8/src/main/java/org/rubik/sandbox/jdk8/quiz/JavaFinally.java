package org.rubik.sandbox.jdk8.quiz;


public class JavaFinally {

	public static void main(String[] args) {
		Finally javaFinally = new Finally();
		System.out.println(javaFinally.getValue());
		System.out.println(javaFinally.getA());
	}

}

class Finally {
	private int a;
	
	public int getValue() {
		int b = a;
		try {
			b = 10;
			return b;
		} finally {
			b = 100;
//			return a;
		}
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
}
