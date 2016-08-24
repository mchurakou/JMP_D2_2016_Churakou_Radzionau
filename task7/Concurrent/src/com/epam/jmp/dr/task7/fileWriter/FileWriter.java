package com.epam.jmp.dr.task7.fileWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * FileWriter class
 *
 */
public class FileWriter {

	/**
	 * value that should be written next
	 */
	private static int currValue = 1;

	/**
	 * lock
	 */
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * returns value that should be written next
	 * 
	 * @return value that should be written next
	 */
	public static int getCurrCounter() {
		lock.readLock().lock();
		int result = currValue;
		lock.readLock().unlock();
		return result;
	}

	/**
	 * wwrites value to the file, and increments counter
	 * 
	 * @param str
	 */
	public static void write(String str) {
		lock.writeLock().lock();
		String fileName = "output.txt";
		java.io.FileWriter fw;
		try {
			fw = new java.io.FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(str + "\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		currValue++;
		lock.writeLock().unlock();
	}

}
