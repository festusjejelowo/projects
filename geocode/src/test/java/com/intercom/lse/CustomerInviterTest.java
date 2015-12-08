package com.intercom.lse;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import com.intercom.lse.vao.Customer;

/**
* Unit test class for CustomerInviter class\
*/
public class CustomerInviterTest extends TestCase{

	final CustomerInviter iv = CustomerInviter.getInstance();

	public CustomerInviterTest(String name){
		super(name);
	}

	public static Test suite(){
		return new TestSuite(CustomerInviterTest.class);
	}


    /**
    * test to see that data is correctly loaded upon class initialization
    */
	public void testHasCustomers(){
		assertTrue(iv.findAll().size() > 0);
	}

    /**
    * search test for customer within 100km from Intercom's office
    */
	public void testSearchWithinRadius(){

	   double longitude = -6.2592576;
	   double latitude = 53.3381985;
	   double radius = 100.0d;
	   List<Customer> invitees = iv.search(longitude,latitude,radius);
	   assertTrue(invitees.size() > 0);
	}
}