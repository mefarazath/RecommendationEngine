package dataHandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.text.AbstractDocument.BranchElement;

import entities.Rating;

public class DataHandler {
	
	//file names required
	private String mappingFile;
	private String ratingsFile;
	
	//hashmap for mapping id to name
	private HashMap<Integer, String> map;
	//hashmap for reverse mapping
	private HashMap<String,Integer> rmap;
	//list to store the user ratings
	private ArrayList<Rating> ratings;
	//map to store the ratings based on the user 
	private HashMap<Integer,ArrayList<Rating>> userRatings;
	
	public DataHandler(){
		initialize();
		map = new HashMap<Integer,String>();
		rmap = new HashMap<>();
		ratings = new ArrayList<Rating>();
		userRatings = new HashMap<>();
	}

	//initializing the data handler
	public void initialize(){
		Properties prop = new Properties();
		try {
			//load the filenames using the properties file
	        prop.load(new FileInputStream("settings.properties"));
	        
	        //set the file names
	        mappingFile = prop.getProperty("mappingFile","u.item");
	        ratingsFile = prop.getProperty("ratingsFile","u.data");
        
		} catch (FileNotFoundException e) {
        	System.err.println("Properties File not found");
	        e.printStackTrace();
        
		} catch (IOException e) {
	        System.err.println("Input/Output Error");
	        e.printStackTrace();
        }
	}
	
	
	
	public String getMappingFile() {
		return mappingFile;
	}


	public String getRatingsFile() {
		return ratingsFile;
	}



	//method to create the dictionary which maps unique identifier to the name of the entity
	public void createMappingDictionary(){
		
		try {
	        BufferedReader br = new BufferedReader(new FileReader(mappingFile));
	        String line = br.readLine();
	        
	        while(line != null){
    	        //split the line and assign data to the map
    	        String data[] = line.split("\\|");
    	        int key = Integer.parseInt(data[0].trim());
    	        String value = data[1];
    	        
    	        //put the mapping into the dictionary
    	        map.put(key, value);
    	        rmap.put(value,key);

    	        line = br.readLine();
	        }
	        
        } catch (FileNotFoundException e) {
	        e.printStackTrace();
        } catch (IOException e) {
	        e.printStackTrace();
        }
		
	}
	
	
	public HashMap<Integer,String> getMappingDictionary(){
		//create the mapping dictionary
		this.createMappingDictionary();
		
		return map;
	}
	
	public HashMap<String,Integer> getReverseMappingDictionary(){
		//create the mapping dictionary
		this.createMappingDictionary();
		
		return rmap;
	}
	
	//method to add the ratings to a list 
	public void createRatings(){
		
		try {
	        BufferedReader br = new BufferedReader(new FileReader(ratingsFile));
	        
	        String line = br.readLine();
	        int count=1;
	        while(line != null){
    	        //split the line and assign data to the map
    	        String data[] = line.split("\t");
    	        
    	        //parse the values
    	        int userId = Integer.parseInt(data[0].trim());
    	        int ratedEntityId = Integer.parseInt(data[1].trim());
    	        double rating = Double.parseDouble(data[2].trim());
    	        
    	        //create the rating object
    	        Rating r = new Rating(userId, ratedEntityId, rating);
    	        ratings.add(r);
    	        
    	        line = br.readLine();
    	        count++;
	        }
	        
	      //  System.out.println(count+"  "+ratings.size());
        } catch (FileNotFoundException e) {
	        System.err.println("File Not Found");
	        e.printStackTrace();
        } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
	}
	
	//method to return the ratings list
	public ArrayList<Rating> getRatings(){
		//add ratings to the list 
		this.createRatings();
		return ratings; 
	}
	
	//method to categorize the ratings based on the user
	public void createUserRatings(){
		
		//add the ratings to the list
		this.createRatings();
		
		ArrayList<Rating> temp;
		
		for (int i = 0; i < ratings.size(); i++) {
	        Rating r = ratings.get(i);
			int userId = r.getUserId();
			//a new user
			if(!userRatings.containsKey(userId)){
				temp = new ArrayList<>();
			}
			//already included user
			else{
				temp = userRatings.get(userId);
			}
			
			//add the rating to the list
			temp.add(r);
			//put the list of ratings for the userId
			userRatings.put(userId,temp);
        }		
	}
	
	//method to get the user ratings
	public HashMap<Integer,ArrayList<Rating>> getUserRatings(){
			// create the user ratings map
			this.createUserRatings();
			return userRatings;
	}
	
}
