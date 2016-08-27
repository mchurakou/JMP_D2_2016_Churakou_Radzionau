package com.epam.jmp.dr.task8.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	public static void main(String[] args) {
		
		ReentrantLock lock1 = new ReentrantLock();
		ReentrantLock lock2 = new ReentrantLock();
		
		Runnable thread1 = () -> {
			System.out.println("T1 trying to get lock1");
			lock1.lock();
			System.out.println("T1 lock1 getted");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("T1 trying to get lock2");
			lock2.lock();
			System.out.println("T1 lock2 getted");
			lock1.unlock();
			lock2.unlock();
			System.out.println("Thread 1 done");
		};
		
		Runnable thread2 = () -> {
			System.out.println("T2 trying to get lock2");
			lock2.lock();
			System.out.println("T2 lock2 getted");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("T2 trying to get lock1");
			lock1.lock();
			System.out.println("T2 lock1 getted");
			lock2.unlock();
			lock1.unlock();
			System.out.println("Thread 2 done");
		};
		
		new Thread(thread1).start();
		new Thread(thread2).start();

	}

}
