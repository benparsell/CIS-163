/**************************************
*
* 	@author Ben Parsell
* 	@version Winter 2017
* 
**************************************/

package atmPack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;


public class MyATMPanel extends JFrame implements ActionListener{
	ATM a;
	ATM b;
	ATM c;
    
	/* Initialize Buttons */
	JButton sus;
    JButton incrementHundreds;
    JButton incrementFifties;
    JButton incrementTwenties;
    JButton decrementHundreds;
    JButton decrementFifties;
    JButton decrementTwenties;
	
    /* Initialize Text Areas for each ATM */
    JTextArea results1;
    JTextArea results2;
    JTextArea results3;
    
    /* Initialize Menu Bar */
    JMenuBar menus;
    
    /* Initialize File tab */
    JMenu fileMenu;
    
    /* Initialize Quit Button for dropdown */
    JMenuItem quitItem;
    
    /* Boolean for changing button color */
    private Boolean suspended = false;
    

    /******************************************************
     * 
     * Main Method - Initializes GUI
     * @param String array
     ******************************************************/
    public static void main(String[] args) {
    	MyATMPanel gui = new MyATMPanel();
    	
    	gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	gui.setTitle("Java ATM (Ben Parsell)");
        gui.pack();
        gui.setVisible(true);
    	
    }
    
    /******************************************************
     * 
     * GUI Constructor
     * Places/locates buttons and text areas
     * 
     ******************************************************/
    public MyATMPanel() {
    	a = new ATM(1,3,5);
    	b = new ATM(10,7,2);
    	c = new ATM(0,0,0);
    	
    	// Instantiate grid
    	setLayout(new GridBagLayout());
    	GridBagConstraints loc = new GridBagConstraints();
    	
    	// ATM 1 Pane
    	results1 = new JTextArea(25,20);
        JScrollPane scrollPane = new JScrollPane(results1);
        loc.gridx = 0;
        loc.gridy = 1;
        loc.gridheight = 12;
        loc.gridwidth = 10;
        loc.insets.left = 20;
        loc.insets.bottom = 5;
        add(scrollPane, loc);
        loc = new GridBagConstraints();
        
        // ATM 2 Pane
        results2 = new JTextArea(25,20);
        JScrollPane scrollPane2 = new JScrollPane(results2);
        loc.gridx = 10;
        loc.gridy = 1;
        loc.gridheight = 12;
        loc.gridwidth = 10;
        loc.insets.left = 20;
        loc.insets.right = 20;
        loc.insets.bottom = 5;
        add(scrollPane2, loc);
        loc = new GridBagConstraints();
        
        // ATM 3 Pane
        results3 = new JTextArea(25,20);
        JScrollPane scrollPane3 = new JScrollPane(results3);
        loc.gridx = 20;
        loc.gridy = 1;
        loc.gridheight = 12;
        loc.gridwidth = 10;
        loc.insets.left = 20;
        loc.insets.right = 20;
        loc.insets.bottom = 5;
        add(scrollPane3, loc);
        loc = new GridBagConstraints();

        // create results label
        loc.gridx = 8;
        loc.gridy = 0;
        loc.insets.bottom = 20;
        loc.insets.top = 10;
        add(new JLabel("ATM 1"), loc);
        loc = new GridBagConstraints();
        
        loc.gridx = 13;
        loc.gridy = 0;
        loc.insets.bottom = 20;
        loc.insets.top = 10;
        add(new JLabel("ATM 2"), loc);
        loc = new GridBagConstraints();
        
        loc.gridx = 29;
        loc.gridy = 0;
        loc.insets.bottom = 20;
        loc.insets.top = 10;
        add(new JLabel("ATM 3"), loc);
        loc = new GridBagConstraints();
        
        // Suspend Button
        sus = new JButton("Suspend");
        loc.gridx = 7;
        loc.gridy= 13;
        loc.gridwidth = 1;
        loc.insets.right = 10;
        loc.insets.left = 10;
        add(sus, loc);
        loc = new GridBagConstraints();
        
        // Add Hundreds button
        incrementHundreds = new JButton("+ Hundreds");
        loc.gridx = 8;
        loc.gridy= 13;
        loc.gridwidth = 1;
        loc.insets.right = 10;
        add(incrementHundreds, loc);
        loc = new GridBagConstraints();
        
        // Add Fifties button
        incrementFifties = new JButton("+ Fifties");
        loc.gridx = 9;
        loc.gridy= 13;
        loc.gridwidth = 1;
        loc.insets.right = 10;
        add(incrementFifties, loc);
        loc = new GridBagConstraints();
        
        // Add twenties button
        incrementTwenties = new JButton("+ Twenties");
        loc.gridx = 10;
        loc.gridy= 13;
        loc.gridwidth = 1;
        loc.insets.right = 10;
        add(incrementTwenties, loc);
        loc = new GridBagConstraints();
        
        decrementHundreds = new JButton("- Hundreds");
        loc.gridx = 8;
        loc.gridy= 14;
        loc.gridwidth = 1;
        loc.insets.right = 10;
        loc.insets.bottom = 10;
        loc.insets.top = 10;
        add(decrementHundreds, loc);
        loc = new GridBagConstraints();
        
        decrementFifties = new JButton("- Fifties");
        loc.gridx = 9;
        loc.gridy= 14;
        loc.gridwidth = 1;
        loc.insets.right = 10;
        loc.insets.bottom = 10;
        loc.insets.top = 10;
        add(decrementFifties, loc);
        loc = new GridBagConstraints();
        
        decrementTwenties = new JButton("- Twenties");
        loc.gridx = 10;
        loc.gridy= 14;
        loc.gridwidth = 1;
        loc.insets.right = 10;
        loc.insets.bottom = 10;
        loc.insets.top = 10;
        add(decrementTwenties, loc);
        loc = new GridBagConstraints();
        
        
        // Instantiate File menu items
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);
        
        // Add Action Listeners
        fileMenu.addActionListener(this);
        quitItem.addActionListener(this);
        sus.addActionListener(this);
        incrementHundreds.addActionListener(this);
        incrementFifties.addActionListener(this);
        incrementTwenties.addActionListener(this);
        decrementHundreds.addActionListener(this);
        decrementFifties.addActionListener(this);
        decrementTwenties.addActionListener(this);

        // Call for initial text in text panes
    	this.atmResults();

    }
    
    /**************************************************
	 * 
	 * 	Method for results pane text
	 * 	@return none
	 * 
	 *************************************************/
    public void atmResults() {
    	results1.append("Hundreds: " + a.getHundreds() + "\n");
    	results1.append("Fifties: " + a.getFifties() + "\n");
    	results1.append("Twenties: " + a.getTwenties());
    	
    	results2.append("Hundreds: " + b.getHundreds() + "\n");
    	results2.append("Fifties: " + b.getFifties() + "\n");
    	results2.append("Twenties: " + b.getTwenties());
    	
    	results3.append("Hundreds: " + c.getHundreds() + "\n");
    	results3.append("Fifties: " + c.getFifties() + "\n");
    	results3.append("Twenties: " + c.getTwenties());
    }
    
    /**************************************************
	 * 
	 * 	Action performed method
	 * 	@param ActionEvent object
	 * 	@return none
	 * 
	 *************************************************/
    public void actionPerformed(ActionEvent e) {
    	JComponent buttonPressed = (JComponent) e.getSource();
        
    	// Suspend button listener
        if(buttonPressed == sus) {
        	if(suspended) {
        		ATM.suspend(false);
            	sus.setBackground(null); 
            	incrementHundreds.setEnabled(true);
            	incrementFifties.setEnabled(true);
            	incrementTwenties.setEnabled(true);
            	decrementHundreds.setEnabled(true);
            	decrementFifties.setEnabled(true);
            	decrementTwenties.setEnabled(true);
            	suspended = false;
        	} else {
        		ATM.suspend(true);
            	sus.setBackground(Color.red); 
            	incrementHundreds.setEnabled(false);
            	incrementFifties.setEnabled(false);
            	incrementTwenties.setEnabled(false);
            	decrementHundreds.setEnabled(false);
            	decrementFifties.setEnabled(false);
            	decrementTwenties.setEnabled(false);
            	suspended = true;
        	}
        	
        }
        
        // Quit menu button
        if(buttonPressed == quitItem) {
        	System.exit(1);
        }
        
        // Increment Hundreds listener
        if(buttonPressed == incrementHundreds) {
        	a.setHundreds(a.getHundreds()+1);
        	b.setHundreds(b.getHundreds()+1);
        	c.setHundreds(c.getHundreds()+1);
        	
        	results1.setText("");
        	results2.setText("");
        	results3.setText("");
        	this.atmResults();
        }
        
        // Increment Fifties Listener
        if(buttonPressed == incrementFifties) {
        	a.setFifties(a.getFifties()+1);
        	b.setFifties(b.getFifties()+1);
        	c.setFifties(c.getFifties()+1);
        	
        	results1.setText("");
        	results2.setText("");
        	results3.setText("");
        	
        	this.atmResults();
        }
        
        // Increment Twenties Listener
        if(buttonPressed == incrementTwenties) {
        	a.setTwenties(a.getTwenties()+1);
        	b.setTwenties(b.getTwenties()+1);
        	c.setTwenties(c.getTwenties()+1);
        	
        	results1.setText("");
        	results2.setText("");
        	results3.setText("");
        	
        	this.atmResults();
        }
        
        //  Decrement Hundreds listener
        if(buttonPressed == decrementHundreds) {
        	a.setHundreds(a.getHundreds()-1);
        	b.setHundreds(b.getHundreds()-1);
        	c.setHundreds(c.getHundreds()-1);
        	
        	results1.setText("");
        	results2.setText("");
        	results3.setText("");
        	
        	this.atmResults();
        }
        
        // Decrement Fifties listener
        if(buttonPressed == decrementFifties) {
        	a.setFifties(a.getFifties()-1);
        	b.setFifties(b.getFifties()-1);
        	c.setFifties(c.getFifties()-1);
        	
        	results1.setText("");
        	results2.setText("");
        	results3.setText("");
        	
        	this.atmResults();
        }
        
        // Decrease Twenties Button
        if(buttonPressed == decrementTwenties) {
        	a.setTwenties(a.getTwenties()-1);
        	b.setTwenties(b.getTwenties()-1);
        	c.setTwenties(c.getTwenties()-1);
        	
        	results1.setText("");
        	results2.setText("");
        	results3.setText(""); 
        	
        	this.atmResults();
        	
        }
    }
    
   
}


