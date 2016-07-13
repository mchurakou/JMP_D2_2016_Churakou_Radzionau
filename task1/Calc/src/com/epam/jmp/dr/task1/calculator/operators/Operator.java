package com.epam.jmp.dr.task1.calculator.operators;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

public enum Operator {
	
	/**
	 * Sum operator
	 */
	SUM("(\\d+\\.*\\d*)\\+(\\d+\\.*\\d*)", 1) {
		/**
		 * implementation of abstract function makeOperation
		 */
		public float makeOperation(float leftOperand, float rightOperand)
		{
			return leftOperand + rightOperand;
		}
	},
	/**
	 * Difference operator
	 */
	DIFF("(\\d+\\.*\\d*)-(\\d+\\.*\\d*)", 1) {
		/**
		 * implementation of abstract function makeOperation
		 */
		public float makeOperation(float leftOperand, float rightOperand)
		{
			return leftOperand - rightOperand;
		}
	},
	/**
	 * Division operator
	 */
	DIV("(\\d+\\.*\\d*)/(\\d+\\.*\\d*)", 2)
	{
		/**
		 * implementation of abstract function makeOperation
		 */
		public float makeOperation(float leftOperand, float rightOperand)
		{
			return leftOperand / rightOperand;
		}
	},
	/**
	 * Multiplication operator
	 */
	MUL("(\\d+\\.*\\d*)\\*(\\d+\\.*\\d*)", 2)
	{
		/**
		 * implementation of abstract function makeOperation
		 */
		public float makeOperation(float leftOperand, float rightOperand)
		{
			return leftOperand * rightOperand;
		}
	};
	
	/**
	 * regular expression pattern
	 */
	private final Pattern pattern;
	/**
	 * operator priority
	 */
	private final int priority;
	/**
	 * regex string of operator expression
	 */
	private final String expression;
	
	/**
	 * Constructor
	 * @param regex operator regular expression
	 * @param priority operator priority
	 */
	Operator(String regex, int priority)
	{
		this.expression = regex;
		this.pattern = Pattern.compile(regex);
		this.priority = priority;
	}
	
	//Fix for violation of DRY principle
	/**
	 * Returns result of operation on two operands
	 * @param leftOperand
	 * @param rightOperand
	 * @return
	 */
	public abstract float makeOperation(float leftOperand, float rightOperand);
	
	
	//Fix of violation of YAGNI principle 
	/**
	 * Returns array of available operators, sorted by priority
	 * @return array of available operators, sorted by priority
	 */
	public static Operator[] getOperatorsByPriority()
	{
		Operator[] result = values();
		Arrays.sort(result, Comparator.comparing(Operator::getPriority).reversed());
		return result;
	}
	
	/**
	 * Returns operator priority
	 * @return operator priority
	 */
	public int getPriority()
	{
		return priority;
	}
	
	/**
	 * Returns operator expression
	 * @return operator expression
	 */
	public String getExpression() {
		return expression;
	}
	
	/**
	 * Returns operator pattern
	 * @return operator pattern
	 */
	public Pattern getPattern()
	{
		return pattern;
	}
	
}
