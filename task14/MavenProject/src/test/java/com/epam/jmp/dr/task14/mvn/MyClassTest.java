package com.epam.jmp.dr.task14.mvn;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for MyClass
 */
public class MyClassTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
	public MyClassTest(String testName)
	{
		super(testName);
	}
	
	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MyClassTest.class );
    }
    
    public void testGetStrProp() {
    	MyClass myClass = new MyClass("Test", 42, true);
    	assertEquals("Test", myClass.getStrProp());
    }
    
    public void testGetIntProp()
    {
    	MyClass myClass = new MyClass("Test", 42, true);
    	assertEquals(42, myClass.getIntProp());
    }
    
    public void testIsBoolProp()
    {
    	MyClass myClass = new MyClass("Test", 42, true);
    	assertEquals(true, myClass.isBoolProp());
    }

}
