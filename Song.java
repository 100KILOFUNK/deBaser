package deBaser;

public class Song extends Media {
	String artist;
	String length;
	String album;
	
	public Song(String name, int year, String length, String album, String artist) {
		super();
		this.name = name;
		this.year = year;
		this.artist = artist;
		this.length = length;
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public String getLength() {
		return length;
	}

	public String getAlbum() {
		return album;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String toString(){
		return (this.name + ", " + this.year + ", " + this.artist + ", " + this.length + ", " + this.album);
	}
	
	public String toFile(){
		return (this.name + "\n" + this.year + "\n" + this.artist + "\n" + this.length + "\n" + this.album);
	}
	
}
