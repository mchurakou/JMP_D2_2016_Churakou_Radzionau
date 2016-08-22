package com.epam.jmp.dr.task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.epam.jmp.dr.task6.operation.facade.OperationHelper;
import com.epam.jmp.dr.task6.operation.facade.OperationType;
import com.epam.jmp.dr.task6.operation.involutionAdaper.InvolutionAdapter;
import com.epam.jmp.dr.task6.operation.involutionAdaper.InvolutionAdapterImp;

public class Calculator {

	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter first vaiable: ");

		String str = "";

		try {
			str = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		float a = Float.valueOf(str);

		System.out.println("Enter operation (+,-,*,/,^ - involution): ");

		String operation = "";

		try {
			operation = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Enter second vaiable: ");

		try {
			str = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		float b = Float.valueOf(str);

		float result = 0;

		switch (operation) {
		case "+":
			result = OperationHelper.performOperation(OperationType.SUM, a, b);
			break;

		case "-":
			result = OperationHelper.performOperation(OperationType.DIFF, a, b);
			break;

		case "*":
			result = OperationHelper.performOperation(OperationType.MUL, a, b);
			break;

		case "/":
			result = OperationHelper.performOperation(OperationType.DIV, a, b);
			break;

		case "^":
			int pow = (int) Math.ceil(b);
			InvolutionAdapter invOperator = new InvolutionAdapterImp();
			switch (pow) {
			case 2:
				result = invOperator.getPow2(a);
				break;

			case 3:
				result = invOperator.getPow3(a);
				break;

			case 4:
				result = invOperator.getPow4(a);
				break;

			case 5:
				result = invOperator.getPow5(a);
				break;

			default:
				System.err.println("Power should not be greater than 5");

			}
			break;

		default:
			System.err.println("Unsupported operation");

		}

		System.out.println(a + " " + operation + " " + b + " = " + result);

	}

}
