package com.epam.jmp.dr.task1.calculator.operators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract base class for binary operators
 *
 */
public abstract class BinaryOperator extends AbstractOperator {
	
	//Fix for violation of DRY principle
	/**
	 * Returns result of operation on two operands
	 * @param leftOperand
	 * @param rightOperand
	 * @return
	 */
	public abstract float makeOperation(float leftOperand, float rightOperand);
	
	//Fix for violation of DRY principle
	@Override
	public float evaluate(String expression) {
		String regex = getExpression();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(expression);
		
		matcher.find();
		
		String leftOperandStr = matcher.group(1);
		String rightOperandStr = matcher.group(2);
		
		float leftOperand = Float.parseFloat(leftOperandStr);
		float rightOperand = Float.parseFloat(rightOperandStr);
		
		return makeOperation(leftOperand, rightOperand);
		
	}

}
