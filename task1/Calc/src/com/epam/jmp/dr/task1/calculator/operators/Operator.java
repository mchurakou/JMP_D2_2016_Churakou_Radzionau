package com.epam.jmp.dr.task1.calculator.operators;

public interface Operator extends Comparable<Operator> {
	
	/**
	 * Returns regex string of operator expression
	 * @return regex string of operator expression
	 */
	String getExpression();
	
	/**
	 * Returns operator priority
	 * @return operator priority
	 */
	int getPriority();
	
	/**
	 * Evaluates expression
	 * @param expression String containing mathematical expression
	 * @return result of expression evaluation
	 */
	float evaluate(String expression);

}
