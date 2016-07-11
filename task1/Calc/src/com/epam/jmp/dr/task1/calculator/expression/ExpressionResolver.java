package com.epam.jmp.dr.task1.calculator.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.jmp.dr.task1.calculator.operators.Operator;
import com.epam.jmp.dr.task1.calculator.util.OperatorsContainer;

public class ExpressionResolver {
	
	//Fix for violation of SRP (single responsibility principle)
	/**
	 * Returns float result of given expression
	 * @param strExpr - string with expression
	 * @return expression evaluation result
	 * @throws UnsupportedOperationException
	 */
	public static float resolve(String strExpr) throws UnsupportedOperationException
	{
		String trimmedStr = strExpr.replaceAll(" ", "");
		
		Operator[] operators = OperatorsContainer.getInstance().getAvailableOperators();
		for(Operator operator : operators)
		{
			String regex = operator.getExpression();
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(trimmedStr);
			String matchedString = null;;
			while(matcher.find())
			{
				matchedString = matcher.group();
				float result = operator.evaluate(matchedString);
				if(matchedString.equals(trimmedStr))
				{
					return result;
				}
				else
				{
					trimmedStr = matcher.replaceFirst(String.valueOf(result));
					return resolve(trimmedStr);
				}
			}
		}
		
		throw new UnsupportedOperationException();
	}

}
