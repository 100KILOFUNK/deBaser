package deBaser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FileChooserUI;



public class GUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//MainFrame
	private BufferedImage image;
	private deBaserHandler obj = new deBaserHandler();
	private JComboBox<String> choices;
	private Container cont;
	private JPanel left;
	private JPanel right;
	private JFileChooser fc;
	
	//Menu
	private JMenuBar tes;
	private JMenu a;
	private JMenu Help;
	private JMenuItem open;
	private JMenuItem saveAs;
	private JMenuItem about;
	
	//right side
	private DefaultListModel<String> info;
	private JList<String> rightList;
	private DefaultListModel<String> bookList;
	private DefaultListModel<String> MovieList;
	private DefaultListModel<String> SongList;
	private JButton editButton;
	private JButton clear;
	private JButton rm;
	
	//Left Side
	private JTabbedPane tabbedPane;
	private JPanel addBook;
	private JPanel addMovies;
	private JPanel addSong;
	int nrOfElements;
	

	//Book specific
	int nrOfBooks;
	private JTextField bName;
	private JTextField byear;
	private JTextField nrOfPages; //remember to use the wrapper class to get it to string
	private JTextField series; // if any
	private JPanel bButtonHolder;
	private JButton badd;
	

	
	//Movies
	int nrOfMovies;
	private JTextField mName;
	private JTextField myear;
	private JTextField rating; //remember to use the wrapper class to get it to string
	private JTextField runtime;
	private JPanel mButtonHolder;
	private JButton madd;
	

	
	//Songs
	int nrOfSongs;
	private JTextField sName;
	private JTextField syear;
	private JTextField artist;
	private JTextField duration; //remember to use the wrapper class to get it to string
	private JTextField album; // if any
	private JPanel sButtonHolder;
	private JButton sadd;
	

	
	
	
	private int index;
	private JFrame edit;
	private Container temp;
	private JTextField tmpName;
	private JTextField tmpYear;
	
	//BookEdit
	private JTextField tmpNrOfPages;
	private JTextField tmpSeries;
	private JButton bapply;
	
	//MovieEdit
	private JTextField tmpRating;
	private JTextField tmpRuntime;
	private JButton mapply;
	//SongEdit
	private JTextField tmpArtist;
	private JTextField tmpLength;
	private JTextField tmpAlbum;
	private JButton sapply;
	
	//Searching
	private JTextField searchBar;
	private DefaultListModel<String> resultArr;
	
	
	
	private class ListRenderer extends DefaultListCellRenderer
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private HashMap<Object, String> theChosen = new HashMap<Object, String>();
  
        public Component getListCellRendererComponent( JList list,
                Object value, int index, boolean isSelected,
                boolean cellHasFocus )
        {
            super.getListCellRendererComponent( list, value, index,
                    isSelected, cellHasFocus );
  
            if( isSelected )
            {
                theChosen.put( value, "chosen" );
            }
            else{
            }
  
            if( theChosen.containsKey( value ) )
            {
                setForeground( Color.blue );
            }
            else
            {
                setOpaque(false);
                
                
            }
  
            return( this );
        }
    }
	
	
	private class documentListener implements DocumentListener{	

		@Override
		public void insertUpdate(DocumentEvent e) {
			search();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			search();
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
	private class Buttonlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//FUNCTION THAT ADD ELEMENTS
			if(e.getSource().equals(badd)){
				addBook();
			}
			
			if(e.getSource().equals(madd)){
				addMovie();
			}
			
			if(e.getSource().equals(sadd)){
				addSong();
			}
			
			//FUNCTION THAT REMOVES SPECIFIC ELEMENT
			if(e.getActionCommand().equals("X")){
				rmSelectedElement();
			}
			
			//These functions open up the new JFrame
			if(e.getSource().equals(editButton)){
				edit();
			}
			
			if(e.getSource().equals(bapply)){
				editBook();
			}
			
			if(e.getSource().equals(mapply)){
				editMovie();
			}
			
			if(e.getSource().equals(sapply)){
				editSong();
			}
			
			if(e.getSource().equals(clear)){
				clear();
			}
			
		}
		
	}
	
	private class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("About")){
				about();
			}
			
			if(e.getSource().equals(saveAs)){
				saveAs();
			}
			
			if(e.getSource().equals(open)){
				open();
			}
			
		}
		
	}
	
	private class ComboBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			@SuppressWarnings("unchecked")
			JComboBox<String> cb =  (JComboBox<String>) e.getSource();
			String newSelection = (String) cb.getSelectedItem();
			if(newSelection.equals("All")){
				rightList.setModel(info);
			}
			if(newSelection.equals("Books")){
				rightList.setModel(bookList);
			}
			if(newSelection.equals("Movies")){
				rightList.setModel(MovieList);
			}if(newSelection.equals("Songs")){
				rightList.setModel(SongList);
			}
			
			
		}
		
	}
	
	
	
	
	public GUI(){
		File img = new File("src/deBaser/CSS.jpg");
		try {
			image = ImageIO.read(img);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		setContentPane(new ImagePanel(image));
		this.cont = getContentPane();
		setSize(1000, 600);
		setTitle("deBaser");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.cont.setLayout(new GridLayout(1,2));
		obj = new deBaserHandler();
		info = new DefaultListModel<String>();
		bookList = new DefaultListModel<String>();
		MovieList = new DefaultListModel<String>();
		SongList = new DefaultListModel<String>();
		
		nrOfBooks = 0;
		nrOfMovies = 0;
		nrOfSongs = 0;
		nrOfElements = 0;
		
		
		
		inititateLeft();
		initiateRight();
		addMenu();
		assignButtons();
		choices.addActionListener(new ComboBoxListener());
		
		
		left.setOpaque(false);
		right.setOpaque(false);
		this.cont.add(left);
		this.cont.add(right);

		
	}
	
	public void search(){
		resultArr.clear();
		String[] results = obj.search(searchBar.getText());
		for(int i = 0; i < results.length; i++){
			resultArr.addElement(results[i]);
		}
		rightList.setModel(resultArr);
	}
	
	public void open(){
		
		fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		int val = fc.showOpenDialog(getParent());
		if(val == JFileChooser.APPROVE_OPTION){
			bookList.removeAllElements();
			MovieList.removeAllElements();
			SongList.removeAllElements();
			File selectedFile = fc.getSelectedFile();
			try {
				obj.readFromFile(selectedFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			info.removeAllElements();
			for(int i = 0; i<obj.getAsArray().size(); i++){
				info.addElement(obj.getIndex(i).toString());
				if(obj.getIndex(i).getClass().equals(Book.class)){
					bookList.addElement(obj.getIndex(i).toString());
				}
				if(obj.getIndex(i).getClass().equals(Movie.class)){
					MovieList.addElement(obj.getIndex(i).toString());
				}
				if(obj.getIndex(i).getClass().equals(Song.class)){
					SongList.addElement(obj.getIndex(i).toString());
				}
			}
			
		}
		
		rightList.setModel(info);
		
	}
	
	public void saveAs(){
		fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		int val = fc.showSaveDialog(getParent());
		if (val == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			System.out.println("File Path: " + selectedFile.getAbsolutePath());
			try {
				obj.saveOnFile(selectedFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void about(){
		System.out.println("lel");
		JFrame aboutFrame = new JFrame();
		Container aboutTMP = new Container();
		JPanel textPanel = new JPanel();
		
		aboutFrame.setResizable(false);
		aboutFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		aboutTMP.setLayout(new FlowLayout());
		aboutFrame.setSize(600, 400);
		aboutFrame.setTitle("About");
		JTextArea aboutTxt = new JTextArea();
		aboutTxt.setBorder(BorderFactory.createTitledBorder("About"));
		aboutTxt.setText("DeBaser Media Handler is written by Nima Goniband at Crowned Stag Solutions.\nThis Software is opensource so use it however you like.\nFor any Troubles, email me at n.goniband@gmail.com\nv1.0 2015.");
		aboutTxt.setEditable(false);
		aboutTxt.setOpaque(false);
		textPanel.setOpaque(false);
		textPanel.add(aboutTxt);
		aboutTMP.add(textPanel);
		aboutFrame.setVisible(true);
		aboutFrame.add(aboutTMP);
	}
	
	public void clear(){
		String ch = (String) choices.getSelectedItem();
		
		
		if(ch.equals("All")){
			obj.rmAll();
			info.removeAllElements();
			bookList.removeAllElements();
			MovieList.removeAllElements();
			SongList.removeAllElements();
		}
		if(ch.equals("Books")){
			bookList.removeAllElements();
			
			for(int i = 0; i < obj.getAsArray().size(); i++){
				if(obj.getIndex(i).getClass().equals(Book.class)){
					obj.rmAtIndex(i);
					info.remove(i);
					i--;
				}
			}
		}
		if(ch.equals("Movies")){
			MovieList.removeAllElements();
			
			for(int i = 0; i < obj.getAsArray().size(); i++){
				if(obj.getIndex(i).getClass().equals(Movie.class)){
					obj.rmAtIndex(i);
					info.remove(i);
					i--;
				}
			}
		}
		
		if(ch.equals("Songs")){
			SongList.removeAllElements();
			
			for(int i = 0; i < obj.getAsArray().size(); i++){
				if(obj.getIndex(i).getClass().equals(Song.class)){
					obj.rmAtIndex(i);
					info.remove(i);
					i--;
				}
			}
		}
		
	}
	
	public void edit(){
		String newSelection = (String) choices.getSelectedItem();
		
		
		
		if(newSelection.equals("All")){
			index = rightList.getSelectedIndex();
			if(obj.getIndex(index).getClass().equals(Book.class)){
				System.out.println("lel");
				beditGUI();
			
			}
			if(obj.getIndex(index).getClass().equals(Movie.class)){
				System.out.println(index);
				meditGUI();
			}
			if(obj.getIndex(index).getClass().equals(Song.class)){
				seditGUI();
			}
			
		}
		
		if(newSelection.equals("Books")){
			String value = rightList.getSelectedValue();
			index = info.indexOf(value);
			beditGUI();
			
		}
		
		if(newSelection.equals("Movies")){
			String value = rightList.getSelectedValue();
			System.out.println(value);
			index = info.indexOf(value);
			System.out.println(index);
			meditGUI();
		}
		
		if(newSelection.equals("Songs")){
			String value = rightList.getSelectedValue();
			index = info.indexOf(value);
			seditGUI();
		}
	}
	
	public void addSong(){
		obj.addSong(sName.getText(), Integer.parseInt(syear.getText()), duration.getText(), album.getText(), artist.getText());
		info.addElement(obj.getLast());
		SongList.addElement(obj.getLast());
		nrOfSongs++;
		nrOfElements++;
		sName.setText("");
		syear.setText("");
		duration.setText("");
		artist.setText("");
		album.setText("");
	}
	
	public void addMovie(){
		obj.addMovie(mName.getText(), Integer.parseInt(myear.getText()), Double.parseDouble(rating.getText()), runtime.getText());
		info.addElement(obj.getLast());
		MovieList.addElement(obj.getLast());
		nrOfMovies++;
		nrOfElements++;
		mName.setText("");
		myear.setText("");
		rating.setText("");
		runtime.setText("");
	}
	
	public void addBook(){
		obj.addBook(bName.getText(), Integer.parseInt(byear.getText()), Integer.parseInt(nrOfPages.getText()), series.getText());
		info.addElement(obj.getLast());
		bookList.addElement(obj.getLast());
		nrOfBooks++;
		nrOfElements++;
		bName.setText("");
		byear.setText("");
		nrOfPages.setText("");
		series.setText("");
	}
	
	public void editSong(){
		String song = info.getElementAt(index);
		obj.getIndex(index).setName(tmpName.getText());
		obj.getIndex(index).setYear(Integer.parseInt(tmpYear.getText()));
		((Song)obj.getIndex(index)).setArtist(tmpArtist.getText());
		((Song)obj.getIndex(index)).setLength(tmpLength.getText());
		((Song)obj.getIndex(index)).setAlbum(tmpAlbum.getText());
		info.remove(index);
		info.add(index, obj.getAtIndex(index));
		int i = SongList.indexOf(song);
		SongList.remove(i);
		SongList.add(i, obj.getAtIndex(index));
		index = 0;
		edit.dispose();
		
	}
	
	public void editMovie(){
		String movie = info.getElementAt(index);
		System.out.println(movie);
		obj.getIndex(index).setName(tmpName.getText());
		obj.getIndex(index).setYear(Integer.parseInt(tmpYear.getText()));
		((Movie)obj.getIndex(index)).setRating(Double.parseDouble(tmpRating.getText()));
		((Movie)obj.getIndex(index)).setRuntime(tmpRuntime.getText());
		info.remove(index);
		info.add(index, obj.getAtIndex(index));
		int i = MovieList.indexOf(movie);
		MovieList.remove(i);
		MovieList.add(i, obj.getAtIndex(index));
		index = 0;
		edit.dispose();

	}
	
	public void editBook(){
		
		String book = info.getElementAt(index);
		obj.getIndex(index).setName(tmpName.getText());
		obj.getIndex(index).setYear(Integer.parseInt(tmpYear.getText()));
		((Book)obj.getIndex(index)).setNrOfPages(Integer.parseInt(tmpNrOfPages.getText()));
		((Book)obj.getIndex(index)).setSeries(tmpSeries.getText());
		info.remove(index);
		info.add(index, obj.getAtIndex(index));
		int i = bookList.indexOf(book);
		bookList.remove(i);
		bookList.add(i, obj.getAtIndex(index));
		index = 0;
		edit.dispose();
		
	}
	
	public void seditGUI(){
		edit = new JFrame();
		temp = new Container();
		
		edit.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		temp.setLayout(new GridLayout(6,1));
		
		edit.setSize(300, 300);
		edit.setTitle("Edit Song");
		edit.setVisible(true);
		tmpName = new JTextField();
		tmpYear = new JTextField();
		tmpArtist = new JTextField();
		tmpLength = new JTextField();
		tmpAlbum = new JTextField();
		sapply = new JButton("apply");
		sapply.addActionListener(new Buttonlistener());
		
		tmpName.setBorder(BorderFactory.createTitledBorder("Title"));
		tmpYear.setBorder(BorderFactory.createTitledBorder("Year"));
		tmpArtist.setBorder(BorderFactory.createTitledBorder("Artist/Group/Composer"));
		tmpLength.setBorder(BorderFactory.createTitledBorder("Length"));
		tmpAlbum.setBorder(BorderFactory.createTitledBorder("Album"));
		
		
		//här ska du kalla på subklassens funktioner
				
		tmpName.setText(obj.getIndex(index).getName());
		obj.getIndex(index).getName();
		tmpYear.setText(String.valueOf(obj.getIndex(index).getYear()));
		tmpArtist.setText(((Song) obj.getIndex(index)).getArtist());
		tmpLength.setText(((Song)obj.getIndex(index)).getLength());
		tmpAlbum.setText(((Song)obj.getIndex(index)).getAlbum());
		
		
		
		temp.add(tmpName);
		temp.add(tmpYear);
		temp.add(tmpArtist);
		temp.add(tmpLength);
		temp.add(tmpAlbum);
		temp.add(sapply);
		edit.add(temp);

		
	}
	
	public void meditGUI(){
		edit = new JFrame();
		temp = new Container();
		edit.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		temp.setLayout(new GridLayout(5,1));
		
		edit.setSize(300, 300);
		edit.setTitle("Edit Movie");
		edit.setVisible(true);
		tmpName = new JTextField();
		tmpYear = new JTextField();
		tmpRating = new JTextField();
		tmpRuntime = new JTextField();
		mapply = new JButton("apply");
		mapply.addActionListener(new Buttonlistener());
		
		tmpName.setBorder(BorderFactory.createTitledBorder("Title"));
		tmpYear.setBorder(BorderFactory.createTitledBorder("Year"));
		tmpRating.setBorder(BorderFactory.createTitledBorder("Rating"));
		tmpRuntime.setBorder(BorderFactory.createTitledBorder("Runtime"));
		
		

				//här ska du kalla på subklassens funktioner
				
		tmpName.setText(obj.getIndex(index).getName());
		obj.getIndex(index).getName();
		tmpYear.setText(String.valueOf(obj.getIndex(index).getYear()));
		tmpRating.setText(String.valueOf(((Movie) obj.getIndex(index)).getRating()));
		tmpRuntime.setText(((Movie)obj.getIndex(index)).getRuntime());
		
		
		temp.add(tmpName);
		temp.add(tmpYear);
		temp.add(tmpRating);
		temp.add(tmpRuntime);
		temp.add(mapply);
		edit.add(temp);

	}
	
	public void beditGUI(){
		edit = new JFrame();
		temp = new Container();
		System.out.println("l");
		edit.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		temp.setLayout(new GridLayout(5,1));
		
		edit.setSize(300, 300);
		edit.setTitle("Edit Book");
		edit.setVisible(true);
		tmpName = new JTextField();
		tmpYear = new JTextField();
		tmpNrOfPages = new JTextField();
		tmpSeries = new JTextField();
		bapply = new JButton("apply");
		bapply.addActionListener(new Buttonlistener());
		
		tmpName.setBorder(BorderFactory.createTitledBorder("Title"));
		tmpYear.setBorder(BorderFactory.createTitledBorder("Year"));
		tmpNrOfPages.setBorder(BorderFactory.createTitledBorder("Number of pages"));
		tmpSeries.setBorder(BorderFactory.createTitledBorder("Series (if any)"));
		
		//här ska du kalla på subklassens funktioner
		tmpName.setText(obj.getIndex(index).getName());
		tmpYear.setText(String.valueOf(obj.getIndex(index).getYear()));
		tmpNrOfPages.setText(String.valueOf(((Book) obj.getIndex(index)).getNrOfPages()));
		tmpSeries.setText(((Book)obj.getIndex(index)).getSeries());
			
		
		
		temp.add(tmpName);
		temp.add(tmpYear);
		temp.add(tmpNrOfPages);
		temp.add(tmpSeries);
		temp.add(bapply);
		edit.add(temp);
		
		
	}
	
	public void rmSelectedElement(){
		obj.rmAtIndex(rightList.getSelectedIndex());
		String toRemove = rightList.getSelectedValue();
		//Kolla igenom alla listor om elementet finns i någon utav dem
		if(info.contains(toRemove)){
			info.removeElement(toRemove);
			
		}
		if(bookList.contains(toRemove)){
			bookList.removeElement(toRemove);
			nrOfBooks--;
			nrOfElements--;
		}
		if(MovieList.contains(toRemove)){
			MovieList.removeElement(toRemove);
			nrOfMovies--;
			nrOfElements--;
		}
		if(SongList.contains(toRemove)){
			SongList.removeElement(toRemove);
			nrOfSongs--;
			nrOfElements--;
		}
	}
	
	public void inititateLeft(){
		
		tabbedPane = new JTabbedPane();
		left = new JPanel();
		left.setLayout(new GridLayout(1,1));
		addBook = new JPanel();
		addBook.setLayout(new GridLayout(5,1));
		addMovies = new JPanel();
		addMovies.setLayout(new GridLayout(5,1));
		addSong = new JPanel();
		addSong.setLayout(new GridLayout(6,1));
		
		
		tabbedPane.setOpaque(false);
		addBook.setOpaque(false);
		addMovies.setOpaque(false);
		addSong.setOpaque(false);
		
		addBookProperties();
		addMoviesProperties();
		addSongProperties();
		addMenu();
		
		
		tabbedPane.add("Add Book", addBook);
		tabbedPane.add("Add Movie", addMovies);
		tabbedPane.add("Add Song", addSong);
		left.add(tabbedPane);
		//left.add(Name);
		//left.add(year);
		
	}
	
	public void initiateRight(){
		JPanel textHolder = new JPanel();
		JScrollPane scr = new JScrollPane();
		scr.setOpaque(false);
		searchBar = new JTextField();
		searchBar.setOpaque(false);
		searchBar.setForeground(new Color(255,255,255));
		searchBar.setBorder(BorderFactory.createTitledBorder(null, "Search", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		resultArr = new DefaultListModel<String>();
		searchBar.getDocument().addDocumentListener(new documentListener());
		right = new JPanel();
		JPanel editButtonHolder = new JPanel();
		editButton = new JButton("Edit");
		clear = new JButton("Clear");
		rm = new JButton("X");
		editButtonHolder.setLayout(new FlowLayout());
		editButtonHolder.add(editButton);
		editButtonHolder.add(clear);
		editButtonHolder.add(rm);
		editButtonHolder.setOpaque(false);
		editButtonHolder.setBorder(BorderFactory.createTitledBorder(null, "Actions Regarding The List", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		String[] mediums = {"All", "Books", "Movies", "Songs"};
		choices = new JComboBox<String>(mediums);
		rightList = new JList<String>(info);
		rightList.setOpaque(false);
		rightList.setCellRenderer(new ListRenderer());
		rightList.setForeground(new Color(255,255,255));
		scr.getViewport().setOpaque(false);
		scr.setViewportView(rightList);
		textHolder.setLayout(new BorderLayout());
		textHolder.add(choices, BorderLayout.NORTH);
		textHolder.add(searchBar, BorderLayout.SOUTH);
		textHolder.setOpaque(false);
		right.setLayout(new BorderLayout());
		textHolder.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		rightList.setBorder(BorderFactory.createTitledBorder(null, "List", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		right.add(textHolder, BorderLayout.NORTH);
		right.add(scr); //Default center
		right.add(editButtonHolder, BorderLayout.SOUTH);
	}
	
	public void addBookProperties(){
		//BOOKSPECIFIC
		Color White = new Color(255, 255, 255, 255);
		bName = new JTextField();
		bName.setBorder(BorderFactory.createTitledBorder(null, "Name", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		byear = new JTextField();
		byear.setBorder(BorderFactory.createTitledBorder(null, "Publishing Year", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		
		nrOfPages = new JTextField();
		series = new JTextField();
		nrOfPages.setBorder(BorderFactory.createTitledBorder(null, "Number Of Pages", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		series.setBorder(BorderFactory.createTitledBorder(null, "Series", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		
		bName.setOpaque(false);
		byear.setOpaque(false);
		nrOfPages.setOpaque(false);
		series.setOpaque(false);
		bButtonHolder = new JPanel();
		badd = new JButton("add");
		bName.setForeground(White);
		byear.setForeground(White);
		nrOfPages.setForeground(White);
		series.setForeground(White);
		
		
		bButtonHolder.setOpaque(false);
		bButtonHolder.setLayout(new FlowLayout());
		bButtonHolder.add(badd);
	
		
		
		addBook.add(bName);
		addBook.add(byear);
		addBook.add(nrOfPages);
		addBook.add(series);
		addBook.add(bButtonHolder);
		
		
	}
	
	public void addMoviesProperties(){
		//MOVIESPECIFIC
		
		Color White = new Color(255, 255, 255, 255);
		mName = new JTextField();
		mName.setBorder(BorderFactory.createTitledBorder(null, "Name", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		myear = new JTextField();
		myear.setBorder(BorderFactory.createTitledBorder(null, "Publishing Year", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		rating = new JTextField();
		rating.setBorder(BorderFactory.createTitledBorder(null, "Rating", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		runtime = new JTextField();
		runtime.setBorder(BorderFactory.createTitledBorder(null, "Runtime", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, new Color(255,255,255)));
		mButtonHolder = new JPanel();
		mName.setForeground(White);
		myear.setForeground(White);
		rating.setForeground(White);
		runtime.setForeground(White);
		
		mName.setOpaque(false);
		myear.setOpaque(false);
		rating.setOpaque(false);
		runtime.setOpaque(false);
		mButtonHolder.setOpaque(false);
		madd = new JButton("add");
		
		mButtonHolder.setLayout(new FlowLayout());
		mButtonHolder.add(madd);
		
		
		
		addMovies.add(mName);
		addMovies.add(myear);
		addMovies.add(rating);
		addMovies.add(runtime);
		addMovies.add(mButtonHolder);
	}
	
	public void addSongProperties(){
		Color White = new Color(255, 255, 255, 255);
		sName = new JTextField();
		sName.setBorder(BorderFactory.createTitledBorder(null, "Title", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(255,255,255)));
		syear = new JTextField();
		syear.setBorder(BorderFactory.createTitledBorder(null, "Publishing year", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(255,255,255)));
		
		
		artist = new JTextField();
		artist.setBorder(BorderFactory.createTitledBorder(null, "Artist/Composer/Group", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(255,255,255)));
		duration = new JTextField();
		duration.setBorder(BorderFactory.createTitledBorder(null, "Duration", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(255,255,255)));
		album = new JTextField();
		album.setBorder(BorderFactory.createTitledBorder(null, "Album", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(255,255,255)));
		sButtonHolder = new JPanel();
		
		sName.setForeground(White);
		syear.setForeground(White);
		duration.setForeground(White);
		album.setForeground(White);
		artist.setForeground(White);
		
		
		sName.setOpaque(false);
		syear.setOpaque(false);
		artist.setOpaque(false);
		duration.setOpaque(false);
		album.setOpaque(false);
		sButtonHolder.setOpaque(false);
		
		sadd = new JButton("add");
		sButtonHolder.setLayout(new FlowLayout());
		sButtonHolder.add(sadd);
		
		
		
		addSong.add(sName);
		addSong.add(syear);
		addSong.add(artist);
		addSong.add(duration);
		addSong.add(album);
		addSong.add(sButtonHolder);
	}
	
	public void addMenu(){
		tes = new JMenuBar();
		//tes.setOpaque(false);
		a = new JMenu("File");
		Help = new JMenu("Help");
		tes.add(a);
		tes.add(Help);
		open = new JMenuItem("Open");
		a.add(open);
		saveAs = new JMenuItem("Save As..");
		a.add(saveAs);
		about = new JMenuItem("About");
		Help.add(about);
		setJMenuBar(tes);
		open.addActionListener(new MenuListener());
		saveAs.addActionListener(new MenuListener());
		about.addActionListener(new MenuListener());
	}
	
	public void assignButtons(){
		badd.addActionListener(new Buttonlistener());
		madd.addActionListener(new Buttonlistener());
		sadd.addActionListener(new Buttonlistener());
		
		editButton.addActionListener(new Buttonlistener());
		clear.addActionListener(new Buttonlistener());
		rm.addActionListener(new Buttonlistener());
		
		
	}

	
}



