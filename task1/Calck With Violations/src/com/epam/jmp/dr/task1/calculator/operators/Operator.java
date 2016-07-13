package com.epam.jmp.dr.task1.calculator.operators;

public interface Operator extends Comparable<Operator> {

	/**
	 * Method returns regular expression string, that corresponds specific
	 * operator implementation
	 * 
	 * @return regular expression string
	 */
	String getExpression();

	/**
	 * Returns operator priority
	 * 
	 * @return operator priority
	 */
	int getPriority();

	/**
	 * Returns float result of given expression
	 * 
	 * @param expression
	 * @return
	 */
	float evaluate(String expression);

}
