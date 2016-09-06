package com.epam.jmp.dr.task9;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.jmp.dr.task9.beans.BeanA;
import com.epam.jmp.dr.task9.beans.BeanB;
import com.epam.jmp.dr.task9.beans.BeanCMethodInjection;
import com.epam.jmp.dr.task9.beans.BeanCSetter;
import com.epam.jmp.dr.task9.beans.BeanE;
import com.epam.jmp.dr.task9.beans.BeanF;

public class Runner {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//bean A DI via setters
		BeanA beanA = (BeanA) context.getBean("BeanA");

		System.out.println(beanA);

		//bean B DI via constructor of bean A
		BeanB beanB = (BeanB) context.getBean("BeanB");

		System.out.println(beanB);

		// singleton bean C uses DI via setter of prototype bean D
		BeanCSetter beanCSetter = (BeanCSetter) context.getBean("BeanCSetter");

		// singleton bean C uses DI via Lookup Method Injection of prototype bean D
		BeanCMethodInjection beanCMInjection = (BeanCMethodInjection) context.getBean("BeanCMethodInjection");
	
		System.out.println("Calling getNumber() method of beanCSetter: " + beanCSetter.getNumber());
		System.out.println("Calling getNumber() method of beanCSetter: " + beanCSetter.getNumber());

		System.out.println("Calling getNumber() method of beanCMInjection: " + beanCMInjection.getNumber());
		System.out.println("Calling getNumber() method of beanCMInjection: " + beanCMInjection.getNumber());

		// Method Replacement
		BeanE originalBeanE = (BeanE) context.getBean("BeanEOriginal");
		BeanE replacedMethodBeanE = (BeanE) context.getBean("BeanEMReplaced");

		System.out.println(originalBeanE.getString());
		System.out.println(replacedMethodBeanE.getString());

		System.out.println("");

		// lifecycle of bean F
		BeanF beanF = (BeanF) context.getBean("BeanF");

		((AbstractApplicationContext) context).registerShutdownHook();

	}

}
