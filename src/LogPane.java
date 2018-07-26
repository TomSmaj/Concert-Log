import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class LogPane extends JPanel {
	
	public LogPane() throws IOException {
		initialize();
	}

	public void initialize() throws IOException {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		JButton bAddShow = new JButton("Add Show");
		JButton bAddFest = new JButton("Add Festival");
		JButton bShowList = new JButton("Show List");
		JButton bFestList = new JButton("Festival List");
		JButton bTopArt = new JButton("Top Artists");
		JButton bTopVen = new JButton("Top Venues");
		JButton bClear = new JButton("Clear");
		
		add(bAddShow);
		add(bAddFest);
		add(bShowList);
		add(bFestList);
		add(bTopArt);
		add(bTopVen);
		add(bClear);
		
		bAddShow.addActionListener(new ButtonListener());
		bAddFest.addActionListener(new ButtonListener());
		bShowList.addActionListener(new ButtonListener());
		bFestList.addActionListener(new ButtonListener());
		bTopArt.addActionListener(new ButtonListener());
		bTopVen.addActionListener(new ButtonListener());		
		
		JTextArea textArea = new JTextArea();
        textArea.setColumns(52);
        textArea.setLineWrap(true);
        textArea.setRows(45);
        textArea.setWrapStyleWord(true);		
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		add(scrollPane);
		
		layout.putConstraint(SpringLayout.NORTH, bAddShow, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, bAddShow, 20, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.NORTH, bAddFest, 10, SpringLayout.SOUTH, bAddShow);
		layout.putConstraint(SpringLayout.WEST, bAddFest, 20, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.NORTH, bShowList, 10, SpringLayout.SOUTH, bAddFest);
		layout.putConstraint(SpringLayout.WEST, bShowList, 20, SpringLayout.WEST, this);		
		
		layout.putConstraint(SpringLayout.NORTH, bFestList, 10, SpringLayout.SOUTH, bShowList);
		layout.putConstraint(SpringLayout.WEST, bFestList, 20, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.NORTH, bTopArt, 10, SpringLayout.SOUTH, bFestList);
		layout.putConstraint(SpringLayout.WEST, bTopArt, 20, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.NORTH, bTopVen, 10, SpringLayout.SOUTH, bTopArt);
		layout.putConstraint(SpringLayout.WEST, bTopVen, 20, SpringLayout.WEST, this);	
		
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, scrollPane, 115, SpringLayout.WEST, bAddShow);			
		
		layout.putConstraint(SpringLayout.NORTH, bClear, 10, SpringLayout.SOUTH, scrollPane);
		layout.putConstraint(SpringLayout.WEST, bClear, 285, SpringLayout.WEST, bAddShow);				
		
	}
	
}
