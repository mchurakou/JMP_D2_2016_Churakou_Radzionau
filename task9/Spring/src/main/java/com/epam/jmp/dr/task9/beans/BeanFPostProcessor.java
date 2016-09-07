package com.epam.jmp.dr.task9.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanFPostProcessor implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("BeanF")) {
			System.out.println(beanName + " BeanPostProcessor.postProcessAfterInitialization method is called");
		}
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("BeanF")) {
			System.out.println(beanName + " BeanPostProcessor.postProcessAfterInitialization method is called");
		}
		return bean;
	}

}
