package entities;

import java.util.Comparator;

public class Recommendation {
		
	private int name;
	private double cValue;
	
	public Recommendation(int name, double cValue) {
	    this.name = name;
	    this.cValue = cValue;
    }

	public int getName() {
		return name;
	}

	public double getcValue() {
		return cValue;
	}
	
}


	

