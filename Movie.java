package deBaser;

public class Movie extends Media {
	double rating;
	String runtime;
	
	
	public Movie(String name, int year, double rating, String runtime) {
		super();
		this.name = name;
		this.year = year;
		this.rating = rating;
		this.runtime = runtime;
		
	}

	public double getRating() {
		return rating;
	}

	public String getRuntime(){
		return  runtime;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	
	public String toString(){
		return (this.name + ", " + this.year + ", " + this.rating + ", " + this.runtime);
	}
	
	public String toFile(){
		return (this.name + "\n" + this.year + "\n" + this.rating + "\n" + this.runtime);
	}
	
}
