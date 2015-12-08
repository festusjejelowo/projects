package com.intercom.lse.vao;
import java.io.Serializable;

public class Customer implements Serializable,Comparable<Customer>{

   private long id;
   private String name;
   private double longitude;
   private double latitude;

   public Customer(){}

   public Customer(long id,String name,double latitude,double longitude){

	   this.id = id;
	   this.name = name;
	   this.longitude = longitude;
	   this.latitude = latitude;
   }

   public long getId(){
	   return this.id;
   }

   public void setId(long id){
	   this.id = id;
   }

   public String getName(){
	   return this.name;
   }

   public void setName(String name){
	   this.name = name;
   }

   public double getLatitude(){
	   return this.latitude;
   }

   public void setLatitude(double latitude){
	   this.latitude = latitude;
   }

   public double getLongitude(){
	   return this.longitude;
   }

   public void setLongitude(double longitude){
	   this.longitude = longitude;
   }

   @Override
   public boolean equals(Object o){
	   return ((o instanceof Customer) &&
		      (((Customer)o).getId() == this.getId())
		      )?true:false;
   }

   @Override
   public int hashCode(){
	   return (int)(17 * id) * name.hashCode();
   }

   @Override
   public int compareTo(Customer other){
	   return (this.getId() < other.getId())?-1:1;
   }
}