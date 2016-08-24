package com.epam.jmp.dr.task7.transmitter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Transmitter class Transmits values between producers and consumers
 * 
 * @author KAT
 *
 */
public class Transmitter {

	/**
	 * queue
	 */
	private static BlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(15);

	/**
	 * puts value in the queue
	 * 
	 * @param value
	 * @throws InterruptedException
	 */
	public static void put(int value) throws InterruptedException {
		q.put(value);
	}

	/**
	 * gets value from the queue
	 */
	public static Integer get() {
		return q.poll();
	}

	/**
	 * checks if queue is empty
	 */
	public static boolean isEmpty() {
		return q.isEmpty();
	}

}
