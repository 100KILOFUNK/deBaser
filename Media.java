package deBaser;

public abstract class Media {
	String name;
	int year;

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	abstract String toFile();
	
	
}
