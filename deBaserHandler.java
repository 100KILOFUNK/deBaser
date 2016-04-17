package deBaser;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class deBaserHandler {
	
	
	
	LinkedList<Media> mediums;
	
	public deBaserHandler(){
		mediums = new LinkedList<Media>();
		
	}


	public void addBook(String name, int year, int nrOfPages, String series){
		Media book = new Book(name, year, nrOfPages, series);
		mediums.add(book);
		
		
	}
	public void addMovie(String name, int year, double rating, String runtime){
		Media Movie = new Movie(name, year, rating, runtime);
		mediums.add(Movie);
		
	}
	public void addSong(String name, int year, String length, String album, String artist){
		Media Song = new Song(name, year, length, album, artist);
		mediums.add(Song);
		
	}
	public void rmBook(){
		
	}
	public LinkedList<Media> getAsArray(){
		return mediums;
	}
	public String getAtIndex(int index){
		return mediums.get(index).toString();
	}
	public Media getIndex(int index){
		return mediums.get(index);
	}
	public String getLast(){
		return mediums.getLast().toString();
		
	}
	public void rmAtIndex(int index){
		mediums.remove(index);
		
	}
	
	public void rmAll(){
		mediums.removeAll(mediums);
	}
	
	public void saveOnFile(File f) throws FileNotFoundException{
		
		PrintWriter wr = new PrintWriter(f);
		
		for(int i = 0; i < getAsArray().size(); i++){
			wr.print(getIndex(i).getClass());
			wr.print("\n");
			//wr.print(getIndex(i));
			//wr.print(((Book)getIndex(i)).toFile());
			wr.print(getIndex(i).toFile());
			wr.print("\n");
		}
		wr.close();
		
	}
	
	public void readFromFile(File r) throws FileNotFoundException{
		mediums.removeAll(mediums);
		
		Scanner scn = new Scanner(r);
		//scn.useDelimiter(",");
		while(scn.hasNextLine()){
			String t = scn.nextLine();
			if(t.equals("class deBaser.Book")){
				addBook(scn.nextLine(), Integer.parseInt(scn.nextLine()), Integer.parseInt(scn.nextLine()),scn.nextLine());
			}
			if(t.equals("class deBaser.Movie")){
				addMovie(scn.nextLine(), Integer.parseInt(scn.nextLine()), Double.parseDouble(scn.nextLine()), scn.nextLine());				
			}
			if(t.equals("class deBaser.Song")){
				addSong(scn.nextLine(), Integer.parseInt(scn.nextLine()), scn.nextLine(), scn.nextLine(), scn.nextLine());
			}
			
			
		}
		
	}
	
	public String[] search(String keyword){
		String[] results = new String[this.mediums.size()];
		int index = 0;
		for(int i = 0; i < this.mediums.size(); i++){
			if(mediums.get(i).name.contains(keyword)){
				results[index] = mediums.get(i).toString();
				index++;
			}
		}
		
		
		return results;
	}
	
	


}
