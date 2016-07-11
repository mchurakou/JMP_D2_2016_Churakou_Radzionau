package com.epam.jmp.dr.task1.calculator.operators;

/**
 * Abstract operator class
 *
 */
public abstract class AbstractOperator implements Operator {
	
	
	//Fix for violation of DRY principle
	/**
	 * Compares two operators based on their priority
	 */
	@Override
	public int compareTo(Operator o) {
		if(o == null || o == this)
		{
			return 0;
		}
		if(o.getPriority() > this.getPriority())
		{
			return 1;
		}
		if(o.getPriority() < this.getPriority())
		{
			return -1;
		}
		return 0;
	}

}
