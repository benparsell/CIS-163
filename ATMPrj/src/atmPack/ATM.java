/**************************************
*
* 	@author Ben Parsell
* 	@version Winter 2017
* 
**************************************/

package atmPack;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;


public class ATM {
	
	/* Global hundred dollar bills instance var */
	private int hundreds;
	
	/* Global fifties dollar bills instance var */
	private int fifties;
	
	/* Global twenties dollar bills instance var */
	private int twenties;
	
	/* Global var to hold suspend state */
	private static boolean suspend;
	
	/**************************************
	 * 
	 * 	Default Constructor
	 * 
	 **************************************/
	public ATM() {
		this.hundreds = 0;
		this.fifties = 0;
		this.twenties = 0;
	}
	
	/**************************************
	 * 
	 * 	Constructor with var initialization
	 * @param hundreds
	 * 
	 **************************************/
	public ATM(int hundreds, int fifties, int twenties) {
		if(hundreds >= 0) {
			this.hundreds = hundreds;
		} else {
			throw new IllegalArgumentException();
		}
		if(fifties >= 0) {
			this.fifties = fifties;
		} else {
			throw new IllegalArgumentException();
		}
		if(twenties >= 0) {
			this.twenties = twenties;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**************************************
	 * 
	 * 	ATM constructor
	 * 	@param ATM object
	 * 
	 **************************************/
	public ATM(ATM other) {
		if(other.hundreds >= 0) {
			this.hundreds = other.hundreds;
		}
		if(other.fifties >= 0) {
			this.fifties = other.fifties;

		}
		if(other.twenties >= 0) {
			this.twenties = other.twenties;	
		}
		
	}
	
	/**************************************
	 * 
	 * 	Setter method for hundred dollar bills
	 * 	@param int amount of bills to change to
	 * 
	 **************************************/
	public void setHundreds(int hundreds) {
		if(suspend) {
			return;
		}
		if(hundreds >= 0) {
			this.hundreds = hundreds;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**************************************
	 * 
	 * 	Getter method for hundred dollar bills
	 * 	@param none
	 * 	@return hundred dollar bills
	 * 
	 **************************************/
	public int getHundreds() {
		return this.hundreds; 
	}
	
	/**************************************
	 * 
	 * 	Setter method for fifty dollar bills
	 * 	@param int amount of bills to change to
	 * 
	 **************************************/
	public void setFifties(int fifties) {
		if(suspend) {
			return;
		}
		if(fifties >= 0) {
			this.fifties = fifties;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**************************************
	 * 
	 * 	Getter method for fifty dollar bills
	 * 	@return amount (int) of fifty dollar 
	 * 	bills
	 **************************************/
	public int getFifties() {
		return this.fifties;
	}
	
	/**************************************
	 * 
	 * 	Setter method for twenty dollar bills
	 * 	@param int amount of bills to change to
	 * 
	 **************************************/
	public void setTwenties(int twenties) {
		if(suspend) {
			return;
		}
		if(twenties >= 0) {
			this.twenties = twenties;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**************************************
	 * 
	 * 	Getter method for twenty dollar bills
	 * 	@param none
	 * 	@return Number of twenty dollar 
	 * 	bills
	 **************************************/
	public int getTwenties() {
		return this.twenties;
	}
	
	/**************************************
	 * 
	 * 	Getter method for total currency
	 *  @param none
	 * 	@return double for total currency
	 * 	
	 **************************************/
	public int getAmount() {
		int total = this.hundreds * 100;
		total += this.fifties * 50;
		total += this.twenties * 20;
		return total;
	}
	
	/**************************************************
	 * 
	 * 	Equals method for default object
	 * @param Standard object
	 * @return boolean using other equals method
	 * 
	 *************************************************/
	public boolean equals(Object other) {
		ATM two = (ATM) other;
		return equals(two);
	}
	
	/**************************************************
	 * 
	 * 	Equals method for an ATM object
	 * 	@param ATM object
	 * 	@return Boolean value for if equal or not
	 * 
	 *************************************************/
	public boolean equals(ATM other) {
		if(this.hundreds == other.hundreds && this.fifties == other.fifties && this.twenties == other.twenties) {
			return true;
		} else {
			return false;
		}
	}
	
	/**************************************************
	 * 
	 * 	Equals method for two ATM objects
	 * 	@param ATM objects, two of them
	 *  @return Boolean value for if equal or not
	 * 
	 *************************************************/
	public static boolean equals(ATM other1, ATM other2) {
		if(other1.hundreds == other2.hundreds) {
			if(other1.fifties == other2.fifties) {
				if(other1.twenties == other2.twenties) {
					return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**************************************************
	 * 
	 * 	Compare to method for ATM object
	 * 	@param ATM object
	 * 	@return 1 if "this" is larger, 0 if equal, -1
	 * 	if "that" object is larger
	 * 
	 *************************************************/
	public int compareTo(ATM other) {
		double thisTotal = this.hundreds * 100;
		thisTotal += this.fifties * 50;
		thisTotal += this.twenties * 20;
		
		double thatTotal = other.hundreds *100;
		thatTotal += other.fifties * 50;
		thatTotal += other.twenties * 20;
		
		if(thisTotal > thatTotal) {
			return 1;
		} else if(thisTotal == thatTotal) {
			return 0;
		} else {
			return -1;
		} 
	}
	
	/**************************************************
	 * 
	 * 	Compare to Method for two ATM objects
	 * 	@param Two ATM objects
	 * 	@return 1 if "param1" is larger, 0 if equal, -1
	 * 	if "param2" object is larger
	 * 
	 *************************************************/
	public static int compareTo(ATM other1, ATM other2) {
		double other1Total = other1.hundreds * 100;
		other1Total += other1.fifties * 50;
		other1Total += other1.twenties * 20;
		
		double other2Total = other2.hundreds *100;
		other2Total += other2.fifties * 50;
		other2Total += other2.twenties * 20;
		
		if(other1Total > other2Total) {
			return 1;
		} else if(other1Total == other2Total) {
			return 0;
		} else {
			return -1;
		}
	}
	
	/**************************************************
	 * 
	 * 	Put in method for depositing bills
	 * 	@param hundred, fifty, and twenty dollar bills
	 * 	@return none
	 * 
	 *************************************************/
	public void putIn(int hundreds, int fifties, int twenties) {
		if(suspend) {
			return;
		}
		if(hundreds >= 0) {
			this.hundreds += hundreds;
		} else {
			throw new IllegalArgumentException();
		}
		if(fifties >= 0) {
			this.fifties += fifties;
		} else {
			throw new IllegalArgumentException();
		}
		if(twenties >= 0) {
			this.twenties += twenties;
		} else {
			throw new IllegalArgumentException();
		}
		
		
	}
	
	/**************************************************
	 * 
	 * 	Put in method for depositing bills
	 * 	@param ATM object
	 * 	@return none
	 * 
	 *************************************************/
	public void putIn(ATM other) {
		if(suspend) {
			return;
		}
		this.hundreds += other.hundreds;
		this.fifties += other.fifties;
		this.twenties += other.twenties;
	}
	
	/**************************************************
	 * 
	 * 	Take out method for withdrawing money
	 * 	@param hundred, fifty, and twenty dollar bills
	 * 	@return none
	 * 
	 *************************************************/
	public void takeOut(int hundreds, int fifties, int twenties) {
		if(suspend) {
			return;
		}
		if(hundreds > 0 && fifties > 0 && twenties > 0) {
			if(this.hundreds - hundreds > 0) {
				if(this.fifties - fifties > 0) {
					if(this.twenties - twenties > 0) {
						this.hundreds -= hundreds;
						this.fifties -= fifties;
						this.twenties -= twenties;
					}
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**************************************************
	 * 
	 * 	Take out method for withdrawing money
	 * 	@param ATM object
	 * 	@return none
	 * 
	 *************************************************/
	public void takeOut(ATM other) {
		if(suspend) {
			return;
		}
		if(other.hundreds > 0 && this.hundreds - other.hundreds > 0) {
			if(other.fifties > 0 && this.fifties - other.fifties > 0) {
				if(other.twenties > 0 && this.twenties - other.twenties > 0) {
					this.hundreds -= other.hundreds;
					this.fifties -= other.fifties;
					this.twenties -= other.twenties;
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**************************************************
	 * 
	 * 	Take out method for withdrawing money (E.C.)
	 * 	@param double amount of money
	 * 	@return ATM object is returned with respective
	 * 	bill amounts
	 * 
	 *************************************************/
	public ATM takeOut(double amount) {
		if(suspend) {
			return null;
		}
		// Cycle through each
			ATM temp = new ATM();
			if(amount > 0) {
			while(amount >= 100 && this.hundreds > 0) {
				amount -= 100;
				this.hundreds --;
				temp.hundreds ++;
			}
			while(amount >= 50 && this.fifties > 0) {
				amount -= 50;
				this.fifties --;
				temp.fifties ++;
			}
			while(amount >= 20 && this.twenties > 0) {
				amount -= 20;
				this.twenties --;
				temp.twenties ++;
			}
			
			return temp;
		} else {
			return null;
		}
	}
	
	/**************************************************
	 * 
	 * 	To String method for ATM objects
	 * 	@return String detailing dollar bills
	 * 
	 *************************************************/
	public String toString() {
		String hundreds;
		String fifties;
		String twenties;
		
		if(this.hundreds <= 1) {
			hundreds = " hundred dollar bill \n";
		} else {
			hundreds = " hundred dollar bills \n";
		}
		if(this.fifties == 1) {
			fifties = " fifty dollar bill \n";
		} else {
			fifties = " fifty dollar bills \n";
		}
		if(this.twenties == 1) {
			twenties = " twenty dollar bill \n";
		} else {
			twenties = " twenty dollar bills \n";
		}
		
		return this.hundreds + hundreds + this.fifties + fifties + this.twenties + twenties;
	}
	
	/**************************************************
	 * 
	 * 	Suspend method to halt ATMs
	 * 	@param Boolean value
	 * 	@return none
	 * 
	 *************************************************/
	public static void suspend(Boolean on) {
		suspend = on;
	}
	
	/**************************************************
	 * 
	 * 	Load method to load object props from file
	 * 	@param String filename
	 * 	@return none
	 * 
	 *************************************************/
	public void load(String filename) {
		String readContents;
			
		try{
			// open the data file
			Scanner fileReader = new Scanner(new File(filename)); 


			// read one String in of data and an int
			int hundreds = fileReader.nextInt();
			int fifties = fileReader.nextInt();
			int twenties = fileReader.nextInt();
			
			if(hundreds >= 0) {
				this.hundreds = hundreds;
			}
			
			if(fifties >= 0) {
				this.fifties = fifties;
			}
			if(twenties >= 0) {
				this.twenties = twenties;
			}
			
		}


			// could not find file
		catch(Exception error) {
			System.out.println("File not found ");
		}
	}
	
	/**************************************************
	 * 
	 * 	Save method for saving ATM props to file
	 * 	@param String filename
	 * 	@return none
	 * 
	 *************************************************/
	public void save(String filename) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		String fileContents = this.hundreds + " " + this.fifties + " " + this.twenties; 
		out.println(fileContents);
		out.close();
		}
	
	/**************************************************
	 * 
	 * 	Main Method for testing
	 * 	@param String Array
	 * 	@return none
	 * 
	 *************************************************/
	public static void main(String[] args) {
		ATM s = new ATM(10,2,3);
		System.out.println("Created ChangeJar:$1160, result: " + s.getAmount());
		
		ATM s1 = new ATM();
		System.out.println("\nCreated ChangeJar:$0, result: " + s1.getAmount());
		
		s1.putIn(10,2,3);
		System.out.println("\nAdded ChangeJar:$1160, result: " + s1.getAmount());
		
		ATM s2 = new ATM(10,2,3);
		s2.putIn(0,0,0);
		System.out.println("\nAdded ChangeJar:$1160, result: " + s2.getAmount());
		
		s2 = new ATM(2,1,3);
		ATM temp = s2.takeOut(250);
		System.out.println("\nTake out the following:\n" + temp);
		System.out.println("Remaining ChangeJar:$60, result: " + s2.getAmount());
		
		s2 = new ATM(5,4,3);
		s2.save("pizza");
		s2 = new ATM();
		s2.load("pizza");
		
		if(s2.equals(new ATM(5,4,3))) {
			System.out.println("\nLoad and Save and Equals works!");
		}
		
		System.out.println(s2);
	}
}
