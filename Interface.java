package Assignment2;

import javax.swing.JOptionPane;

//-----------------------------------------------------------------------------------------
/*
* Authors: Benjamin Young and Ryan McCormack
* Student ID's: c3330653 c3329771
* Email: BenjaminYoung7198@gmail.com  ryanmack@bigpond.com
* Requirements: 
* 	javax.swing.JOptionPane
*	Depot.java
*/
//-----------------------------------------------------------------------------------------


public class Interface {
	
	//Variable/Object Types of class Interface
	private static Depot[] DepotArr;
	
	//------------------------------------//
	public static void main(String[] args) {
		Interface GUI = new Interface();
		GUI.run();
		System.exit(0);
	}
	//------------------------------------//
	
	public void run() {
		String menuChoice = "";//used to carry mainMenu() return
		boolean exit = true; //Used for menu loop
		
		//Initialize depot array
		DepotArr = new Depot[4];
		
		//Initialize depot objects
		for(int count = 0; count < DepotArr.length; count++) {
			DepotArr[count] = new Depot();
		}
//------------------------------------------------------------------
		while (exit) {
			//Menu GUI
			menuChoice = mainMenu();
			
			if(menuChoice.equals("exit")) {
				return;
			}
			else if(menuChoice.equals("1")) {
				addDepot();
				continue;
			}
			else if(menuChoice.equals("2")) {
				removeDepot();
				continue;
			}
			else if(menuChoice.equals("3")) {
				addProduct();
				continue;
			}
			else if(menuChoice.equals("4")) {
				removeProduct();
				continue;
			}
			else if(menuChoice.equals("5")) {
				listDepot();
				continue;
			}
			else if(menuChoice.equals("6")) {
				listProduct();
				continue;
			}
			else if(menuChoice.equals("7")) {
				lookForProduct();
				continue;
			}
			else if(menuChoice.equals("8")) {
				valueOfDepot();
				continue;
			}
			else if(menuChoice.equals("9")) {
				writeToFile();
				continue;
			}
			else if(menuChoice.equals("10")) {
				readfromFile();
				continue;
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid menu choice selected.\nPlease enter a number 1-10\nClick on cancel to exit program.", "Information", JOptionPane.INFORMATION_MESSAGE);
				continue;
			}
		}
	}

//------------------------------------------------------------------
//************************Flow control******************************
//------------------------------------------------------------------
	
	/**
	 * @param, Nothing
	 * @returns User menu choice as String, "exit" to system exit, Num if valid
	 */
	public String mainMenu() {
		
		//String Num for user input
		//Int num for return value
		String Num = "";
		boolean exit = true;
		int val;
		
		while(exit) {
			//Request UI
			Num = JOptionPane.showInputDialog(null, "Enter number corresponding to menu choice:\nPress cancel to exit.\n1: Add Depot\n2: Remove Depot\n3: Add Product\n4: Remove products\n5: List existing depots\n6: List products in a depot\n7: Search for product\n8: Collective value of depot\n9: Write to file (Save data)\n10: Read from file (Load save)");
			
			//Check to see if input is valid
			val = isValid(Num);
			
			//Respond accordingly
			if(val == 0) {
				return "exit";
			}
			
			Num = Num.replace(" ", "");
			
			if (val == 1) {
				continue;
			}
			
			exit = false;
		}
		return Num;
	}

//------------------------------------------------------------------
//------------------------------------------------------------------
	/**@param Nothing
	 * @Return Nothing
	 * Executes addDepot menu and flow control
	 */
	public void addDepot() {
		String Name;
		boolean exit = true;
		int val;//used to carry method returns
		
		//Look to see if a empty depot object is available
		if(freeDepotCount() == 0) {
			JOptionPane.showMessageDialog(null, "4 depots already exist!", "Notice", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		while(exit) {
			Name = JOptionPane.showInputDialog(null, "Enter name of Depot you wish to add:");
			
			val = isValid(Name);//is it valid
			
			if(val == 0) {//Did they click cancel
				exit = false;
				continue;
			}
			else if(val == 1) {
				continue;
			}
			
			Name = Name.toLowerCase();
			
			//TODO: See if exists
			if(doesDepotExist(Name)) {
				JOptionPane.showMessageDialog(null, "That depot already exists!", "Notice", JOptionPane.INFORMATION_MESSAGE);
				continue;
			}
			
			DepotArr[avalableDepot()].writeName(Name);
			
			for(int count = 0; count < 4; count++) {
				System.out.println(DepotArr[count].readName());
			}
			
			exit = false;
		}
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void removeDepot() {}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void addProduct() {}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void removeProduct() {}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void listDepot() {}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void listProduct() {}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void lookForProduct() {}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void valueOfDepot() {}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void writeToFile() {}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void readfromFile() {}
	
//------------------------------------------------------------------
//**********************Program Methods*****************************
//------------------------------------------------------------------
	
	/**
	 * @param Takes string
	 * @returns int, 0 for null, 1 for invalid/no, 2 for valid
	 */
	public int isValid(String input) {
		if(input == null) {
			return 0;
		}
		
		input = input.replace(" ", "");
		
		if(input.equals("")) {
			JOptionPane.showMessageDialog(null, "No input detected, please enter valid input!", "WARNING", JOptionPane.WARNING_MESSAGE);
			return 1;
		}
		
		return 2;
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------
	/**
	 * @param Nothing
	 * @returns int based on number of depots
	 */
	
	public int freeDepotCount() {
		
		int depotCount = 0;
		
		for(int count = 0; count < DepotArr.length; count++) {

			if ((DepotArr[count].readName().equals(""))) {
				depotCount++;
			}
		}
		
		System.out.print(depotCount);//TODO
		return depotCount;
	}
//------------------------------------------------------------------
//------------------------------------------------------------------
	/**
	 * @param Nothing
	 * @returns reference number of available depot, returns -1 if all depots are full
	 */
	
	public int avalableDepot() {
		
		for(int count = 0; count < DepotArr.length; count++) {
			if(DepotArr[count].readName().equals("")) {
				return count;
			}
		}
		
		return -1;
	}
//------------------------------------------------------------------
//------------------------------------------------------------------
	/**
	 * @param, depot String name
	 * @returns true, if it exists, false if not
	 */
	public boolean doesDepotExist(String Name) {
		for(int count = 0; count < 4; count++) {
			if(DepotArr[count].readName().equals(Name)) {
				return true;
			}
		}
		return false;
	}
}
