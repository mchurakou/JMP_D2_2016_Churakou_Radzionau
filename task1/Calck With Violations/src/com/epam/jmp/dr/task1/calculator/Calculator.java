package com.epam.jmp.dr.task1.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.jmp.dr.task1.calculator.operators.Operator;
import com.epam.jmp.dr.task1.calculator.util.OperatorsContainer;

/**
 * 
 * Calculator runner class
 *
 */
public class Calculator {

	public static void main(String[] args) {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
	            System.in));

		while(true)
		{
		    System.out.println("Type some expression. Supported operations: '+ - / *'. Brackets are not supported. Type 'exit' to exit programm");
		    String str = "";
			try {
				str = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(str.equals("exit"))
			{
				break;
			}
			float result = 0;
			try
			{
				result = resolve(str);
			}
			catch(UnsupportedOperationException e)
			{
				System.err.println("Sorry, this operation is not supported");
			}
			
			System.out.println(str + " = " + result);
		}
		
	}
	
	
	//Violation of SRP (single responsibility principle)
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
