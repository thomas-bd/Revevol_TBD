package revevol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RomanNumeralsConverter {
      
    //dictionary mapping the roman letters and their integer value
	private HashMap<String, Integer> symbolsTab;
    
    //constructor creating the dictionnary
	public RomanNumeralsConverter() {
        this.symbolsTab = new HashMap<String, Integer>();
        symbolsTab.put("I", 1);
        symbolsTab.put("V", 5);
        symbolsTab.put("X", 10);
        symbolsTab.put("L", 50);
        symbolsTab.put("C", 100);
        symbolsTab.put("D", 500);
        symbolsTab.put("M", 1000);
    }
    

    public HashMap<String,Integer> getSymbolsTab() {
        return this.symbolsTab;
    }

    public void setSymbolsTab(HashMap<String, Integer> map) {
        this.symbolsTab = map;
    }
    
           
    //returns true if romanString is a correct roman numerals expression
    public boolean checkSyntax(String romanString) {
        boolean result = false;
        if (romanString != null && !romanString.isEmpty()) {
            /*regex defining the proper syntax of roman numerals 
             * note: unlimited M, and a maximum of 1 D/L/V and 3 I/X/C are allowed 
             */             
        	String regex = "^(M*(CM|CD|(D?C{0,3}))(XC|XL|(L?X{0,3}))(IX|IV|(V?I{0,3})))$";
            result = romanString.matches(regex);
        }
        return result;
    }

         
    
    //browses romanString and returns an ArrayList with corresponding integer values 
     public List<Integer> translateToInts(String romanString) {
        String currentChar = null; 
        int currentVal = 0;
        List<Integer> values = new ArrayList<Integer>();        
        for (int i=0; i<romanString.length(); i++){
            currentChar = Character.toString(romanString.charAt(i));            
            currentVal = this.getSymbolsTab().get(currentChar);            
            values.add(currentVal); 
        }        
        return values;        
    }
     
    
    //main algorithm: does the additions/subtractions recursively and returns the corresponding arabic result    
    public int calculate(List<Integer> values) {
        int result = 0;        
        if (values.size() == 1){
        	result = values.get(0);
        }
        else{
        	List<Integer> nextValues = (List<Integer>)values.subList(1, values.size());        	       
        	//any number gets subtracted from the following if the next one is strictly greater (ex: IV => 5 - 1) 
        	if(values.get(0) < values.get(1)){        
        		result = this.calculate(nextValues) - values.get(0);
        	}
        	//any number is added to the following if the next one is less or equal (ex: VI => 5 + 1) 
        	if (values.get(0) >= values.get(1)){
        		result = values.get(0) + this.calculate(nextValues);
        	}
        }
        return result; 
    }
    
    
    /*translates romanString into its arabic-numerals equivalent.
     * note: returns -1 if romanString is empty/null/syntaxically wrong  
     */
    public int convertRomanToArabic(String romanString) {
        //gets rid of any potential white spaces at the extremities of romanString 
    	romanString = romanString.trim();
    	int result = -1;
    	if (checkSyntax(romanString)){
        	List<Integer> number = this.translateToInts(romanString);
        	result = this.calculate(number);
        }
    	return result;
    }
    
            
    
}