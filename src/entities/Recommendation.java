package entities;


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
	
	@Override
	public boolean equals(Object obj) {
	    return this.recId == ((Recommendation)obj).getRecId();
	}
}


	

