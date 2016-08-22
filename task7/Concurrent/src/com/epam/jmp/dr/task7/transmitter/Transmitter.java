package com.epam.jmp.dr.task7.transmitter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Transmitter {
	
	private static BlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(15);
	
	public static void put(int value) throws InterruptedException
	{
		q.put(value);
	}
	
	public static int get() throws InterruptedException
	{
		return q.poll(8, TimeUnit.SECONDS);
	}

}
