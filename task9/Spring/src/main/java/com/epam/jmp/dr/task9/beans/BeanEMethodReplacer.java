package com.epam.jmp.dr.task9.beans;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class BeanEMethodReplacer implements MethodReplacer {

	public Object reimplement(Object bean, Method method, Object[] args) throws Throwable {
		if (!"getString".equals(method.getName())) {
			throw new IllegalArgumentException("Wrong method reimplemented: " + method.getName());
		}
		return "Reimplemented behaviour of BeanE";
	}

}
