package com.epam.jmp.dr.task1.calculator.operators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.jmp.dr.task1.calculator.util.OperatorUtil;

/**
 * 
 * Implementation of "difference" operator
 *
 */
public class DiffOperator implements Operator {

	@Override
	public String getExpression() {
		return "(\\d+\\.*\\d*)-(\\d+\\.*\\d*)";
	}

	@Override
	public int getPriority() {
		//Violation of OCP (open/closed principle)
		return OperatorUtil.getPriority(this);
	}
	
	//Violation of DRY principle
	/**
	 * Compares two operators based on their priority
	 */
	@Override
	public int compareTo(Operator arg0) {
		if(arg0 == this)
		{
			return 0;
		}
		if(arg0.getPriority() > this.getPriority())
		{
			return 1;
		}
		if(arg0.getPriority() < this.getPriority())
		{
			return -1;
		}
		return 0;
	}
	
	//Violation of DRY principle
	
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
		
		return leftOperand - rightOperand;
		
	}

}
