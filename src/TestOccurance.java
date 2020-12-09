import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;


class TestOccurance {
	
	@Test
	public void testWordCount() {
		//variables
		Map<String, Integer> fileWordsExpected = new HashMap<String, Integer>();
		Map<String, Integer> fileWordsActual = new HashMap<String, Integer>();	
		ClassLoader classLoader = TestOccurance.class.getClassLoader();
		//This only works if you have the poem in the BIN folder WHERE THE CLASSES ARE STORED IN
		File fileChecker = new File(classLoader.getResource("module2-poem.txt").getFile());
		
		//methods
		fileWordsExpected = wordCountM(fileChecker, fileWordsExpected);
		fileWordsActual = ReadFile.wordCount(fileChecker, fileWordsActual);
		
		//assert
		assertEquals(fileWordsExpected, fileWordsActual);
		
	}
	@Test
	public void testSortWordFreq() {
		//same variables from Test1 - NO CHANGES
		Map<String, Integer> fileWordsExpected = new HashMap<String, Integer>();
		Map<String, Integer> fileWordsActual = new HashMap<String, Integer>();	
		ClassLoader classLoader = TestOccurance.class.getClassLoader();
		File fileChecker = new File(classLoader.getResource("module2-poem.txt").getFile());
		
		//additional variables - CHANGES
		ArrayList<String> orderArrayExpected = new ArrayList<String>();
		ArrayList<String> orderArrayActual = new ArrayList<String>();
		
		
		//methods - CHANGES
		orderArrayExpected = sortWordFreqM(wordCountM(fileChecker, fileWordsExpected));
		orderArrayActual = ReadFile.sortWordFreq(ReadFile.wordCount(fileChecker, fileWordsActual));

		//assert
		assertEquals(orderArrayExpected, orderArrayActual);
		
	}
	
	@Test
	public void testfileReader() {
		//same variables from previous test NO CHANGES
		Map<String, Integer> fileWordsExpected = new HashMap<String, Integer>();
		Map<String, Integer> fileWordsActual = new HashMap<String, Integer>();	
		ClassLoader classLoader = TestOccurance.class.getClassLoader();
		File fileChecker = new File(classLoader.getResource("module2-poem.txt").getFile());
		ArrayList<String> orderArrayExpected = new ArrayList<String>();
		ArrayList<String> orderArrayActual = new ArrayList<String>();
		
		//NEW VARIABLE - CHANGES
		String fileName = fileChecker.getAbsolutePath();
		
		//methods - NO CHANGES
		orderArrayExpected = sortWordFreqM(wordCountM(fileChecker, fileWordsExpected));
		//method - CHANGES
		orderArrayActual = ReadFile.fileReader(fileName, fileWordsActual, orderArrayActual);
		
		//assert
		assertEquals(orderArrayExpected, orderArrayActual);
	}
	

	//methods
	public static Map<String, Integer> wordCountM(File fileName, Map<String, Integer> fileWords) {
		Scanner file;//setting up text file to be scanned and as well as passing values
		try {
			file = new Scanner(fileName);
			file.useDelimiter("[^a-zA-Z]+");
	    	while (file.hasNext())
	    	{
	    		
	    		String tempPasser = file.next();
	    		
	    		String trackingWords = tempPasser.toLowerCase();
	    		Integer countWords = fileWords.get(trackingWords);
	    		if(countWords != null)
	    		{
	    			countWords++;
	    		}
	    		else
	    		{
	    			countWords = 1;
	    		}
	    		fileWords.put(trackingWords, countWords);
	    	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileWords; 
	}

	public static ArrayList<String> sortWordFreqM(Map<String,Integer> fileWords)
    {
    	List<Entry<String, Integer>> passingList = new LinkedList<Entry<String, Integer>>(fileWords.entrySet());
		ArrayList<String> orderArray = new ArrayList<String>();
    	Collections.sort(passingList, new Comparator<Entry<String, Integer>>()
    			{

					@Override
					public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) 
					{
						// TODO Auto-generated method stub
						return o2.getValue().compareTo(o1.getValue());
					}
    		
    			});
    	
    	int tempIndex = 0;
    	for(Entry<String,Integer> e : passingList)
    	{
    		tempIndex++;
    		if(tempIndex <= 20)
    		{
    			if(tempIndex == 1) {
	    			String getStringText =e.getKey() + " " + e.getValue();
	    			orderArray.add(getStringText);
    			}
    			else {
	    			String getStringText ="\n" + e.getKey() + " " + e.getValue();
	    			orderArray.add(getStringText);
    			}
    		}
    		else
    		{
    			return orderArray;
    		}
    	}
		return orderArray;
    }
}


