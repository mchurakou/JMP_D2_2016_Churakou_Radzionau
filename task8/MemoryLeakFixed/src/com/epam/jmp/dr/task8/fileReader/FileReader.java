package com.epam.jmp.dr.task8.fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {

	private BufferedReader bufferedReader;

	public FileReader(String filename) throws FileNotFoundException {
		java.io.FileReader fileReader = new java.io.FileReader(filename);
		bufferedReader = new BufferedReader(fileReader);
	}

	public String getNextLine() throws IOException {
		return bufferedReader.readLine();
	}

	public void close() throws IOException {
		bufferedReader.close();
	}

}
