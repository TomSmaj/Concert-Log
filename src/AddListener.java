import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.*;

public class AddListener implements ActionListener{

	//sql connector
	Connection conn = null;
	
	//local components to be associated with components from ButtonListener
	JComboBox mBox, dBox, yBox;
	JTextField vText;
	ArrayList<JTextField> tfL;
	String type;
	JFrame frame;
	
	//strings to store values from components, i.e. intended to hold the string values from the component objects
	String month, day, year, venue;
	ArrayList<String> artistList = new ArrayList<>();
	
	//constructor takes the components from ButtonListener and associates them with local variables
	public AddListener(JComboBox monthBox, JComboBox dayBox, JComboBox yearBox, JTextField venText,
			ArrayList<JTextField> tfList, String type, JFrame frame) {
		mBox = monthBox;
		dBox = dayBox;
		yBox = yearBox;
		vText = venText;
		tfL = tfList;
		this.type = type;
		this.frame = frame;
		
		//load JDBC driver
		try {Class.forName("com.mysql.jdbc.Driver").newInstance();}
		catch(Exception ex) {}
		//connect to MySQL database, db name = concertlog, username=root, password=root
		try {conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/concertlog","root","root");}
		catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//getting the selected month, date and year from the combo boxes
		//as well as the venue and artists entered into the text fields
		month = (String)mBox.getSelectedItem();
		day = (String)dBox.getSelectedItem();
		year = (String)yBox.getSelectedItem();
		venue = vText.getText();
		
		//populate artistList with Strings from the artist text fields
		String out = ""; //out is all artists enter concatenate together into a string for easy output
		for(int i = 0; i < tfL.size(); i++)
		{
			artistList.add(tfL.get(i).getText());
			out += tfL.get(i).getText() + ", ";
		}
		
		//output of user input, used for testing purposes
		System.out.println("month: " + month + ", day: " + day + ", year: " + year + "\nvenue: " + venue + ", artist(s): " + out);
		
		//string that will be used to execute insertions into the MySQL tables
		String inA = "INSERT into artists (artistName, timesSeen) VALUES(%s, %s)";
		String inV = "INSERT into venues (venueName, timesVisited, city, type) VALUES(%s, %s)";
		String inC = "INSERT into cities (cityName, country) VALUES(%s, %S)";
		String inS = "INSERT into shows (venueName, date, type) VALUES(%s, %s, %s)";
		String inSA = "INSTER into shows_artists (showID, artistID) VALUES(%s, %s)";
		
		//string for checking if artist, venue, or city already exists
		String chA = "SELECT artistID FROM artists WHERE artistName = \"%s\"";
		String chV = "SELECT venueID from venues WHERE venueName = \"%s\"";
		String chC = "SELECT cityID FROM venues WHERE cityName = \"%s\"";
		
		//booleans that will be set to true if artist/venue/city exists in database.
		boolean aExists = false, vExists = false, cExists = false;
		try {
			//check if artists already exist in database
			Statement st = conn.createStatement();
			//executing the SQL command contained in chA, which returns the artist ID of the artist at index 0 of artistList 
			ResultSet rs = st.executeQuery(String.format(chA, artistList.get(0)));
			//isBeforeFirst() returns true if it is position before data, if there is no data, it will return false
			aExists = rs.isBeforeFirst();
			if(aExists) {
				rs.next();
				System.out.println("Artist ID: " + rs.getInt("artistID"));
			}
			else {System.out.println("Artist does not exist.");}
			//check if venue already exists in database (and then see if city exists)
			rs = st.executeQuery(String.format(chV, venue));
			vExists = rs.isBeforeFirst();
			if(vExists) {
				rs.next();
				System.out.println("Venue ID: " + rs.getInt("venueID"));
			}
			else {System.out.println("Venue does not exist.");}
			//if the venue does not exist, the user will have to be prompted to enter the city the venue is in
			//then check if that city exists already
			
		}
		catch(Exception ae) 
		{
		    System.out.println("SQLException: " + ae.getMessage());
		}
	
		//closes frame
		frame.dispose();
	}
}
