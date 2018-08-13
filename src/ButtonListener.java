import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ButtonListener implements ActionListener {

	//create the layout object that will used when assigning the spring layout for each frame
	SpringLayout layout = new SpringLayout();
	
	//arrays used for date combo boxes
	String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
						"October", "November", "December"};
	String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", 
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	String[] years = {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", 
						"2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
			
	
	
	public ButtonListener() {
		
	}
	
	public void actionPerformed(ActionEvent e) {
//		JOptionPane.showMessageDialog(null, e.getActionCommand());
		if(e.getActionCommand().equals("Show List")) {sLFrame();}
		else if(e.getActionCommand().equals("Add Show")) {aSFrame();}
/*		else if(e.getActionCommand().equals("Add Festival")) {aFFrame();}
		else if(e.getActionCommand().equals("Festival List")) {fLFrame();}
		else if(e.getActionCommand().equals("Top Artists")) {tAFrame();}
		else if(e.getActionCommand().equals("Top Venues")) {tVFrame();}		
		else if(e.getActionCommand().equals("Clear")) {cLFrame();}		*/
	}
	
	//pop up window for adding a show
	public void aSFrame(){
		//create JFrame and JPanel
		JFrame asF = new JFrame("Add Show");
		JPanel asPanel = new JPanel();
		//set layout to spring layout, object is created at top of file
		asPanel.setLayout(layout);
		//create a bold label that reads 'Add Show'
		JLabel asLabel = new JLabel("Add Show");
		Font font = new Font("", Font.BOLD,14);
		asLabel.setFont(font);	
		JLabel dateLabel = new JLabel("Date");
		JLabel venueLabel = new JLabel("Venue");
		JLabel artistLabel = new JLabel("Artist(s)");
		//creating month, day, and year combo boxes for date selection
		JComboBox monthBox = new JComboBox(months);
		JComboBox dayBox = new JComboBox(days);
		JComboBox yearBox = new JComboBox(years);
		//text field for venue name and first artist
		JTextField venText = new JTextField(20);
		JTextField artText = new JTextField(20);
		//button for adding additional artist text fields
		JButton addArt = new JButton("+");
		JButton submit = new JButton("Submit");
		
		int py = 220; //setting the size of the panel, the height is a variable because it is used later when updating the panel size when additional textfields are added
		asPanel.setPreferredSize(new Dimension(400, py));
		
		//adding all components to panel
		asPanel.add(asLabel);
		asPanel.add(dateLabel);
		asPanel.add(venueLabel);
		asPanel.add(artistLabel);	
		asPanel.add(monthBox);
		asPanel.add(dayBox);
		asPanel.add(yearBox);
		asPanel.add(venText);
		asPanel.add(artText);
		asPanel.add(addArt);
		asPanel.add(submit);
		
		//set location of each component, e.g. setting the north side of asLabel 17 pixels from north side of asPanel
		layout.putConstraint(SpringLayout.NORTH, asLabel, 17, SpringLayout.NORTH, asPanel); //Add Show label
		layout.putConstraint(SpringLayout.WEST, asLabel, 170, SpringLayout.WEST, asPanel);	
		layout.putConstraint(SpringLayout.NORTH, dateLabel, 38, SpringLayout.NORTH, asLabel); //date Label
		layout.putConstraint(SpringLayout.WEST, dateLabel, 55, SpringLayout.WEST, asPanel);	
		layout.putConstraint(SpringLayout.NORTH, monthBox, 35, SpringLayout.NORTH, asLabel); //month combo box
		layout.putConstraint(SpringLayout.WEST, monthBox, 70, SpringLayout.WEST, dateLabel);			
		layout.putConstraint(SpringLayout.NORTH, dayBox, 35, SpringLayout.NORTH, asLabel); //day combo box
		layout.putConstraint(SpringLayout.WEST, dayBox, 15, SpringLayout.EAST, monthBox);			
		layout.putConstraint(SpringLayout.NORTH, yearBox, 35, SpringLayout.NORTH, asLabel); //year combo box
		layout.putConstraint(SpringLayout.WEST, yearBox, 15, SpringLayout.EAST, dayBox);		
		layout.putConstraint(SpringLayout.NORTH, venueLabel, 42, SpringLayout.NORTH, dateLabel); //venue label
		layout.putConstraint(SpringLayout.WEST, venueLabel, 55, SpringLayout.WEST, asPanel);
		layout.putConstraint(SpringLayout.NORTH, venText, 42, SpringLayout.NORTH, dateLabel); //venue text field
		layout.putConstraint(SpringLayout.WEST, venText, 35, SpringLayout.EAST, venueLabel);			
		layout.putConstraint(SpringLayout.NORTH, artistLabel, 42, SpringLayout.NORTH, venueLabel); //artist Label
		layout.putConstraint(SpringLayout.WEST, artistLabel, 55, SpringLayout.WEST, asPanel);	
		layout.putConstraint(SpringLayout.NORTH, artText, 42, SpringLayout.NORTH, venueLabel); //artist text field
		layout.putConstraint(SpringLayout.WEST, artText, 35, SpringLayout.EAST, venueLabel);	
		layout.putConstraint(SpringLayout.NORTH, submit, 17, SpringLayout.SOUTH, artText); //Add button button under artist text field
		layout.putConstraint(SpringLayout.WEST, submit, 75, SpringLayout.EAST, venueLabel);			
		layout.putConstraint(SpringLayout.NORTH, addArt, 17, SpringLayout.SOUTH, artText); //Add addArt button under artist text field
		layout.putConstraint(SpringLayout.WEST, addArt, 25, SpringLayout.EAST, submit);			
	
		
		//this is where the button that adds additional artist text fields is located
		ArrayList<JTextField> tfList = new ArrayList<>(); //declare text field array list
		tfList.add(artText); //add the first artist textfield to the array
		addArt.addActionListener(new ActionListener()  //adding a listener to the button (addArt)
		{
			public void actionPerformed(ActionEvent e)
			{
				tfList.add(new JTextField(20));		//creating the new button and adding to the array list
				int length = tfList.size();			//get length of array list, used to access the last index in the next line 
				asPanel.add(tfList.get(length-1));	//add newly created button to panel
				layout.putConstraint(SpringLayout.NORTH, tfList.get(length-1), 42, SpringLayout.NORTH, tfList.get(length-2)); //position new text field under old one
				layout.putConstraint(SpringLayout.WEST, tfList.get(length-1), 35, SpringLayout.EAST, venueLabel);		
				layout.putConstraint(SpringLayout.NORTH, addArt, 17, SpringLayout.SOUTH, tfList.get(length-1)); //change position of addArt button to under new text field
				layout.putConstraint(SpringLayout.NORTH, submit, 17, SpringLayout.SOUTH, tfList.get(length-1)); //change position of submit button to under new text field
				int npy = py + ((length - 1) * 42); //the size of the panel is updated by getting the length of the array list minus 1 (number of textfields added) multiplying it 42 pixels and then adding it to the original frame height
				asPanel.setPreferredSize(new Dimension(400, npy));
				asF.revalidate(); //refreshes the frame
				asF.repaint();
				asF.pack();
			}
		});
		
		//add listener to the submit button
		//type is a string that will be use to determine whether the event being added is a show or a festival
		submit.addActionListener(new AddListener(monthBox, dayBox, yearBox, venText, tfList, "show", asF));
		
		//add panel to frame, set frame size, set visible 
		asF.add(asPanel);
		asF.setLocationRelativeTo(null);
		asF.setVisible(true);
		asF.pack();
	}
	
	//pop up window for choosing options for the show list
	public void sLFrame() {
		JFrame slF = new JFrame("Show List");  
		
		JPanel slPanel = new JPanel();
		
		slPanel.setLayout(layout);
		
		JLabel slLabel = new JLabel("Show List");
		Font font = new Font("", Font.BOLD,14);
		slLabel.setFont(font);		
		
		JButton b30 = new JButton("Last 30 Days");
		JButton b90 = new JButton("Last 90 Days");
		JButton b180 = new JButton("Last 180 Days");
		JButton b365 = new JButton("Last 365 Days");
		JButton bAll = new JButton("All Time");
		
		slPanel.add(slLabel);
		slPanel.add(b30);
		slPanel.add(b90);
		slPanel.add(b180);
		slPanel.add(b365);
		slPanel.add(bAll);	
		
		layout.putConstraint(SpringLayout.NORTH, slLabel, 17, SpringLayout.NORTH, slPanel);
		layout.putConstraint(SpringLayout.WEST, slLabel, 45, SpringLayout.WEST, slPanel);		
		
		layout.putConstraint(SpringLayout.NORTH, b30, 30, SpringLayout.NORTH, slLabel);
		layout.putConstraint(SpringLayout.WEST, b30, 20, SpringLayout.WEST, slPanel);
		
		layout.putConstraint(SpringLayout.NORTH, b90, 10, SpringLayout.SOUTH, b30);
		layout.putConstraint(SpringLayout.WEST, b90, 20, SpringLayout.WEST, slPanel);
		
		layout.putConstraint(SpringLayout.NORTH, b180, 10, SpringLayout.SOUTH, b90);
		layout.putConstraint(SpringLayout.WEST, b180, 20, SpringLayout.WEST, slPanel);		
		
		layout.putConstraint(SpringLayout.NORTH, b365, 10, SpringLayout.SOUTH, b180);
		layout.putConstraint(SpringLayout.WEST, b365, 20, SpringLayout.WEST, slPanel);
		
		layout.putConstraint(SpringLayout.NORTH, bAll, 10, SpringLayout.SOUTH, b365);
		layout.putConstraint(SpringLayout.WEST, bAll, 20, SpringLayout.WEST, slPanel);		
		
		slF.add(slPanel);
		slF.setSize(100, 275);
		slF.setLocationRelativeTo(null);
		slF.setVisible(true);		
	}
}
