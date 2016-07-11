package com.epam.jmp.dr.task1.calculator.operators;

public class DiffOperator extends BinaryOperator {


	@Override
	public String getExpression() {
		return "(\\d+\\.*\\d*)-(\\d+\\.*\\d*)";
	}

	@Override
	public int getPriority() {
		//Fix violation of OCP (open/closed principle)
		return 1;
	}

	@Override
	public float makeOperation(float leftOperand, float rightOperand) {
		return leftOperand - rightOperand;
	}
	
	

}
