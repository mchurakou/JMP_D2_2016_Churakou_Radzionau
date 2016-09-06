package com.epam.jmp.dr.task9.beans;

public class BeanB {

	private BeanA beanA;

	public BeanB(BeanA a) {
		beanA = a;
	}

	public BeanA getBeanA() {
		return beanA;
	}

	public void setBeanA(BeanA beanA) {
		this.beanA = beanA;
	}

	public String toString() {
		return "BeanB; injected bean: " + beanA;
	}

}
