package com.epam.jmp.dr.task1.calculator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.epam.jmp.dr.task1.calculator.operators.Operator;

import com.epam.jmp.dr.task1.calculator.operators.factory.OperatorsFactory;

/**
 * 
 * OperatorsContainer util class. Contains array of available operators, sorted
 * by priority
 *
 */
public class OperatorsContainer {

	private static OperatorsContainer instance = null;

	private Operator[] operators;

	private OperatorsContainer() {
		List<Operator> o = new ArrayList<>();

		// Violation of YAGNI principle - Factory is not required
		OperatorsFactory factory = new OperatorsFactory();

		o.add(factory.getOperator("SUM"));
		o.add(factory.getOperator("DIFF"));
		o.add(factory.getOperator("MUL"));
		o.add(factory.getOperator("DIV"));

		operators = new Operator[o.size()];
		operators = o.toArray(operators);
		Arrays.sort(operators);
	}

	/**
	 * Returns array of available operators
	 * 
	 * @return
	 */
	public Operator[] getAvailableOperators() {
		return operators;
	}

	public static OperatorsContainer getInstance() {
		if (instance == null) {
			instance = new OperatorsContainer();
		}
		return instance;
	}

}
