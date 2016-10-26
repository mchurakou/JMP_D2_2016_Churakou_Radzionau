package com.epam.jmp.dr.task16.calculator;

public class Calculator {

	public static Result calculate(double a, double b, double c) throws ArithmeticException {
		Result res = new Result();

		if (a == 0 && b == 0 && c == 0) {
			throw new ArithmeticException("x*x*0 + x*0 + 0 = 0; x belongs (-Infinity; +Infinity)");
		}
		if (a == 0 && b == 0) {
			throw new ArithmeticException(" a and b should not be both equal to zero");
		}

		if (a == 0) {
			double x = -c / b;
			res.setX1(x);
			res.setX2(x);
			return res;
		}

		double D = b * b - 4 * a * c;

		if (D < 0) {
			throw new ArithmeticException("D < 0; result is a complex value");
		}

		double x1 = (-b + Math.sqrt(D)) / (2.0 * a);
		double x2 = (-b - Math.sqrt(D)) / (2.0 * a);

		res.setX1(x1);
		res.setX2(x2);

		return res;
	}

}
