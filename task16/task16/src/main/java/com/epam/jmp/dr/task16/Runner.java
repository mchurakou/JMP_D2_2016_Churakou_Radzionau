package com.epam.jmp.dr.task16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.epam.jmp.dr.task16.calculator.Calculator;
import com.epam.jmp.dr.task16.calculator.Result;

public class Runner {

	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		String str = "";

		try {
			double a, b, c;
			System.out.println("This calculator solves x*x*a + x*b + c = 0. Please enter values:");

			System.out.print("a: ");
			str = reader.readLine();
			a = Double.valueOf(str);

			System.out.print("b: ");
			str = reader.readLine();
			b = Double.valueOf(str);

			System.out.print("c: ");
			str = reader.readLine();
			c = Double.valueOf(str);

			System.out.println("Solution for x*x*" + a + " + x*" + b + " + " + c + " = 0   is:");

			Result res = Calculator.calculate(a, b, c);

			System.out.println(res);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArithmeticException e) {
			System.err.println(e.getMessage());
		}

	}

}
