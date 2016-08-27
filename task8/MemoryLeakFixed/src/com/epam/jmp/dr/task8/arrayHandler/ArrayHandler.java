package com.epam.jmp.dr.task8.arrayHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayHandler class - stores substring of given string into array
 *
 */
public class ArrayHandler {

	private List<String> list;

	public ArrayHandler() {
		list = new ArrayList<String>();
	}

	public void storeStr(String str) {
		// Fix for memory leak
		// now, we create new string of 3 chars from the old big string
		String line = new String(str.substring(0, 3));
		list.add(line);
	}

}
