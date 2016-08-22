package com.epam.jmp.dr.task6.operation.facade;

import com.epam.jmp.dr.task6.operation.DiffOperator;
import com.epam.jmp.dr.task6.operation.MulOperator;
import com.epam.jmp.dr.task6.operation.SumOperator;
import com.epam.jmp.dr.task6.operation.divProxy.DivOperatorProxy;
import com.epam.jmp.dr.task6.operation.divProxy.IDivOperator;

///Facade pattern example
/**
 *	OperationHelper class
 *
 */
public class OperationHelper {

	/**
	 * Performs operation, dependent of given OperationType
	 * @param type - operation type
	 * @param a - first operand
	 * @param b - second operand
	 * @return result of operation specified by OperationType
	 */
	public static float performOperation(OperationType type, float a, float b) {
		switch (type) {
		case DIFF:
			DiffOperator diff = new DiffOperator();
			return diff.getDiff(a, b);

		case DIV:
			IDivOperator div = new DivOperatorProxy();
			return div.getDiv(a, b);

		case MUL:
			MulOperator mul = new MulOperator();
			return mul.getMul(a, b);

		case SUM:
			SumOperator sum = new SumOperator();
			return sum.getSum(a, b);

		default:
			return 0;

		}
	}

}
