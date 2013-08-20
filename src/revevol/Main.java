/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package revevol;


/**
 *
 * @author NQJB7966
 */
public class Main {
 
     public static void main(String[] args) {
    	 //Creates a converter
    	 RomanNumeralsConverter converter = new RomanNumeralsConverter();   
         //String to be converted (must be a proper roman numerals expression without spaces)
    	 String romanNumber = "MCDXIV ";
         
    	 int arabicNumber = converter.convertRomanToArabic(romanNumber);
         System.out.println(arabicNumber);               
	}
    
}
