package com.epam.jmp.dr.task7.producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducersContainer {
	
	private static ExecutorService executor = null;
	
	public static void start(int threadsAmount)
	{
		executor = Executors.newFixedThreadPool(threadsAmount);
		
		for(int i = 0; i < threadsAmount; i++)
		{
			executor.submit(new Producer());
		}
	}
	
	public static void stop()
	{
		if(executor != null)
		{
			executor.shutdown();
		}
	}
	
	public static ExecutorService getExecutor()
	{
		return executor;
	}
	
	public static boolean isWorkDone()
	{
		if(executor != null)
		{
			return executor.isTerminated();
		}
		return false;
	}
	

}
