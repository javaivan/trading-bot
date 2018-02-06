package com.trading.util;

public class Stopper {

	long start = 0;
	String name = null;

	public Stopper(String name) {
		this.name = name;
		this.start = System.currentTimeMillis();
		
		System.out.println("Start " + name + "...");
	}
	
	public void finish() {
		System.out.println(name + " finished in " + ((System.currentTimeMillis()-start)/1000) + " seconds.");
	}
}
