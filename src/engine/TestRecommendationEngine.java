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
		r.showTopRecommendations();

		
	}

}
