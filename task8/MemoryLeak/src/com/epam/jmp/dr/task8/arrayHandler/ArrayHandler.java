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
		// Causes memory leak
		// str.substring returns original string, but with the offset set to 0
		// and count set to 3
		list.add(str.substring(0, 3));
	}

}
