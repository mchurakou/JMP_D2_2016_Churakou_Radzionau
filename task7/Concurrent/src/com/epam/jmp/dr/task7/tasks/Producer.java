package com.epam.jmp.dr.task7.tasks;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.dr.task7.generator.Generator;
import com.epam.jmp.dr.task7.transmitter.Transmitter;

/**
 * Producer thread class
 * @author KAT
 *
 */
public class Producer implements Runnable {

	private CountDownLatch latch;

	public Producer(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {

		int sleepTime = (new Random()).nextInt(5) + 1;
		int value = -1;
		message("Started with sleeepTime: " + sleepTime + "s");
		while ((value = Generator.getValue()) > 0) {
			try {
				TimeUnit.SECONDS.sleep(sleepTime);

				message("Trying to put value: " + value);
				Transmitter.put(value);
				message("Putted value: " + value);

			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}

		}
		message("Generator stopped");
		message("Exiting");
		latch.countDown();

	}

	private void message(String msg) {
		Logger logger = LogManager.getRootLogger();
		logger.debug(Thread.currentThread().getName() + "-PRODUCER " + msg);
	}

}
