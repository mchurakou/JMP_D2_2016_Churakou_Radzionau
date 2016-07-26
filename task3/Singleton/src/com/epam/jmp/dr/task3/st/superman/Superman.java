package com.epam.jmp.dr.task3.st.superman;

public class Superman {

	private static Superman instance = null;

	public String name;

	public int amountOfSavedPeople;

	private Superman() {
		name = "Clark";
		amountOfSavedPeople = 100;
	}

	public static synchronized Superman getInstance() {
		if (instance == null) {
			instance = new Superman();
		}
		return instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmountOfSavedPeople() {
		return amountOfSavedPeople;
	}

	public void setAmountOfSavedPeople(int amountOfSavedPeople) {
		this.amountOfSavedPeople = amountOfSavedPeople;
	}

	public String toString() {
		return "Name: " + name + "; People saved: " + amountOfSavedPeople;
	}

}
