package com.intercom.lse;
/**
* CustomerInviter
* @author Jejelowo B. Festus
* @author mail.festus@gmail.com
*
*  This class providess a utility for reading JSON-encoded Intercom customer
* from File. The application user can then search for and invite any customer
* a particular distamce e.g 100km from their Dublin office
* (GPS coordinates 53.3381985, -6.2592576) for some food and drinks
*/
import com.intercom.lse.vao.Customer;

import java.io.InputStream;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CustomerInviter{

   //gps coordinate 53.3381985, -6.2592576
   private static CustomerInviter instance;
   private static List<Customer> customers;

   /** earth radius in KM */
    public static final double EARTH_RADIUS = 6372.8d;

   public static CustomerInviter getInstance(){

      if(instance == null){
      	instance = new CustomerInviter();
	  }

	  return instance;

   }

   private CustomerInviter(){
	   load();
   }

   public List<Customer> findAll(){
	   return customers;
   }

   /**
   * searches the in-memory list of customer with a specific distance
   * from a referenced geographical point.
   *
   *@param longitude - double longitude of referenced location
   *@param latitude - double latitude of referenced location
   *@param radius - double distance within which search should be performed.
   */
   public List<Customer> search(double longitude,double latitude,double radius){

       List<Customer> found = new ArrayList<Customer>();
       for(Customer customer: customers){

		   if(distance(longitude,latitude,customer) <= radius){
			   found.add(customer);
		   }
	   }

	   if(found.size() == 0){
		   System.out.println("No customer found with the search perimiter");
	   }else{
		   print(found);
	   }

	   return found;
   }
   /**
   * calculates the distance between 2 geographic points
   * in this case from Intercom office and customer location
   *
   * @param longitude - double refrenced location logitude
   * @param latitude - double referenced location latitude
   * @param customer - Customer object being looked for
   */
   public double distance(double longitude,double latitude,Customer customer){

      if(customer == null) return 0.00d;

      double deltalat = Math.toRadians(customer.getLatitude() - latitude);
      double deltalog = Math.toRadians(customer.getLongitude() - longitude);

      double a = Math.pow(Math.sin(deltalat/2),2) +
                 Math.pow(Math.sin(deltalog/2),2) * Math.cos(latitude) * Math.cos(customer.getLatitude());
      double c = 2 * Math.asin(Math.sqrt(a));

      return EARTH_RADIUS * c;

   }

   /**
   * helper class to print list of customers
   *
   * @param records - list of customer objects
   */
   private void print(List<Customer> records){

	   Collections.<Customer>sort(records);
	   for(Customer customer: records){
		   System.out.println(customer.getId()+"\t"+customer.getName());
	   }
   }

   /**
   * loads the json-encoded customer records file bundled with this application
   * and store them as in-memory database using List
   */
   private static void load(){

	   System.out.println("loading customer data from file...");
	   customers = new ArrayList<Customer>();
	   String file = "gistfile1.txt";
	   JSONParser parser = new JSONParser();

	   try{

		   ClassLoader cl = Thread.currentThread().getContextClassLoader();
		   InputStream is = cl.getResourceAsStream(file);
		   Scanner scanner = new Scanner(is);

		   while(scanner.hasNextLine()){

			   String json = scanner.nextLine();
			   JSONObject obj = (JSONObject)parser.parse(json);

			   Customer customer = new Customer();
			   customer.setId(((Long)obj.get("user_id")).longValue());
			   customer.setName((String)obj.get("name"));
			   customer.setLatitude(Double.parseDouble((String)obj.get("latitude")));
			   customer.setLongitude(Double.parseDouble((String)obj.get("longitude")));
			   customers.add(customer);

		   }
	   }catch(Exception e){
		   e.printStackTrace();
	   }

	   System.out.println("loading completed with "+customers.size()+" records.");
   }

   public static void main(String[] args){

	   CustomerInviter cv = CustomerInviter.getInstance();
	   double longitude = -6.2592576;
	   double latitude = 53.3381985;
	   double radius = 100.0d;
	   cv.search(longitude,latitude,radius);
   }
}