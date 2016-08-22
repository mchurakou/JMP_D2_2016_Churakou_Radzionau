package com.epam.jmp.dr.task7.generator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Generator {
	
	private static int counter = 0;
	
	private static Lock lock = new ReentrantLock();
	
	public static int getValue()
	{
		lock.lock();
		++counter;
		lock.unlock();
		return counter;
		
	}

}
