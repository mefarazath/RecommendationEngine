package entities;

import java.util.Comparator;

public class Recommendation {
		
	private int recId;
	private double cValue;
	
	public Recommendation(int name, double cValue) {
	    this.recId = name;
	    this.cValue = cValue;
    }

	public int getRecId() {
		return recId;
	}

	public double getcValue() {
		return cValue;
	}
	
}


	

