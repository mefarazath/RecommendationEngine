package entities;

import java.util.Comparator;

public class RecommendationComparator implements Comparator<Recommendation>{

	@Override
    public int compare(Recommendation r1, Recommendation r2) {
	    
		if(r1.getcValue()>r2.getcValue()){
			return -1;
		}
		else if(r1.getcValue()<r2.getcValue()){
			return 1;
		}
		else
			return 0;
    }
	
}