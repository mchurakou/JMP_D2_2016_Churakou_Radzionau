package com.epam.jmp.dr.task1.calculator.util;

import com.epam.jmp.dr.task1.calculator.operators.DiffOperator;
import com.epam.jmp.dr.task1.calculator.operators.DivOperator;
import com.epam.jmp.dr.task1.calculator.operators.MulOperator;
import com.epam.jmp.dr.task1.calculator.operators.Operator;
import com.epam.jmp.dr.task1.calculator.operators.SumOperator;

/**
 * Util class
 *
 */
public class OperatorUtil {
	
	//Violation of OCP (open/closed principle)
	/**
	 * returns operator priority based on operator class
	 * @param operator
	 * @return operator priority
	 */
	public static int getPriority(Operator operator)
	{
		if(operator instanceof SumOperator) return 1;
		if(operator instanceof DiffOperator) return 1;
		if(operator instanceof MulOperator) return 2;
		if(operator instanceof DivOperator) return 2;
		throw new IllegalArgumentException("Operator type not supported: " + operator.getClass());
	}

}
