package com.epam.jmp.dr.task6.operation.divProxy;

public class DivOperatorProxy implements IDivOperator {
	
	private DivOperator div;
	
	public DivOperatorProxy()
	{
		div = new DivOperator();
	}

	@Override
	public float getDiv(float a, float b) {
		if(b == 0)
		{
			return 0;
		}
		return div.getDiv(a, b);
	}

}
