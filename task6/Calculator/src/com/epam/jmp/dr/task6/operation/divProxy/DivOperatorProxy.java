package com.epam.jmp.dr.task6.operation.divProxy;

///Proxy pattern example
/**
 * Div proxy class
 *
 */
public class DivOperatorProxy implements IDivOperator {

	/**
	 * Div operator instance
	 */
	private DivOperator div;

	/**
	 * Constructor
	 */
	public DivOperatorProxy() {
		div = new DivOperator();
	}

	/**
	 * Returns 0 and prints error message if second operator is 0
	 */
	@Override
	public float getDiv(float a, float b) {
		if (b == 0) {
			System.err.println("Zero division!");
			return 0;
		}
		return div.getDiv(a, b);
	}

}
