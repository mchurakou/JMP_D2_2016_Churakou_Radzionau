package com.epam.jmp.dr.task7.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.jmp.dr.task7.fileWriter.FileWriter;
import com.epam.jmp.dr.task7.transmitter.Transmitter;

/**
 * Consumer thread class
 * @author KAT
 *
 */
public class Consumer implements Runnable {

	private WorkflowController controller;
	private CountDownLatch latch;

	public Consumer(WorkflowController controller, CountDownLatch latch) {
		this.controller = controller;
		this.latch = latch;
	}

	@Override
	public void run() {

		int sleepTime = (new Random()).nextInt(5) + 1;
		Map<Integer, String> handledValues = new HashMap<>();
		message("Started with sleeepTime: " + sleepTime + "s");
		while (!controller.isGenerationStopped() || !Transmitter.isEmpty()) {
			try {
				TimeUnit.SECONDS.sleep(sleepTime);

				message("Trying to get value");
				Integer value = Transmitter.get();
				message(" Value getted: " + value);

				if (value != null) {
					String result = value + " - number was handled";
					handledValues.put(value, result);
					flushToFile(handledValues);
				}
			} catch (InterruptedException e) {
				message("Interrupted");
				return;
			}
		}

		while (!handledValues.isEmpty()) {
			flushToFile(handledValues);
		}

		message("Exit");
		latch.countDown();

	}

	private void flushToFile(Map<Integer, String> handledValues) {
		if (handledValues.isEmpty()) {
			return;
		}
		Iterator entries = handledValues.entrySet().iterator();
		while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();
			Integer i = (Integer) thisEntry.getKey();
			String str = (String) thisEntry.getValue();

			if (FileWriter.getCurrCounter() == i) {
				FileWriter.write(handledValues.get(i));
				entries.remove();
			}
		}
	}

	private void message(String msg) {
		Logger logger = LogManager.getRootLogger();
		logger.debug(Thread.currentThread().getName() + "-CONSUMER " + msg);
	}

}
