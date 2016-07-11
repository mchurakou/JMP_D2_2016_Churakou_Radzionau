package com.epam.jmp.dr.task1.calculator.operators.factory;

import com.epam.jmp.dr.task1.calculator.operators.DiffOperator;
import com.epam.jmp.dr.task1.calculator.operators.DivOperator;
import com.epam.jmp.dr.task1.calculator.operators.MulOperator;
import com.epam.jmp.dr.task1.calculator.operators.Operator;
import com.epam.jmp.dr.task1.calculator.operators.SumOperator;

/**
 * Operator class factory
 *
 */

//Violation of YAGNI principle - Factory is not required
public class OperatorsFactory {
	
	/**
	 * Method instaniates and returns Operator object dependent of operatorType string
	 * @param operatorType
	 * @return
	 */
	public Operator getOperator(String operatorType)
	{
		if(operatorType == null)
		{
			return null;
		}
		
		if(operatorType.equals("SUM"))
		{
			return new SumOperator();
		}
		else if(operatorType.equals("DIFF"))
		{
			return new DiffOperator();
		}
		else if(operatorType.equals("MUL"))
		{
			return new MulOperator();
		}
		else if(operatorType.equals("DIV"))
		{
			return new DivOperator();
		}
		
		return null;
	}

}
