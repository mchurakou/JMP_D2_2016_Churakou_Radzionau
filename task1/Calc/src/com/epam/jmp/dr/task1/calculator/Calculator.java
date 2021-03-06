package com.epam.jmp.dr.task1.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.epam.jmp.dr.task1.calculator.expression.ExpressionResolver;


/**
 * 
 * Calculator runner class
 *
 */
public class Calculator {

	public static void main(String[] args) {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
	            System.in));

		while (true) {
		    System.out.println("Type some expression. Supported operations: '+ - / *'.\n"
		    		+ "Brackets are not supported. Type 'exit' to exit programm");
		    
		    String str = "";
		    
			try {
				str = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (str.equals("exit")) {
				break;
			}
			
			float result = 0;
			
			try {
				result = ExpressionResolver.resolve(str);
			}
			catch (UnsupportedOperationException e) {
				System.err.println("Sorry, this operation is not supported");
			}
			
			System.out.println(str + " = " + result);
		}		
	}	

}
