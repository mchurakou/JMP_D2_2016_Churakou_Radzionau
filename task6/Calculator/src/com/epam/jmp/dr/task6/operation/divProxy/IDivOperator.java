package com.epam.jmp.dr.task6.operation.divProxy;

/**
 * IDiv interface
 *
 */
public interface IDivOperator {

	/**
	 * 
	 * @param a - first operand
	 * @param b - second operand
	 * @return Returns 0 and prints error message if second operand is 0
	 */
	public float getDiv(float a, float b);

}
