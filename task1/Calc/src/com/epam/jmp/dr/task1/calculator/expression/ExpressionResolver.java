package com.epam.jmp.dr.task1.calculator.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.jmp.dr.task1.calculator.operators.Operator;

public class ExpressionResolver {

	// Fix for violation of SRP (single responsibility principle)
	/**
	 * Returns float result of given expression
	 * 
	 * @param strExpr
	 *            - string with expression
	 * @return expression evaluation result
	 * @throws UnsupportedOperationException
	 */
	public static float resolve(String strExpr) throws UnsupportedOperationException {
		String trimmedStr = strExpr.replaceAll(" ", "");

		for (Operator operator : Operator.getOperatorsByPriority()) {
			String regex = operator.getExpression();
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(trimmedStr);
			String matchedString = null;
			while (matcher.find()) {
				matchedString = matcher.group();
				float result = evaluateSingleOperatorExpression(matchedString, operator);
				if (matchedString.equals(trimmedStr)) {
					return result;
				} else {
					trimmedStr = matcher.replaceFirst(String.valueOf(result));
					return resolve(trimmedStr);
				}
			}
		}

		throw new UnsupportedOperationException();
	}

	// Fix for violation of DRY and SRP principles
	/**
	 * Returns float result of given expression for specific operator
	 * 
	 * @param expression
	 * @param operator
	 * @return
	 */
	public static float evaluateSingleOperatorExpression(String expression, Operator operator) {
		Matcher matcher = operator.getPattern().matcher(expression);

		matcher.find();

		String leftOperandStr = matcher.group(1);
		String rightOperandStr = matcher.group(2);

		float leftOperand = Float.parseFloat(leftOperandStr);
		float rightOperand = Float.parseFloat(rightOperandStr);

		return operator.makeOperation(leftOperand, rightOperand);
	}

}
