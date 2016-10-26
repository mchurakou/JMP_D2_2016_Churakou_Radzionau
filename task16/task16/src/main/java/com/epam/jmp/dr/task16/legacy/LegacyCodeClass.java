package com.epam.jmp.dr.task16.legacy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LegacyCodeClass {

	private String getMessage() {
		return "Legacy code private message";
	}

	public static String getStaticMessage() {
		return "Legacy static message";
	}

	public void printPrivateMessage() {
		System.out.println(getMessage());
	}

	public final String getFinalMessage() {
		return "Legacy final message";
	}

	public String getStringFromFile() throws IOException {
		FileReader fileReader = new FileReader("test");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		line = bufferedReader.readLine();

		bufferedReader.close();

		return line;
	}

}
