package com.epam.jmp.dr.task9.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.*;

public class BeanF
		implements InitializingBean, DisposableBean, BeanNameAware, ApplicationContextAware, BeanClassLoaderAware {

	private String prop;

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		System.out.println("Bean F prop property is setted to: " + prop);
		this.prop = prop;
	}

	public BeanF() {
		System.out.println("Bean F constructor is called");
	}

	public void init() {
		System.out.println("Bean F calling init() method");
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Bean F calling InitializingBean.afterPropertiesSet()");
	}

	@SuppressWarnings("restriction")
	@PostConstruct
	public void postConstruct() {
		System.out.println("Bean F postConstruct() method is called");
	}

	public void destroyBean() {
		System.out.println("Bean F destroy-method is called");
	}

	public void destroy() throws Exception {
		System.out.println("Bean F DisposableBean.destroy() method is called");

	}

	@SuppressWarnings("restriction")
	@PreDestroy
	public void preDestroy() {
		System.out.println("Bean F preDestroy() method is called");
	}

	public void setBeanName(String name) {
		System.out.println("Bean F setBeanName() is called. Bean name: " + name);
	}

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println("Bean F ApplicationContextAware.setApplicationContext() method is called");
	}

	public void setBeanClassLoader(ClassLoader arg0) {
		System.out.println("Bean F BeanClassLoaderAware.setBeanClassLoader() method is called");
	}

}
