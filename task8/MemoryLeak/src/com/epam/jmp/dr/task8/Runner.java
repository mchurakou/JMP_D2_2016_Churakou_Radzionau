package com.epam.jmp.dr.task8;

import java.io.IOException;

import com.epam.jmp.dr.task8.arrayHandler.ArrayHandler;
import com.epam.jmp.dr.task8.fileReader.FileReader;

public class Runner {

	public static void main(String[] args) throws IOException, InterruptedException {

		FileReader reader = new FileReader("resources/src.txt");
		ArrayHandler handler = new ArrayHandler();

		String lineFromFile = null;
		while (true) {
			lineFromFile = reader.getNextLine();
			if (lineFromFile == null) {
				break;
			}
			handler.storeStr(lineFromFile);
		}

		System.out.println("Done!");
		Thread.sleep(30000);

	}

}
