package com.mooczone.test1;

public class Test {

	
	private void swap(int a, int b){
		int c;
		c = a;
		a = b;
		b = c;
	}
	 
	
	
	public static void main(String[] args) {
		
		Test test = new Test();
		
		int a = 1;
		int b = 2;
		test.swap(a, b);
		
		System.out.println("The a = "+ a + ", The b = " + b +".");
	}

}
