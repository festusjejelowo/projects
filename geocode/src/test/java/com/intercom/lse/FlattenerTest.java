package com.intercom.lse;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FlattenerTest extends TestCase{

	/**
	* creates test case
	* @param name of test
	*/
	public FlattenerTest(String testName){
		super(testName);
	}

	/**
	* @return suite of tests being carried out
	*/
	public static Test suite(){
		return new TestSuite(FlattenerTest.class);
	}

	/**
	* tests for null arrays
	*/
	public void testNull(){
		assertTrue(Flattener.flatten(null) == null);
	}

	/**
	* tests if arbitrarily nested array is now flat
	*/
	public void testNestedArray(){

		System.out.println("2. testing arbitrarily nested array is flat");
		Object[] nested = new Object[]{new Object[]{1,2,new Object[]{3}},4};
		Integer[] flattened = Flattener.flatten(nested);

		System.out.println("\tinput  [[1,2,[3]],4]");
		System.out.println("\toutput "+Flattener.toString(flattened));
		assertTrue(Flattener.toString(flattened).equals("[1,2,3,4]"));
	}

	public void testMultipleObjectNestedArray(){

		System.out.println("1. testing to ensure only integers are flatened from a multi-nested array");
        System.out.println("\tinput  ['Damisi',87.23,64,12,[10,20,30,[12-12-2015 4:20,788]]]");
		Object[] nested = new Object[]{"Damisi",new Double(87.23),64,12,new Object[]{10,20,30,new Object[]{new java.util.Date(),788}}};
		Integer[] flatted = Flattener.flatten(nested);
		System.out.println("\toutput "+Flattener.toString(flatted)+"\n");
		assertTrue(Flattener.toString(flatted).equals("[64,12,10,20,30,788]"));
	}
}