/***********************************************
* 
* GUI for 2048 Game
* @author Ben Parsell
*
**********************************************/

package game1024GUI;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import game1024.*;

public class gameGUI extends JFrame implements KeyListener, ActionListener {
	
	/** Game Object **/
	NumberGame game;
	
	/** 2D Array for Tile Labels **/
	JLabel[][] labelHolder;
	
	/** JPanel for tiles **/
	JPanel tilePanel = new JPanel();
	
	/** Grid for Tiles **/
	GridLayout tileGrid;
	
	/** JPanel for Statistics **/
	JPanel stats = new JPanel();
	
	/** Main Font **/
	Font font = new Font ("Helvetica", 1 , 56);
	
	/** Secondary Font **/
	Font smallFont = new Font ("Helvetica", 1 , 32);
	Color fontColor = new Color(119, 110, 101);
	
	/* Initialize Menu Bar */
    JMenuBar menus;
    
    /* Initialize File tab */
    JMenu fileMenu;
    
    /* Initialize Quit Button for dropdown */
    JMenuItem quitItem;
    
    /** JButtons for Extra Options **/
    JButton resize;
    JButton reset;
    JButton undo;
    
    /** JButtons for Move **/
    JButton up;
    JButton down;
    JButton left;
    JButton right;
    
    /** Board Border RGB Color **/
    Color borderColor = new Color(187, 173, 160);
    
    
    /** Total Moves Label **/
    JLabel totalMovesLabel = new JLabel("");
    
    /** Label for total moves **/
    JLabel movesLabelText = new JLabel("Total Moves :");
    
    /** Score Label **/
    JLabel scoreText = new JLabel("Score :");
    
    /** Display Score **/
    JLabel score;
    
    /** High Score **/
    JLabel highScore;
    
    /** Label for High Score Label **/
    JLabel highScoreLabel;
    
    /** Num Sessions **/
    JLabel sessions;
    
    /** Label for sessions Label **/
    JLabel sessionsLabel;
    
    /*************************************************
     * 
     *	Main Constructor
     * 
     ************************************************/
	public gameGUI() {
		BorderLayout mainLayout = new BorderLayout();
		this.setLayout(mainLayout);
		
		// Add Keyboard Listener
		addKeyListener(this);
		
		// Setup Tile Panel Grid/Main Color
		Color gvsu = new Color(205, 193, 180);
		tilePanel.setBackground(gvsu);
		tileGrid = new GridLayout(4,4);
		tilePanel.setLayout(tileGrid);
		
		// Setup Score Grid
		GridLayout scoreGrid = new GridLayout(4,4);
		this.stats.setLayout(scoreGrid);
		
		// Call initial loop
		this.playLoop();
        
		// Render tiles and other settings
        this.render();
		
        // Setup button pane
		JPanel buttonPanel = new JPanel();
		
		// Focus as true messed with key listener
		up = new JButton("Up");
		up.setFocusable(false);
		
		down = new JButton("Down");
		down.setFocusable(false);
		
		left = new JButton("Left");
		left.setFocusable(false);
		
		right = new JButton("Right");
		right.setFocusable(false);
		
		// Add move buttons to pane
		buttonPanel.add(up);
		buttonPanel.add(down);
		buttonPanel.add(left);
		buttonPanel.add(right);
		
		// Setup extra buttons/functions pane
		JPanel extraButtons = new JPanel();
		
		// Focus messed with key listener
		reset = new JButton("Reset");
		reset.setFocusable(false);
		
		resize = new JButton("Resize");
		resize.setFocusable(false);
		
		undo = new JButton("Undo");
		undo.setFocusable(false);
		
		// Add buttons to pane
		extraButtons.add(reset);
		extraButtons.add(resize);
		extraButtons.add(undo);
		

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
        reset.addActionListener(this);
        undo.addActionListener(this);
        resize.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);
		
		// Add panes to JPanel
		this.add(tilePanel,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.EAST);
		this.add(extraButtons, BorderLayout.SOUTH);
		this.add(stats, BorderLayout.WEST);
		
		// Initial JPanel settings
		this.pack();
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	/*************************************************
     * 
     *	Main Method to launch GUI
     * 
     ************************************************/
	public static void main(String[] args) {
		gameGUI g = new gameGUI();
	}
	
	
	/*************************************************
     * 
     *	Key Typed, needed by Interface. Disregard this
     * 
     ************************************************/
    public void keyTyped(KeyEvent e) {
        
    }
    
    
    /*************************************************
     * 
     *	Key Pressed Action Controller
     *	@param KeyEvent object
     * 
     ************************************************/
    public void keyPressed(KeyEvent e) {
    	if(e.getKeyChar() == 'w') {
    		game.slide(SlideDirection.UP);
    		this.render();
    	}
    	if(e.getKeyChar() == 'a') {
    		game.slide(SlideDirection.LEFT);
    		this.render();
    	}
    	if(e.getKeyChar() == 's') {
    		game.slide(SlideDirection.DOWN);
    		this.render();
    	}
    	if(e.getKeyChar() == 'd') {
    		game.slide(SlideDirection.RIGHT);
    		this.render();
    	}
    	if(e.getKeyCode() == 38) {
    		game.slide(SlideDirection.UP);
    		this.render();
    	}
    	if(e.getKeyCode() == 37) {
    		game.slide(SlideDirection.LEFT);
    		this.render();
    	}
    	if(e.getKeyCode() == 39) {
    		game.slide(SlideDirection.RIGHT);
    		this.render();
    	}
    	if(e.getKeyCode() == 40) {
    		game.slide(SlideDirection.DOWN);
    		this.render();
    	}
    	if (game.getStatus() != GameStatus.IN_PROGRESS) {
            if(game.getStatus() == GameStatus.USER_LOST) {
         	   int result = JOptionPane.showConfirmDialog(this, "Game over. Would you like to start over?", "Game Over", JOptionPane.YES_NO_OPTION);
         	   	switch (result) {
         	   		case 0: 
         	   			game.reset();
         	   			this.render();
         	   			break;
         	   		case 1:
         	   			System.exit(0);
         	   			break;
         	   		case 2:
         	   			System.exit(0);
         	   			break;
         	   	}
            }
            if(game.getStatus() == GameStatus.USER_WON) {
            	int result = JOptionPane.showConfirmDialog(this, "You Won! Would you like to start over?", "You Won!", JOptionPane.YES_NO_OPTION);
         	   	switch (result) {
         	   		case 0: 
         	   			game.reset();
         	   			this.render();
         	   			break;
         	   		case 1:
         	   			System.exit(0);
         	   			break;
         	   		case 2:
         	   			System.exit(0);
         	   			break;
         	   	}
            }
         }
    }
    
    /*************************************************
     * 
     *	Key Released, needed by Interface. 
     *	Disregard this
     * 
     ************************************************/
    public void keyReleased(KeyEvent e) {
    	
    }
    
    /*************************************************
     * 
     *	Play Loop Method, starts game initially
     * 
     ************************************************/
    private void playLoop() {
    	game = new NumberGame();
		game.resizeBoard(4, 4, 2048);
		labelHolder = new JLabel[4][4];
		game.placeRandomValue();
        game.placeRandomValue();
    }
    
    
    /*************************************************
     * 
     *	Render method + Setup settings
     * 
     ************************************************/
    private void render() {
    	
    	// Setup Stats labels/Panel
    	this.stats.removeAll();
    	this.score = new JLabel(game.getScore() + "");
    	this.totalMovesLabel = new JLabel(game.getMoves() + "");
    	this.highScoreLabel = new JLabel("High Score: ");
    	this.highScore = new JLabel(game.getHighScore() + "");
    	this.sessionsLabel = new JLabel("Num Sessions: ");
    	this.sessions = new JLabel(game.getSessions() + "");
    	
    	// Set Label Dimensions
    	this.movesLabelText.setPreferredSize(new Dimension(80,10));
    	this.totalMovesLabel.setPreferredSize(new Dimension(80,10));
    	this.scoreText.setPreferredSize(new Dimension(80,10));
    	this.score.setPreferredSize(new Dimension(80,10));
    	this.highScoreLabel.setPreferredSize(new Dimension(80,10));
    	this.highScore.setPreferredSize(new Dimension(80,10));
    	this.sessionsLabel.setPreferredSize(new Dimension(90,10));
    	this.sessions.setPreferredSize(new Dimension(80,10));
    	
    	// Add all Labels
    	this.stats.add(movesLabelText);
    	this.stats.add(totalMovesLabel);
    	this.stats.add(scoreText);
    	this.stats.add(score);
    	this.stats.add(highScoreLabel);
    	this.stats.add(highScore);
    	this.stats.add(sessionsLabel);
    	this.stats.add(sessions);
    	
    	// Reload Stats Panel
    	this.stats.revalidate();
    	
    	
    	// Copy board and reset tile panel
    	int[][] temp = game.copyBoard();
    	tilePanel.removeAll();
    	
    	// For Loop for 2D Label array
    	for(int i = 0; i <= this.labelHolder.length-1; i++) {
    		for(int j = 0; j <= this.labelHolder[i].length-1; j++) {
    			
    			// Set Each tile value
    			String tempString;
    			if(temp[i][j] == 0) {
    				tempString = "";
    			} else {
    				tempString = String.valueOf(temp[i][j]);
    			}
    			this.labelHolder[i][j] = new JLabel(tempString, SwingConstants.CENTER);
    			
    			// Style tile text
    			this.labelHolder[i][j].setBorder(BorderFactory.createLineBorder(borderColor,7));
    			this.labelHolder[i][j].setFont(font);
    			tilePanel.add(this.labelHolder[i][j]);
    			this.labelHolder[i][j].setPreferredSize(new Dimension(150,150));
    			
    			// Tile color values
    			switch(temp[i][j]) {
    				case 2:
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(238, 228, 218));
    					this.labelHolder[i][j].setForeground(fontColor);
    					if(temp.length > 7 || temp[0].length > 7) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    				case 4: 
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(237, 224, 200));
    					this.labelHolder[i][j].setForeground(fontColor);
    					if(temp.length > 7 || temp[0].length > 7) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    				case 8:
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(242, 177, 121));
    					this.labelHolder[i][j].setForeground(Color.WHITE);
    					if(temp.length > 7 || temp[0].length > 7) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    				case 16:
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(245, 149, 99));
    					this.labelHolder[i][j].setForeground(Color.WHITE);
    					if(temp.length > 6 || temp[0].length > 6) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    				case 32:
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(246, 124, 95));
    					this.labelHolder[i][j].setForeground(Color.WHITE);
    					if(temp.length > 6 || temp[0].length > 6) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    				case 64:
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(246, 94, 59));
    					this.labelHolder[i][j].setForeground(Color.white);
       					if(temp.length > 6 || temp[0].length > 6) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    				case 128:
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(237, 207, 114));
    					this.labelHolder[i][j].setForeground(Color.WHITE);
    					if(temp.length > 5 || temp[0].length > 5) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    				case 256:
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(237, 204, 97));
    					this.labelHolder[i][j].setForeground(Color.white);
    					if(temp.length > 5 || temp[0].length > 5) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    				case 512: 
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(237, 200, 80));
    					this.labelHolder[i][j].setForeground(Color.white);
    					if(temp.length > 5 || temp[0].length > 5) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    				case 1024:
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(237, 197, 63));
    					this.labelHolder[i][j].setForeground(Color.WHITE);
    					if(temp.length > 5 || temp[0].length > 5) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    				case 2048:
    					this.labelHolder[i][j].setOpaque(true);
    					this.labelHolder[i][j].setBackground(new Color(237, 194, 46));
    					this.labelHolder[i][j].setForeground(Color.WHITE);
    					if(temp.length > 5 || temp[0].length > 5) {
    						this.labelHolder[i][j].setFont(smallFont);
    					}
    					break;
    			}
    			
    		}
    	}
    	this.tilePanel.revalidate();
    	
    }
  
    
    /*************************************************
     * 
     *	Action Performed Method for buttons
     *	@param ActionEvent object
     * 
     ************************************************/
    public void actionPerformed(ActionEvent e) {
    	
    	// Found this from (http://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog)
    	JTextField rows = new JTextField(5);
        JTextField columns = new JTextField(5);
        JTextField winningValue = new JTextField(5);
    	
        // Setup new panel to take user input on resize
    	JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Rows"));
        myPanel.add(rows);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Columns"));
        myPanel.add(columns);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel ("Winning Value"));
        myPanel.add(winningValue);
        // End Stack Overflow
    	
        // Setup button "listener"
    	JComponent buttonPressed = (JComponent) e.getSource();
    	
    	// Quit button in menu
    	if(buttonPressed == this.quitItem) {
    		System.exit(0);
    	}
    	
    	// Reset button
    	if(buttonPressed == this.reset) {
    		game.reset();
	   		this.render();
	   		
    	}
    	
    	// Reset button/pane
    	if(buttonPressed == this.resize) {
    		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Row and Col Values", JOptionPane.OK_CANCEL_OPTION);
    		if (result == JOptionPane.OK_OPTION) {
    	         try {
    	        	 if(Integer.parseInt(rows.getText()) > 1 && Integer.parseInt(columns.getText()) > 1) {
    	        		 if(Integer.parseInt(rows.getText()) < 11 && Integer.parseInt(columns.getText()) < 11) {
			    	         this.tileGrid = new GridLayout(Integer.parseInt(rows.getText()),Integer.parseInt(columns.getText()));
			    	         tilePanel.setLayout(tileGrid);
			    	         labelHolder = new JLabel[Integer.parseInt(rows.getText())][Integer.parseInt(columns.getText())];
			
			    	         game.resizeBoard(Integer.parseInt(rows.getText()), Integer.parseInt(columns.getText()), Integer.parseInt(winningValue.getText()));
			    	         game.reset();
			    	         this.render();
    	        		 } else {
    	        			 JOptionPane.showMessageDialog(this.getContentPane(), "Sorry... No larger than 10 :)"); 
    	        		 }
    	        	 } else {
    	        		 JOptionPane.showMessageDialog(this.getContentPane(), "Please Make Rows and Columns greater than 1");
    	        	 }
    	         } catch(Exception excep) {
    	        	 JOptionPane.showMessageDialog(this.getContentPane(), "Please Enter valid numbers");
    	         }
    	         
    	      }
    	}
    	
    	// Undo Button
    	if(buttonPressed == this.undo) {
    		try{
    			game.undo();
    			this.render();
    		} catch(Exception excep) {
    			JOptionPane.showMessageDialog(this.getContentPane(), "There's no move to undo!");
    		}
    	}
    	
    	// Up 
    	if(buttonPressed == this.up) {
    		game.slide(SlideDirection.UP);
    		this.render();
    	}
    	
    	// Down
		if(buttonPressed == this.down) {
			game.slide(SlideDirection.DOWN);
    		this.render();		
		}
		
		// Left
		if(buttonPressed == this.left) {
			game.slide(SlideDirection.LEFT);
    		this.render();
		}
		
		// Right
		if(buttonPressed == this.right) {
			game.slide(SlideDirection.RIGHT);
    		this.render();
		}
    }
    
}
