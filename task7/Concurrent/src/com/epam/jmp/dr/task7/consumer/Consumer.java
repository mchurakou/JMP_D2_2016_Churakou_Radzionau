package com.epam.jmp.dr.task7.consumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.epam.jmp.dr.task7.producer.ProducersContainer;
import com.epam.jmp.dr.task7.transmitter.Transmitter;

public class Consumer implements Runnable {

	@Override
	public void run() {

		int sleepTime = (new Random()).nextInt(5) + 1;

		while (!ProducersContainer.isWorkDone()) {
			try {
				TimeUnit.SECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

			Integer value = null;
			try {
				value = Transmitter.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (value == null) {
				continue;
			}

			String result = value + " - number was handled";
			
			//ToDO: write to the file, according to number
			

		}
		
		System.out.println(Thread.currentThread().getName() + " consumer finished");

	}

}
