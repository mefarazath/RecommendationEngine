package engine;

import java.util.HashMap;
import java.util.Iterator;

import dataHandler.DataHandler;

public class TestRecommendationEngine {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//test the data handler
		DataHandler d = new DataHandler();
		//System.out.println(d.getMappingFile());
		//System.out.println(d.getRatingsFile());
		
		//d.createUserRatings();
		RecommendationEngine r = new RecommendationEngine();
	
		//String name = "Indiana Jones and the Last Crusade (1989)";
		//show the topN recommendations for topN a given entity
		//r.showRecommendations(name);
		
		
		//show topN for all the entities
		r.showTopRecommendations();
		
	}

}
