package com.epam.jmp.dr.task3.st;

import com.epam.jmp.dr.task3.st.superman.Superman;

public class Runner {

	public static void main(String[] args) {

		Superman s1 = Superman.getInstance();
		Superman s2 = Superman.getInstance();

		System.out.println("Two new pointers on Superman class were created:");
		System.out.println("s1:");
		System.out.println(s1);

		System.out.println("s2:");
		System.out.println(s2);

		System.out.println("Setting amount of saved peopel to 999999 on s1 pointer:");
		s1.setAmountOfSavedPeople(999999);
		System.out.println("s1:");
		System.out.println(s1);

		System.out.println("s2:");
		System.out.println(s2);

		System.out.println("Setting Superman name to Clark Kent on s2 poiner:");
		s2.setName("Clark Kent");
		System.out.println("s1:");
		System.out.println(s1);

		System.out.println("s2:");
		System.out.println(s2);

	}

}
