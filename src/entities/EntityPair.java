package entities;

public class EntityPair {
		
		private int entity1;
		private int entity2;
		
		// base data required 
		private int numberOfCorratings; //number of corratings for the pair
		private double ratingSum1;  //sum of ratings for entity 1
		private double ratingSum2;  //sum of ratings for entity2
		private double ratingSquareSum; //sum of squares of ratings for entity 1	
		private double ratingSquareSum2; //sum of squares of the ratings for entity 2
		private double dotProductSum;	//sum of dot products of the ratings
		
		public EntityPair(int entity1, int entity2) {
	      
	        this.entity1 = entity1;
	        this.entity2 = entity2;
        }

		public int getEntity1() {
			return entity1;
		}

		public int getEntity2() {
			return entity2;
		}

		public int getNumberOfCorratings() {
			return numberOfCorratings;
		}

		public void setNumberOfCorratings(int numberOfCorratings) {
			this.numberOfCorratings = numberOfCorratings;
		}

		public double getRatingSum1() {
			return ratingSum1;
		}

		public void setRatingSum1(double ratingSum1) {
			this.ratingSum1 = ratingSum1;
		}

		public double getRatingSum2() {
			return ratingSum2;
		}

		public void setRatingSum2(double ratingSum2) {
			this.ratingSum2 = ratingSum2;
		}

		public double getRatingSquareSum() {
			return ratingSquareSum;
		}

		public void setRatingSquareSum(double ratingSquareSum) {
			this.ratingSquareSum = ratingSquareSum;
		}

		public double getRatingSquareSum2() {
			return ratingSquareSum2;
		}

		public void setRatingSquareSum2(double ratingSquareSum2) {
			this.ratingSquareSum2 = ratingSquareSum2;
		}

		public double getDotProduct() {
			return dotProductSum;
		}

		public void setDotProduct(double dotProduct) {
			this.dotProductSum = dotProduct;
		}
		
		@Override
		public String toString() {
			return entity1+","+entity2;
		}
		
		@Override
		public boolean equals(Object obj) {
			return this.toString().equals(obj);
		}
		
		
		
}
