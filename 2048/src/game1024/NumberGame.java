/***********************************************
* 
* Logic for 2048 Game
* @author Ben Parsell
*
**********************************************/

package game1024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.lang.Exception;

public class NumberGame implements NumberSlider {
	
	/** Board Height **/
	private int height;
	
	/** Board Width **/
	private int width;
	
	/** Board winning value **/
	private int winningValue;
	
	/** 2D Array for Board **/
	private int[][] board;
	
	/** 2D Array for Board **/
	private int[][] refBoard;
	
	/** Cell Object from Cell class **/
	private Cell cell = new Cell();
	
	/** Helper var for if game is won **/
	private boolean gameWon;
	
	/** Helper var for if game is lost **/
	private boolean gameLost;
	
	/** Helper var for if game is ongoing **/
	private boolean in_progress;
	
	/** Stack of 2D arrays for undo **/
	private Stack<int[][]> undoHolder = new Stack<int[][]>();
	
	/** Counter Variable for fun **/
	private int numMoves = 0;
	
	/** Game Score **/
	private int score;
	
	/** Game moves **/
	private int moves;
	
	/** Current Session High Score **/
	private int highScore;
	
	/** Number of Sessions played **/
	private int sessions = 1;
	
	
	
	/***********************************************
	 * 
	 * Default Constructor (Mainly for testing)
	 * 
	 **********************************************/
	public NumberGame() {
		
	}
	
	/***********************************************
	 * 
	 * Constructor (Mainly for testing)
	 * @param height The height of the board
	 * @param width the width of the board
	 * @param winningValue tile score to win
	 * 
	 **********************************************/
	public NumberGame(int height, int width, int winningValue) {
		this.gameLost = false;
		this.gameWon = false;
		this.in_progress = true;
		
		this.height = height;
		this.width = width;
		this.winningValue = winningValue;
		this.board = new int[height][width];
	}
	
	/***********************************************
	 * 
	 * Resize Method for board
	 * @param height The height of the board
	 * @param width the width of the board
	 * @param winningValue tile score to win
	 * @throws IllegalArgumentException if winning
	 * value is not power of 2
	 * 
	 **********************************************/
	public void resizeBoard(int height, int width, int winningValue) throws IllegalArgumentException {
		this.gameLost = false;
		this.gameWon = false;
		this.in_progress = true;
		
		this.height = height;
		this.width = width;
		if((winningValue & -winningValue) == winningValue) {
			this.winningValue = winningValue;
		} else {
			throw new IllegalArgumentException();
		}
		
		this.board = new int[height][width];
	}
	
	/***********************************************
	 * 
	 * Getter for game Score
	 * @return int score
	 * 
	 **********************************************/
	public int getScore() {
		return this.score;
	}
	
	/***********************************************
	 * 
	 * Getter for High Score
	 * @return int high score
	 * 
	 **********************************************/
	public int getHighScore() {
		return this.highScore;
	}
	
	/***********************************************
	 * 
	 * Getter for game Moves
	 * @return number of moves
	 * 
	 **********************************************/
	public int getMoves() {
		return this.moves;
	}
	
	/***********************************************
	 * 
	 * Getter for sessions
	 * @return number of sessions
	 * 
	 **********************************************/
	public int getSessions() {
		return this.sessions;
	}
	
	/***********************************************
	 * 
	 * Reset method to reset initial values
	 * 
	 **********************************************/
	public void reset() {
		this.resizeBoard(this.height, this.width, this.winningValue);
		this.in_progress = true;
		this.gameWon = false;
		this.gameLost = false;
		this.placeRandomValue();
		this.placeRandomValue();
		this.moves = 0;
		if(this.score > this.highScore) {
			this.highScore = this.score;
		}
		this.score = 0;
		this.sessions++;
	}
	
	/***********************************************
	 * 
	 * Set Values Method for new 2D array
	 * @param 2D array of board values
	 * 
	 **********************************************/
	public void setValues(final int[][] ref) {
		for(int i = 0; i <  ref.length; i++) {
			for(int j = 0; j < ref[i].length; j++) {
				this.board[i][j] = ref[i][j];
			}
		}
	}
	
	
	/***********************************************
	 * 
	 * Helper Method to test if game has been won
	 * @return boolean for if game is won
	 * 
	 **********************************************/
	private boolean gameWon() {
		for(int j = 0; j <= this.board.length-1; j++) {
			for(int i = 0; i <= this.board[j].length-1; i++) {
				if(this.board[j][i] == this.winningValue) {
					this.in_progress = false;
					this.gameWon = true;
					return true;
				}
			}
		}
		return false;
	}
	
	/***********************************************
	 * 
	 * Helper Method to undo last move
	 * @throws IllegalStateException if stack empty
	 * 
	 **********************************************/
	public void undo() throws IllegalStateException {
		
		if(this.undoHolder.isEmpty()) {
			throw new IllegalStateException();
		}
		if(this.moves > 0) {
			this.moves--;
		}
		int[][] swap = new int[this.height][this.width];
		swap = this.undoHolder.peek();
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board[i].length; j++) {
				this.board[i][j] = swap[i][j];
			}
		}
		this.undoHolder.pop();
		
		
	}
	
	/***********************************************
	 * 
	 * Helper Method to copy the game board
	 * @return 2D array for a board
	 * 
	 **********************************************/
	public int[][] copyBoard() {
		int[][] temp = new int[this.board.length][this.board[0].length];
		for(int j = this.board.length - 1; j >= 0; j--){
            for (int i = 0; i <= this.board[j].length - 1; i++){
            	temp[j][i] = this.board[j][i];
            }
		}
		return temp;
	}
	
	
	/***********************************************
	 * 
	 * Method to get current game status
	 * @return an enum for gamestatus
	 * 
	 **********************************************/
	public GameStatus getStatus() {
		if(in_progress == true) {
			return GameStatus.IN_PROGRESS;
		}
		if(gameWon == true && in_progress == false) {
			return GameStatus.USER_WON;
		}
		if(gameLost == true && in_progress == false) {
			return GameStatus.USER_LOST;
		}
		return GameStatus.IN_PROGRESS;
	}
	
	/***********************************************
	 * 
	 * Helper Method to see if player can move
	 * @return boolean for if moves available
	 * 
	 **********************************************/
	private boolean canMove() {
		for(int j = 0; j < this.board.length; j++){
            for (int i = 0; i < this.board[j].length; i++){
            	if(this.board[j][i] == 0) {
            		return true;
            	}
            	if(this.board[j][i] != 0 && j != this.board.length-1){
                    if (this.board[j][i] == this.board[j+1][i]){
                    	return true;
                    }
				}
            	if (this.board[j][i] != 0 && i != this.board[j].length-1){
                    if (this.board[j][i] == this.board[j][i+1]){
                    	return true;
                    }
				}
                
			}
		}
		this.gameLost = true;
		this.in_progress = false;
		return false;
	}
	
	/***********************************************
	 * 
	 * Helper method for undo - copies values from
	 * main board
	 * @return 2D integer array
	 * 
	 **********************************************/
	private int[][] undoHelper() {
		int swap[][] = new int[this.board.length][this.board[0].length];
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board[0].length; j++) {
				swap[i][j] = this.board[i][j];
			}
		}
		return swap;
	}
	
	/***********************************************
	 * 
	 * Helper method to see if move is valid
	 * @return boolean for if valid move
	 * 
	 **********************************************/
	private boolean isValidMove() {
		if(Arrays.deepEquals(this.refBoard, this.board)) {
			return false;
		}
		return true;
	}
	
	/***********************************************
	 * 
	 * Slide Method that calls helper methods
	 * @return boolean if direction can slide
	 * @param enum slide direction value
	 * 
	 **********************************************/
	public boolean slide(SlideDirection dir) {
			if(dir == SlideDirection.LEFT) {
				this.undoHolder.push(this.undoHelper());
				this.combineLeft();
				
				refBoard = this.copyBoard();
				this.canMove();
				this.gameWon();
				this.numMoves++;
				return true;
			}
			if(dir == SlideDirection.RIGHT) {
				this.undoHolder.push(this.undoHelper());
				this.combineRight();
				
				refBoard = this.copyBoard();
				this.gameWon();
				this.canMove();
				this.numMoves++;
				return true;
			}
			if(dir == SlideDirection.DOWN) {
				this.undoHolder.push(this.undoHelper());
				this.combineDown();
				
				refBoard = this.copyBoard();
				this.gameWon();
				this.canMove();
				this.numMoves++;
				return true;
			}
			if(dir == SlideDirection.UP) {
				this.undoHolder.push(this.undoHelper());
				this.combineUp();
				refBoard = this.copyBoard();
				this.gameWon();
				this.canMove();
				this.numMoves++;
				return true;
			}
		return false;
	}
	
	/***********************************************
	* 
	* Helper Method for Sliding Left
	* 
	**********************************************/
	private void slideLeft() {
		for (int j = 0; j <= this.board.length - 1; j++){
            for (int i = 0; i <= this.board[j].length - 1; i++){
                for (int x = i; x > 0; x--){
                    if (this.board[j][x] != 0 && x != 0){
                        if (this.board[j][x-1] == 0){
                            this.board[j][x-1] = this.board[j][x];
                            this.board[j][x] = 0;
                        }
                    }
                 }     
             }
         }
	}
	
	/***********************************************
	* 
	* Helper Method for Sliding Right
	* 
	**********************************************/
	private void slideRight() {
		for (int j = this.board.length - 1; j >= 0; j--){
            for (int i = this.board[j].length - 1; i >= 0; i--){
                for (int x = i; x < this.board[j].length - 1; x++){
                    if (this.board[j][x] != 0 && x != this.board[j].length - 1){
                        if (this.board[j][x+1] == 0){
                            this.board[j][x+1] = this.board[j][x];
                            this.board[j][x] = 0;
                        }
                    }
                 }     
             }
         }
	
	}
	
	/***********************************************
	* 
	* Helper Method for Sliding Up
	* 
	**********************************************/
	private void slideUp() {
		for (int j = 0; j <= this.board.length - 1; j++){
            for (int i = 0; i <= this.board[j].length - 1; i++){
                for (int x = j; x > 0; x--){
                    if (this.board[x][i] != 0 && x != 0){
                        if (this.board[x-1][i] == 0){
                            this.board[x-1][i] = this.board[x][i];
                            this.board[x][i] = 0;
                        }
                    }
                 }     
             }
         }
		
	}
	
	
	/***********************************************
	 * 
	 * Helper Method for Sliding Down
	 * 
	 **********************************************/
	private void slideDown() {
		for(int j = this.board.length - 1; j >= 0; j--){
            for (int i = this.board[j].length - 1; i >= 0; i--){
                for (int x = j; x < this.board.length - 1; x++){
                    if (this.board[x][i] != 0 && x != this.board.length - 1){
                        if (this.board[x+1][i] == 0){
                            this.board[x+1][i] = this.board[x][i];
                            this.board[x][i] = 0;
                        }
                    }
                 }     
             }
         }
	}
	
	/***********************************************
	 * 
	 * Helper method that calls slide down, and 
	 * checks for combinations (merges)
	 * 
	 **********************************************/
	private void combineDown(){
        this.slideDown();
        for (int j = this.board.length - 1; j >= 0; j--){
            for (int i = 0; i <= this.board[j].length - 1; i++){
                if (this.board[j][i] != 0 && j != 0){
                    if (this.board[j][i] == this.board[j-1][i]){
                    	this.score = this.score + (this.board[j][i]*2);
                        this.board[j][i] = this.board[j-1][i] * 2;
                        this.board[j-1][i] = 0;
                    }
                }
            }
        }
        
        this.slideDown();
        if(this.isValidMove()) {
        	this.moves++;
        	this.placeRandomValue();
        }
    }
	
	/***********************************************
	 * 
	 * Helper method that calls slide left, and 
	 * checks for combinations (merges)
	 * 
	 **********************************************/
	private void combineLeft(){
        this.slideLeft();
        for(int j = this.board.length - 1; j >= 0; j--){
            for (int i = 0; i <= this.board[j].length - 1; i++){
                if (this.board[j][i] != 0 && i != this.board[j].length - 1){
                    if (this.board[j][i] == this.board[j][i+1]){
                    	this.score = this.score + (this.board[j][i]*2);
                        this.board[j][i] = this.board[j][i+1] * 2;
                        this.board[j][i+1] = 0;
                    }
                }
            }
        }
        this.slideLeft();
        if(this.isValidMove()) {
        	this.moves++;
        	this.placeRandomValue();
        }
    }
	
	/***********************************************
	 * 
	 * Helper method that calls slide right, and 
	 * checks for combinations (merges)
	 * 
	 **********************************************/
	private void combineRight(){
        this.slideRight();
        for (int j = 0; j <= this.board.length - 1; j++){
            for (int i = this.board[j].length - 1; i >= 0; i--){
                if (this.board[j][i] != 0 && i != 0){
                    if (this.board[j][i] == this.board[j][i-1]){
                    	this.score = this.score + (this.board[j][i]*2);
                        this.board[j][i] = this.board[j][i-1] * 2;
                        this.board[j][i-1] = 0;
                    }
                }
            }
        }
        this.slideRight();
        if(this.isValidMove()) {
        	this.moves++;
        	this.placeRandomValue();
        }
    }
	
	/***********************************************
	 * 
	 * Helper method that calls slide up, and 
	 * checks for combinations (merges)
	 * 
	 **********************************************/
	private void combineUp(){
        this.slideUp();
        for(int j = 0; j <= this.board.length - 1; j++){
            for (int i = this.board[j].length - 1; i >= 0; i--){
                if (this.board[j][i] != 0 && j != this.board.length - 1){
                    if (this.board[j][i] == this.board[j+1][i]){
                    	this.score = this.score + (this.board[j][i]*2);
                        this.board[j][i] = this.board[j+1][i] * 2;
                        this.board[j+1][i] = 0;
                    }
                }
            }
        }
        this.slideUp();
        if(this.isValidMove()) {
        	this.moves++;
        	this.placeRandomValue();
        }
    }
	
	/***********************************************
	 * 
	 * Method to get non-empty tiles
	 * @return an arraylist of cells that are empty
	 * 
	 **********************************************/
	public ArrayList<Cell> getNonEmptyTiles() {
		// Should be good, ready to test
		Cell temp;
		ArrayList<Cell> nonEmpty = new ArrayList<Cell>();
		
		
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				if(this.board[i][j] != 0) {
					temp = new Cell(i, j, this.board[i][j]);
					nonEmpty.add(temp);
				}
			}
		}
		return nonEmpty;
	}
	
	/***********************************************
	 * 
	 * Method to place random value on board
	 * @return Cell with coordinates of value
	 * @throws IllegalStateException if no cell 
	 * available
	 * 
	 **********************************************/
	public Cell placeRandomValue() throws IllegalStateException {
		// Needs logic gate to ensure tile is actually empty
		Random rnd = new Random();
		if(!(this.isValidMove())) {
			throw new IllegalStateException();
		} else {
		try {
			
			int randCol = rnd.nextInt(this.board.length);
			int randRow = rnd.nextInt(this.board[0].length);
			
			while(this.board[randCol][randRow] != 0) {
				randCol = rnd.nextInt(this.board.length);
				randRow = rnd.nextInt(this.board[0].length);	
			}
			
			/*
			int exponent = (int) (Math.log(this.winningValue)/Math.log(2));
			double randomValue = (double) rnd.nextInt(exponent+1);
			int finalValue = (int) Math.pow(2, randomValue);
			*/
			// The above is for the original instructions, which are stupid. I'm using 2's or 4's like the normal game
			
			int randomValue = rnd.nextInt(12);
			int finalValue = 2;
			
			if(randomValue > -1 && randomValue < 6) {
				 finalValue = 2;
			} else if(randomValue > 6 && randomValue < 12) {
				 finalValue = 4;
			}
			
			this.board[randCol][randRow] = finalValue;
			
			
			//System.out.println(this.board[0][3]);
			//System.out.println(this.board[2][3]);
			
			this.cell = new Cell(randCol, randRow, finalValue);
			return cell;
			
		} catch(Exception e) {
			
		}
		}
		return null;
	}
}
