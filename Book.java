package deBaser;

public class Book extends Media{
	int nrOfPages;
	String series; //if any
	
	
	public Book(String name, int year, int nrOfPages, String series) {
		super();
		this.name = name;
		this.year = year;
		this.nrOfPages = nrOfPages;
		this.series = series;
	}
	
	public Book(String name, int year, int nrOfPages){
		super();
		this.name = name;
		this.year = year;
		this.nrOfPages = nrOfPages;
		this.series = null;
	}
	
	public int getNrOfPages() {
		return nrOfPages;
	}

	public String getSeries() {
		return series;
	}

	public void setNrOfPages(int nrOfPages) {
		this.nrOfPages = nrOfPages;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String toString(){
		return (this.name + ", " + this.year + ", " + this.nrOfPages + ", " + this.series);
	}
	
	public String toFile(){
		return (this.name + "\n" + this.year + "\n" + this.nrOfPages + "\n" + this.series);
	}
	
	
	
	
	
}
