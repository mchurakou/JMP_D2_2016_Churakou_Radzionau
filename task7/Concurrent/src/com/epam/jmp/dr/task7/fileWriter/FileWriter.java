package com.epam.jmp.dr.task7.fileWriter;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileWriter {
	
	private static int currValue = 1;
	
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public static int getCurrCounter()
	{
		return currValue;
	}
	
	public static void write(String str)
	{
		System.out.println(str);
		currValue++;
	}

}
