package com.epam.jmp.dr.task7;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.epam.jmp.dr.task7.consumer.Consumer;
import com.epam.jmp.dr.task7.generator.Generator;
import com.epam.jmp.dr.task7.producer.ProducersContainer;

public class Runner {

	public static void main(String[] args) throws InterruptedException {
		
		int producersAmount = 15;
		int consumersAmount = 10;
		
		ExecutorService consumerService = Executors.newFixedThreadPool(consumersAmount);
		ProducersContainer.start(producersAmount);
		
		for(int i = 0; i < consumersAmount; i++)
		{
			consumerService.submit(new Consumer());
		}
		
		//ProducersContainer.stop();
		//consumerService.shutdown();
		

	}

}
