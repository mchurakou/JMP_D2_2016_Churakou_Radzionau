package com.epam.jmp.dr.task1.calculator.operators;


public class DivOperator extends BinaryOperator {

	@Override
	public String getExpression() {
		return "(\\d+\\.*\\d*)/(\\d+\\.*\\d*)";
	}

	@Override
	public int getPriority() {
		//Fix violation of OCP (open/closed principle)
		return 2;
	}
	
	@Override
	public float makeOperation(float leftOperand, float rightOperand) {
		return leftOperand / rightOperand;
	}

}
