import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * ReadFile.class is the class that does the reading of the given text file as well as the calculations of the word occurrences
 * This class uses two methods and one main method that process the whole calculation.
 * 
 * @author Jan
 *
 */

public class ReadFile {
	/**
	 * This is the method that utilizes the two others methods in this class
	 * The important of this method is the focus of it being an arraylist as well as condensing code reading
	 * 
	 * @param fileName this will be inputed as string from the user selected textfile
	 * @param fileWords filewords is a hashmap placing string as its key this will be used to record the data, order the data, and pass to the array
	 * @param array this array will then be used and return. Array was choosen for easy handling on Filechooser.class gui components.
	 * @return the array will be return to the Filechooser.class once it contains the correct data 
	 */
	public static ArrayList<String> fileReader(String fileName, Map<String, Integer> fileWords, ArrayList<String> array)
	{
		File checker = new File(fileName);
        	//methods
          	wordCount(checker, fileWords);
           	array = sortWordFreq(fileWords);
		return array;
	}
	
	/**
	 * This method takes the file and scans the file for words limited by the delimiter 
	 * While the scanning is occurring the word is counted and checks if the word had repeated or not.
	 * @param fileName this is textfile that will be passed through the method to be used scanned and read 
	 * @param fileWords is a hashmap that places String as the key this allows to keep track of the words that have repeated. 
	 * @return Once the fileName has been fully scanned the return will be fileWords key and values to the main method.
	 * 
	 */
	
	public static Map<String, Integer> wordCount(File fileName, Map<String, Integer> fileWords)
	    {
	    	Scanner file;//setting up text file to be scanned and as well as passing values
			try {
				file = new Scanner(fileName);
				file.useDelimiter("[^a-zA-Z]+");//limiting string to alphabets 
		    	while (file.hasNext())//while the text file contains a token it moves on until it reaches none
		    	{
		    		
		    		String tempPasser = file.next();//passing the scanner token that it encounter into the string
		    		
		    		String trackingWords = tempPasser.toLowerCase();//passing string once all of the string has been lower cased
		    		Integer countWords = fileWords.get(trackingWords);//key and integer is passed but integer is recorded
		    		if(countWords != null)//once integer is passed checks if its null
		    		{
		    			countWords++;//if not null integer goes up by 1 
		    			//this happens when key if a known key is passed and already has a value
		    		}
		    		else
		    		{
		    			//if key is null it gets 1 to represent its first iteration
		    			countWords = 1;
		    		}
		    		fileWords.put(trackingWords, countWords);//key and value gets recorded and loop goes again
		    	}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return fileWords;
	    }

	/**
	 *sortWordFreq is the next method that organizes the occurrences by greatest to least by comparing each keys
	 *Once done so the hashmap will then be passed into a ArrayList.
	 *
	 * @param fileWords this is a hashmap that contains data recorded from the wordCount method
	 * @return once fileWords is passed into the Arraylist the arraylist will then be returned 
	 */
	public static ArrayList<String> sortWordFreq(Map<String,Integer> fileWords)
	    {
	    	//this creates a set containing the same elements as the hashmap as well as passing the set into the list
	    	List<Entry<String, Integer>> passingList = new LinkedList<Entry<String, Integer>>(fileWords.entrySet());
			ArrayList<String> orderArray = new ArrayList<String>();
	    	//using collection sorts allows the sorting of the passingList and as well as using the comparator interface as a anonymous class
	    	//we can then compare two objects of the set and return the sorted elements into the passingList.
	    	Collections.sort(passingList, new Comparator<Entry<String, Integer>>()
	    			{

						@Override
						public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) 
						{
							// TODO Auto-generated method stub
							return o2.getValue().compareTo(o1.getValue());
						}
	    		
	    			});
	    	
	    	int tempIndex = 0;//temp value for limiting list
	    	
	    	//initially I was going to transfer the list back to the map but if we can just using the sorted list 
	    	//to show the ordered items then i think its fine
	    	for(Entry<String,Integer> e : passingList)//enchanced for loop
	    	{
	    		tempIndex++;
	    		if(tempIndex <= 20)//this will be printed until the temp variable reaches past 21
	    		{
	    			if(tempIndex == 1) {
		    			String getStringText =e.getKey() + " " + e.getValue();
		    			orderArray.add(getStringText);
	    			}
	    			else {
		    			String getStringText =e.getKey() + " " + e.getValue();
		    			orderArray.add(getStringText);
	    			}
	    		}
	    		else
	    		{
	    			//System.out.println(orderArray);
	    			
	    			return orderArray;
	    		}
	    	}
			return orderArray;
	    	
	    }
}
