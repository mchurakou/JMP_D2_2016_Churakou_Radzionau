package com.epam.jmp.dr.task9.beans;

public class BeanA {

	private String property1;
	private int property2;
	private String property3;

	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}

	public int getProperty2() {
		return property2;
	}

	public void setProperty2(int property2) {
		this.property2 = property2;
	}

	public String getProperty3() {
		return property3;
	}

	public void setProperty3(String property3) {
		this.property3 = property3;
	}

	public String toString() {
		return "BeanA; property1: " + property1 + ", property2: " + property2 + ", property3: " + property3;
	}

}
