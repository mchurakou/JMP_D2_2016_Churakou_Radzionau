package com.epam.jmp.dr.task7;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.epam.jmp.dr.task7.consumer.Consumer;
import com.epam.jmp.dr.task7.fileWriter.FileWriter;
import com.epam.jmp.dr.task7.generator.Generator;
import com.epam.jmp.dr.task7.producer.ProducersContainer;
import com.epam.jmp.dr.task7.transmitter.Transmitter;

public class Runner {

	public static void main(String[] args) throws InterruptedException {
		
		int producersAmount = 10;
		int consumersAmount = 15;
		
		Generator.setMaxCount(100);
		
		Runnable producer = () -> {
			
			int sleepTime = (new Random()).nextInt(5) + 1;
			int value = -1;
			
			while((value = Generator.getValue()) > 0)
			{
				try {
					TimeUnit.SECONDS.sleep(sleepTime);
					Transmitter.put(value);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
				
				
				//System.out.println(Thread.currentThread().getName() +  " val: " + value);
			}
			
			//System.out.println(Thread.currentThread().getName() +  " Done!");
			
		};
		
		Runnable consumer = () -> {
			int sleepTime = (new Random()).nextInt(5) + 1;
			
			while(!Generator.isStopped())
			{
				try {
					TimeUnit.SECONDS.sleep(sleepTime);
					Integer value = Transmitter.get();
					if(value != null)
					{
						String result = value + " - number was handled";
						while(FileWriter.getCurrCounter() != value) {}
						FileWriter.write(result);
						//System.out.println(result);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
				
			}
			
			System.out.println(Thread.currentThread().getName() +  " Done!");
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(producersAmount + consumersAmount);
		for(int i = 0; i < producersAmount; i++)
		{
			executor.submit(producer);
		}
		
		for(int i = 0; i < consumersAmount; i++)
		{
			executor.submit(consumer);
		}
		
		executor.shutdown();
		
		

	}

}
