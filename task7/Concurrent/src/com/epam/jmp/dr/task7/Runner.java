package com.epam.jmp.dr.task7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;

import com.epam.jmp.dr.task7.generator.Generator;
import com.epam.jmp.dr.task7.tasks.Consumer;
import com.epam.jmp.dr.task7.tasks.Producer;
import com.epam.jmp.dr.task7.tasks.WorkflowController;

public class Runner {

	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String producersAmountStr = "";
		String consumersAmountStr = "";
		String generatorMaxCountStr = "";

		try {
			System.out.println("Enter number of producer threads: ");
			producersAmountStr = reader.readLine();
			System.out.println("Enter number of consumer threads: ");
			consumersAmountStr = reader.readLine();
			System.out.println("Enter generator max count: ");
			generatorMaxCountStr = reader.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		int producersAmount = Integer.valueOf(producersAmountStr);
		int consumersAmount = Integer.valueOf(consumersAmountStr);
		int generatorMaxCount = Integer.valueOf(generatorMaxCountStr);

		LogManager.getRootLogger().debug("////////////////////////////////////////////////");
		LogManager.getRootLogger().debug("WORK STARTED");

		System.out.println("Please wait...");

		Generator.setMaxCount(generatorMaxCount);

		WorkflowController controller = new WorkflowController();
		CountDownLatch producerLatch = new CountDownLatch(producersAmount);
		CountDownLatch consumerLatch = new CountDownLatch(consumersAmount);

		ExecutorService executor = Executors.newFixedThreadPool(producersAmount + consumersAmount);
		for (int i = 0; i < producersAmount; i++) {
			executor.submit(new Producer(producerLatch));
		}

		for (int i = 0; i < consumersAmount; i++) {
			executor.submit(new Consumer(controller, consumerLatch));
		}

		try {
			producerLatch.await();
			controller.setGenerationStopped();
			System.out.println("All Producers are stopped");
			consumerLatch.await();
			System.out.println("Work is done!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();

	}

}
