import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddListener implements ActionListener{

	//local components to be associated with components from ButtonListener
	JComboBox mBox, dBox, yBox;
	JTextField vText;
	ArrayList<JTextField> tfL;
	
	//strings to be used for output
	String month, day, year, venue;
	ArrayList<String> artistList = new ArrayList<>();
	
	//constructor takes the components from ButtonListener and associates them with local variables
	public AddListener(JComboBox monthBox, JComboBox dayBox, JComboBox yearBox, JTextField venText,
			ArrayList<JTextField> tfList) {
		mBox = monthBox;
		dBox = dayBox;
		yBox = yearBox;
		vText = venText;
		tfL = tfList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//getting the selected month, date and year from the combo boxes
		//as well as the venue and artists entered into the text fields
		month = (String)mBox.getSelectedItem();
		day = (String)dBox.getSelectedItem();
		year = (String)yBox.getSelectedItem();
		venue = vText.getText();
		
		//populated artistList with Strings from the artist text fields
		String out = ""; //out is all artists enter concatenate together into a string for easy output
		for(int i = 0; i < tfL.size(); i++)
		{
			artistList.add(tfL.get(i).getText());
			out += tfL.get(i).getText() + ", ";
		}
		JOptionPane.showMessageDialog(null, "month: " + month + ", day: " + day + ", year: " + year + "\nvenue: " + venue + ", artist(s): " + out);
		
	}

}
