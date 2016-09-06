package com.epam.jmp.dr.task9.beans;

import java.util.Random;

public class BeanD {

	private int number;

	public BeanD() {
		number = new Random().nextInt(100);
	}

	public int getNumber() {
		return number;
	}

}
