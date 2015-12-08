package com.intercom.lse;
/**
* <b>Flatener.java</b>
* @author - Jejelowo .B. Festus
* @author - mail.festus@gmail.com
*
* <p>
* A utility class to flatten any arbitrarily nested Integer array to a flat array of integers<br>
* by calling the flatten method.<br>
*
* e.g [1,2,[3]],4] -> [1,2,3,4]
*
* <pre>
*   Object[] d = new Object[]{1,2,new Object[]{3},4};
*   Integer[] normalized = Flattener.flatten(d);
* </pre>
* </p>
*/
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Flattener{

	/**
	* This method flattens an an arbitrarily nested Integer array to a flat array of Integers.
	* e.g [1,2,[3],4] -> [1,2,3,4]
	*
	* @param nested Object array of integers
	* @return Array of integers that have been flattened.
	*/
	public static Integer[] flatten(Object[] nested){

		if(nested == null) return null;
		List<Integer> flattened = flat(nested);
		return (Integer[])flattened.toArray(new Integer[flattened.size()]);
	}

    public static List<Integer> flat(Object[] data){

         List<Integer> normalized = new ArrayList<Integer>();
         for(Object o: data){


			 if(o instanceof Integer){
				 normalized.add((Integer)o);
			 }else if(o.getClass().isArray()){
				normalized.addAll(flat((Object[])o));
			 }
		 }
       return normalized;
    }

    /**
    * Helper method to convert flattened array to string
    *
    * @param arrays of flat Integer arrays
    * @return String of flattened array
    */
    public static String toString(Object[] arrays){

		StringBuffer sb = new StringBuffer("[");
		for(Object o:arrays){
			sb.append(o.toString());
			sb.append(",");
		}

		sb.delete(sb.length()-1,sb.length());
		sb.append("]");
		return sb.toString();
	}

	public static void print(Object[] arrays){
		System.out.println(toString(arrays));
	}

    public static void main(String[] args){

		Object[] d = new Object[]{new Object[]{1,2,new Object[]{3}},4};
		Integer[] flat = Flattener.flatten(d);
		print(flat);
    }
}