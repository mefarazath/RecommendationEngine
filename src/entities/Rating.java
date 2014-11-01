package entities;

public class Rating {
	//attributes of a rating
	private int userId;
	private int ratedEntityId; //refactor to suit the entity eg. bookId
	private double rating;
	
	public Rating(int userId, int ratedEntityId, double rating) {
	    this.userId = userId;
	    this.ratedEntityId = ratedEntityId;
	    this.rating = rating;
    }

	public int getUserId() {
		return userId;
	}

	public int getRatedEntityId() {
		return ratedEntityId;
	}

	public double getRating() {
		return rating;
	}
	
	
	
	
	
}
