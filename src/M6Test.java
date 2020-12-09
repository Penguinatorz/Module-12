import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;

/*
 * Jancarlo Sevilla
 * 10/17/2020
 * Module-6-UI Design
 * 
 */


public class M6Test {
	
	/**
	 * This is the main method. It runs the application running the Filechooser.class
	 * This displays a menu for the user to choose a text file and once the program is run
	 * A pop-up window will detail the number of occurrences of words from greatest to least 
	 * 
	 * @param args Filechooser class is run in the M6Test class.
	 */

	public static void main(String args[]) 
	{ 
		
		//---------------tester
		 //URL url = M6Test.class.getClassLoader().getResource("module2-poem.txt");
	      //System.out.println("Value = " + url);
		
		
		
		//--------------database
		
		//WordDBTB word1 = new WordDBTB("the", 1);
		//WordDBTB word2 = new WordDBTB("test", 1);
		//WordDBTB word3 = new WordDBTB("this", 1);
		//WordDBTB word4 = new WordDBTB("a", 1);
		
		//getConnection();
		
		//Database and table creation/deletion
		createDatabase();
		deleteTable();
		createTable();
		
		//inserting
		//insertWord(word1);
		//insertWord(word2);
		//insertWord(word3);
		//insertWord(word4);
		
    	String str = "C:\\Users\\blast\\Desktop\\Stuff\\Homework\\CEN-3024C\\MODULE-10\\module2-poem.txt";
		Map<String, Integer> fileWords = new HashMap<String, Integer>();
		ArrayList<String> orderArray = new ArrayList<String>();
		orderArray = ReadFile.fileReader(str, fileWords, orderArray);
		
		insertWordArray(orderArray);
		
		//prinintg data
		for(WordDBTB w: selectALL())
		{ 
			System.out.println(w); 
		}
		
		//-----------FileChoosing menu
		//Application.launch(Filechooser.class, args);
		 

	} 
	
	public static Connection getConnection(){
		Connection c = null;
		boolean canConnect = false;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/word_occurrences";
			String username = "root";
			String password = "Password";
			Class.forName(driver);
			
			c = DriverManager.getConnection(url, username, password);
			canConnect = true;
		} catch (Exception e) {
			//System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.out.println(">>Can't connect to database creating new database.\n");
		}
		if(!canConnect) {
			try {
				String driver = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306";
				String username = "root";
				String password = "Password";
				Class.forName(driver);
				
				c = DriverManager.getConnection(url, username, password);
				canConnect = true;
			} catch (Exception e) {
				//System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.out.println(">> Failed to connect - closing now");
				System.exit(0);
			}
		}
		System.out.println("Opened database successfully");

		return c;
	}
	
	public static Connection createDatabase() {
		//method to set the database up
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();

			statement = connection.createStatement();

			String sql = "CREATE DATABASE IF NOT EXISTS word_occurrences ";

			statement.executeUpdate(sql);
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Database created successfully");
		return connection;
	}
	
	public static Connection createTable() {
		//method to set the table up
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();

			statement = connection.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS word" 
					+ "(ID INTEGER PRIMARY KEY        AUTO_INCREMENT, "
					+ " word_Value                Char(50) NOT NULL," 
					+ "occurrences           Char(50)   NOT NULL)";

			statement.executeUpdate(sql);
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
		return connection;
	}
	
	public static Connection deleteTable() {
		//method to delete table
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			
			String sql = "DROP TABLE word";
			statement.executeUpdate(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection insertWord(WordDBTB word) {
		//method on inserting data
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();

			connection.setAutoCommit(false);

			//data gets inserted
			statement = connection.createStatement();
			String sql = "INSERT INTO word (word_value,occurrences) " 
					+ "VALUES ("
					+ "' "
					+ word.getWord_value()
					+ "'," 
					+ word.getOccurrences() 
					+ " );";
			statement.executeUpdate(sql);

			// System.out.println("Worked!!!");

			statement.close();
			connection.commit();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println(">> Inserted Word - " + word.getWord_value()
				+ " - record created successfully\n");
		return connection;
	}
	
	public static Connection insertWordArray(ArrayList<String> array) {
		//method on inserting data
		ArrayList<String> passingList = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();

			connection.setAutoCommit(false);
			String sql = "INSERT INTO word (word_value, occurrences) VALUES " + 
			        "(?,?)";
			statement = connection.prepareStatement(sql);
			//data gets inserted
			String str1[] = new String[	array.size()];
			
			for(int j = 0; j < array.size(); j++) {
				str1[j] = array.get(j);
				String temp = str1[j];
				String[] sp = temp.split(" ");
				statement.setString(1, sp[0]);
				statement.setString(2, sp[1]);
				statement.execute();
			}
			
			// System.out.println("Worked!!!");

			statement.close();
			connection.commit();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println(">> record created successfully\n");
		return connection;
	}
	
	public static ArrayList<WordDBTB> selectALL() {
		//array method 
		Connection connection = null;
		Statement statement = null;

		ArrayList<WordDBTB> wordArray = new ArrayList<>();

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM word;");
			System.out.println(">> Returning all data in word ------------");
			while (rs.next()) {//getting data from table and placing into array once done the return statement will display the text
				WordDBTB word1 = new WordDBTB(rs.getString("word_value"), 
						rs.getInt("occurrences"), rs.getInt("ID"));
				wordArray.add(word1);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return wordArray;

	}
}
