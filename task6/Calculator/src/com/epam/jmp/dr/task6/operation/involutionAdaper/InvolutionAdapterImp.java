package com.epam.jmp.dr.task6.operation.involutionAdaper;

import com.epam.jmp.dr.task6.operation.SqrOperator;

/// Adapter pattern
/**
 * InvolutionAdapterImp class
 *
 */
public class InvolutionAdapterImp extends SqrOperator implements InvolutionAdapter {

	@Override
	public float getPow3(float a) {
		float result = getSqr(a);
		return result * a;
	}

	@Override
	public float getPow4(float a) {
		float result = getSqr(a);
		return result * a * a;
	}

	@Override
	public float getPow5(float a) {
		float result = getSqr(a);
		return result * a * a * a;
	}

	@Override
	public float getPow2(float a) {
		return getSqr(a);
	}

}
