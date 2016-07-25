package com.epam.jmp.dr.task3.af.factory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.epam.jmp.dr.task3.af.handler.FileHandler;
import com.epam.jmp.dr.task3.af.handler.Handler;

public class FileHadlerFactory extends AbstractHandlerFactory {

	/**
	 * Builds FileHandler with provided details
	 */
	@Override
	public Handler createHandler(String details) throws IOException {
		String fileName = details;
		// Trying to open file for writing to make sure that this is possible
		FileWriter fw = new FileWriter(fileName, true);
		fw.close();
		// Trying to open file for reading to make sure that this is possible
		FileReader fileReader = new FileReader(fileName);
		Handler handler = new FileHandler(fileName);
		return handler;
	}

}
