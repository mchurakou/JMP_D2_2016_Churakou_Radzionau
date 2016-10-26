package com.epam.jmp.dr.task16.calculator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculatorTest {

	@Test
	public void testCalculate_whenPositiveDiscriminantThenCalculateResult() throws Exception {

		double a = 2;
		double b = 5;
		double c = 3;

		Result res = Calculator.calculate(a, b, c);

		double x = res.getX1();

		assertEquals(0.0, x * x * a + x * b + c, 0);

		x = res.getX2();
		assertEquals(0.0, x * x * a + x * b + c, 0);

	}

	@Test
	public void testCalculate_whenAValueIsZeroThenCalculateResult() throws Exception {

		double a = 0;
		double b = 5;
		double c = 3;

		Result res = Calculator.calculate(a, b, c);

		double x = res.getX1();

		assertEquals(0.0, x * x * a + x * b + c, 0);

		x = res.getX2();
		assertEquals(0.0, x * x * a + x * b + c, 0);

	}

	@Test(expected = ArithmeticException.class)
	public void testCalculate_whenBValueIsZeroAndResultIsComplexValueThenThrowArithmeticException() throws Exception {

		double a = 5;
		double b = 0;
		double c = 3;

		Calculator.calculate(a, b, c);

	}

	@Test
	public void testCalculate_whenBValueIsZeroAndResultIsNotComplexValueThenCalculateResult() throws Exception {

		double a = 8;
		double b = 0;
		double c = -2;

		Result res = Calculator.calculate(a, b, c);

		double x = res.getX1();

		assertEquals(0.0, x * x * a + x * b + c, 0);

		x = res.getX2();
		assertEquals(0.0, x * x * a + x * b + c, 0);

	}

	@Test
	public void testCalculate_whenCValueIsZeroAndResultIsNotComplexValueThenCalculateResult() throws Exception {

		double a = 8;
		double b = 3;
		double c = 0;

		Result res = Calculator.calculate(a, b, c);

		double x = res.getX1();

		assertEquals(0.0, x * x * a + x * b + c, 0);

		x = res.getX2();
		assertEquals(0.0, x * x * a + x * b + c, 0);

	}

	@Test(expected = ArithmeticException.class)
	public void testCalculate_whenDiscriminantIsLessThanZeroThenThrowArithmeticException() throws Exception {

		double a = 8;
		double b = 2;
		double c = 2;

		Calculator.calculate(a, b, c);

	}

	@Test(expected = ArithmeticException.class)
	public void testCalculate_whenAllParametersAreZeroThenThrowArithmeticException() throws Exception {

		double a = 0;
		double b = 0;
		double c = 0;

		Calculator.calculate(a, b, c);

	}

	@Test(expected = ArithmeticException.class)
	public void testCalculate_whenAandBParametersAreZeroThenThrowArithmeticException() throws Exception {

		double a = 0;
		double b = 0;
		double c = 15;

		Calculator.calculate(a, b, c);

	}

}
