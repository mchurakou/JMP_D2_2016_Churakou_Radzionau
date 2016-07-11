package com.epam.jmp.dr.task1.calculator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.epam.jmp.dr.task1.calculator.operators.DiffOperator;
import com.epam.jmp.dr.task1.calculator.operators.DivOperator;
import com.epam.jmp.dr.task1.calculator.operators.MulOperator;
import com.epam.jmp.dr.task1.calculator.operators.Operator;
import com.epam.jmp.dr.task1.calculator.operators.SumOperator;

/**
 * 
 * OperatorsContainer util class. Contains array of available operators, sorted by priority
 *
 */
public class OperatorsContainer {
	
	private static OperatorsContainer instance = null;
	
	private Operator[] operators;
	
	private OperatorsContainer()
	{
		List<Operator> o = new ArrayList<Operator>();
		
		//Fix of violation of YAGNI principle - Factory is not required
		
		o.add(new SumOperator());
		o.add(new DiffOperator());
		o.add(new MulOperator());
		o.add(new DivOperator());
		
		operators = new Operator[o.size()];
		operators = o.toArray(operators);
		Arrays.sort(operators);
	}
	
	/**
	 * Returns array of available operators
	 * @return
	 */
	public Operator[] getAvailableOperators()
	{
		return operators;
	}
	
	public static OperatorsContainer getInstance()
	{
		if(instance == null)
		{
			instance = new OperatorsContainer();
		}
		return instance;
	}

}
