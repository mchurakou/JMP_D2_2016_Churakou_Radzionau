package com.epam.jmp.dr.task7.generator;


public class Generator {
	
	private static int counter = 0;
	
	private static int maxCount = 0;
	
	//private static Lock lock = new ReentrantLock();
	
	public static void setMaxCount(int value)
	{
		maxCount = value;
	}
	
	
	public static synchronized int getValue()
	{
		counter++;
		if(counter <= maxCount)
		{
			return counter;
		}
		return -1;
		
	}
	
	public static boolean isStopped()
	{
		return counter > maxCount;
	}

}
