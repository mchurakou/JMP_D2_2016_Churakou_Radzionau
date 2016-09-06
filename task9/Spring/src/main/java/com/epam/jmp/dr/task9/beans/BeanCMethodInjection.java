package com.epam.jmp.dr.task9.beans;

public abstract class BeanCMethodInjection {

	public abstract BeanD getBeanD();

	public int getNumber() {
		return getBeanD().getNumber();
	}

}
