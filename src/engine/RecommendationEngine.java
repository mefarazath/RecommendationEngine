package engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import dataHandler.DataHandler;

import entities.EntityPair;
import entities.Rating;
import entities.Recommendation;
import entities.RecommendationComparator;



public class RecommendationEngine {
	
	// hash map to store the ratings based on user
	private HashMap<Integer,ArrayList<Rating>> userRatings;
	//hash map to store the corratings
	private HashMap<String,EntityPair> coratings;
	//hash map to store the recommendation for each entitiy
	HashMap<Integer,ArrayList<Recommendation>> rec;
	
	private DataHandler handler;
	
	public RecommendationEngine(){
			handler = new DataHandler();
			userRatings = handler.getUserRatings();
			coratings = new HashMap<>();
			rec = new HashMap<>();
	}
	//for each user	create pairs of corrated movies and accumilate data required
	// for calculating the correlation coefficient of the moview pair
	public void buildCoratings(){
			
			//iterate for each user
			for(int userId : userRatings.keySet()){
				//get the ratings of the user
				ArrayList<Rating> r = userRatings.get(userId);
				
				//for each list of ratings compute the base data for each pair of ratedEntities
				for (int i = 0; i < r.size()-1; i++)
				{
	                
						for (int j = i+1; j < r.size(); j++) 
						{
	                        	//get the ratings to create the pairs ie. create entity pair and ratings pair
								Rating r1 = r.get(i);
								Rating r2 = r.get(j);
								
								EntityPair entityPair = null;
								String key = r1.getRatedEntityId()+","+r2.getRatedEntityId();
								
								//check whether pair exists and if so get the entitypair object
								if(coratings.containsKey(key))
								{
									entityPair = coratings.get(key);
								}
								//else create a new object
								else
								{
									entityPair = new EntityPair(r1.getRatedEntityId(), r2.getRatedEntityId());
								}
								
								//update the base data required for correlation coefficient
								int numberOfCorratings = entityPair.getNumberOfCorratings()+1;
								double ratingSum1 = entityPair.getRatingSum1()+r1.getRating();
								double ratingSum2 = entityPair.getRatingSum2()+r2.getRating();
								double squareSum1 = entityPair.getRatingSquareSum()+(r1.getRating()*r1.getRating());
								double squareSum2 = entityPair.getRatingSquareSum2()+((r2.getRating()*r2.getRating()));
								double dotProductSum = entityPair.getDotProduct()+(r1.getRating()*r2.getRating());
								
								entityPair.setNumberOfCorratings(numberOfCorratings);
								entityPair.setRatingSum1(ratingSum1);
								entityPair.setRatingSum2(ratingSum2);
								entityPair.setRatingSquareSum(squareSum1);
								entityPair.setRatingSquareSum2(squareSum2);
								entityPair.setDotProduct(dotProductSum);
								
								coratings.put(entityPair.toString(),entityPair);
	                        
                        }
	                
                }
						
				
//						for(String key:coratings.keySet()){
//								if(coratings.get(key).getNumberOfCorratings()>=75)
//								System.out.println(key+" : "+coratings.get(key).getNumberOfCorratings());
//						}
				
			}
			
	}
	
	//build the recommendations
	public void buildRecommendations(int minimumCoratings){
		
		// build the corating
		this.buildCoratings();
		
		for(String key:coratings.keySet())
		{
			//split the key ie: entity1Id,entity2Id
			String entityIds[] = key.split(",");
			int entity1 = Integer.parseInt(entityIds[0].trim());
			int entity2 = Integer.parseInt(entityIds[1].trim());
			
			EntityPair pair = coratings.get(key);
			
			if(pair.getNumberOfCorratings() >= minimumCoratings)
			{
				// get the numerator for the correlation coefficient
				double num = pair.getNumberOfCorratings()*pair.getDotProduct() - pair.getRatingSum1()*pair.getRatingSum2();
				// get the denominator for the correlation cofficient
				double d1 = Math.sqrt(pair.getNumberOfCorratings()*pair.getRatingSquareSum() - Math.pow(pair.getRatingSum1(),2));
				double d2 = Math.sqrt(pair.getNumberOfCorratings()*pair.getRatingSquareSum2()- Math.pow(pair.getRatingSum2(), 2));
				double den = d1*d2;
				// calculate the correlation coefficient
				double cValue = num/den;
				
				ArrayList<Recommendation> temp1 = new ArrayList<>();
				ArrayList<Recommendation> temp2 = new ArrayList<>();
				
				if(rec.containsKey(entity1)){
					temp1 = rec.get(entity1);
				}
				if(rec.containsKey(entity2)){
					temp2 = rec.get(entity2);
				}
				
				//add the recommendations to the recommendation lists of each entity
				temp1.add(new Recommendation(entity2, cValue));
				temp2.add(new Recommendation(entity1, cValue));
				
				//add the entities to the recommendation map {entity -> recommendation} 
				rec.put(entity1,temp1);
				rec.put(entity2, temp2);
				
			}		
		}		
	}
	
	//method to show n recommendations for each entity
	public void showTopRecommendations(int topN){
				//build the recommendation dictionary
				this.buildRecommendations(50);
		
				//iterate for each entity
				for(int id: rec.keySet())
				{
					String name = this.handler.getMappingDictionary().get(id);
					ArrayList<Recommendation> listOfRec =rec.get(id);
					//sort recommendations based on the cValue
					Collections.sort(listOfRec,new RecommendationComparator());
					//sublist based on value of topN
					System.out.println("Top "+topN+" Recommendations for "+name+" :");
					for (int i = 0; i < listOfRec.size(); i++) {
							if(i==topN)
								break;
							Recommendation r = listOfRec.get(i);
							System.out.println(r.getName()+" "+r.getcValue());
                    }
					System.out.println();
				}
		
	}
	//method to show n recommendations for a specific entity
	public ArrayList<Recommendation> showRecommendations(String name,int topN){
				return null;
	}
	
}
