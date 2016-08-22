package com.epam.jmp.dr.task7.producer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.epam.jmp.dr.task7.generator.Generator;
import com.epam.jmp.dr.task7.transmitter.Transmitter;

public class Producer implements Runnable {

	@Override
	public void run() {
		
		int sleepTime = (new Random()).nextInt(5) + 1;
		
		try {
			TimeUnit.SECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		int value = Generator.getValue();
		
		try {
			Transmitter.put(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() +  " finished");

	}
	
	

}
