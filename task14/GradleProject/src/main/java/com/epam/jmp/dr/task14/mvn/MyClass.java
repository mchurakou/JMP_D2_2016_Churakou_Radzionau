package com.epam.jmp.dr.task14.mvn;

/**
 * My class
 * @author Nikopol
 *
 */
public class MyClass {

	/**
	 * String property
	 */
	private String strProp;
	/**
	 * int property
	 */
	private int intProp;
	/**
	 * bool property
	 */
	private boolean boolProp;

	/**
	 * Constructor
	 * @param strProp
	 * @param intProp
	 * @param boolProp
	 */
	public MyClass(String strProp, int intProp, boolean boolProp) {
		this.strProp = strProp;
		this.intProp = intProp;
		this.boolProp = boolProp;
	}

	/**
	 * str property getter
	 * @return strProp
	 */
	public String getStrProp() {
		return strProp;
	}

	/**
	 * str property setter
	 * @param strProp
	 */
	public void setStrProp(String strProp) {
		this.strProp = strProp;
	}

	/**
	 * int property getter
	 * @return intProp
	 */
	public int getIntProp() {
		return intProp;
	}

	/**
	 * int property setter
	 * @param intProp
	 */
	public void setIntProp(int intProp) {
		this.intProp = intProp;
	}

	/**
	 * bool property getter
	 * @return boolProp
	 */
	public boolean isBoolProp() {
		return boolProp;
	}

	/**
	 * bool property sesser
	 * @param boolProp
	 */
	public void setBoolProp(boolean boolProp) {
		this.boolProp = boolProp;
	}

}
