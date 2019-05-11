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
	private Depot[] DepotArr;
	
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
			
			//See if exists
			if(doesDepotExist(Name)) {
				JOptionPane.showMessageDialog(null, "That depot already exists!", "Notice", JOptionPane.INFORMATION_MESSAGE);
				continue;
			}
			
			DepotArr[avalableDepot()].writeName(Name);
			
			exit = false;
		}
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void removeDepot() {
		//Declare variables
		String Name;
		boolean exit = true;
		int val;
		
		//See if depot exists
		if(freeDepotCount() == 4) {
			JOptionPane.showMessageDialog(null, "No depots exist!", "Notice", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//while loop
		while (exit) {
			
			//User input
			Name = JOptionPane.showInputDialog(null, "Enter name of depot to remove.\nExisting depots:\n- "+DepotArr[0].readName()+"\n- "+DepotArr[1].readName()+"\n- "+DepotArr[2].readName()+"\n- "+DepotArr[3].readName());
			
			val = isValid(Name);
			
			//null cheack
			if(val == 0) {
				return;
			}
			
			//verify
			if (val == 1) {
				continue;
			}
			
			Name = Name.toLowerCase();
			
			//look for depot
			val = findDepot(Name);
			
			if(val == -1) {
				continue;
			}
			//delete depot
			DepotArr[val].close();
			
			return;
			
		}
		
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void addProduct() {
		
		//Declare variables
		String Name;
		String Name1;
		boolean exit1 = true;
		boolean exit2 = true;
		int val;
		int ref = -3;
		int count;
		
		//See if depots exist
		if(freeDepotCount() == 4) {
			JOptionPane.showMessageDialog(null, "No depots exist!", "Notice", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		while(exit1) {
			
			//User Input product Name
			Name = JOptionPane.showInputDialog(null, "Enter name of product you wish to add:");
			
			val = isValid(Name);
			
			//null check
			if(val == 0) {
				return;
			}
			
			//verify
			if (val == 1) {
				continue;
			}
			
			Name = Name.toLowerCase();

			//Look for existing product
			for(count = 0; count < 4; count++) {
				ref = DepotArr[count].findProduct(Name);
				if(ref != -1) {
					JOptionPane.showMessageDialog(null, "Product "+Name+" Exists in "+DepotArr[count].readName()+".");
					break;
				}
				else {
					ref = -2;
				}
			}
			
			while(exit2) {
				//User input Depot name
				//User Input product Name
				Name1 = JOptionPane.showInputDialog(null, "Enter name of Depot you wish to add the product to:\nExisting depots:\n- "+DepotArr[0].readName()+"\n- "+DepotArr[1].readName()+"\n- "+DepotArr[2].readName()+"\n- "+DepotArr[3].readName());
				
				val = isValid(Name1);
				
				//null check
				if(val == 0) {
					return;
				}
				
				//verify
				if (val == 1) {
					continue;
				}
				
				Name1 = Name1.toLowerCase();

				//look for depot
				val = findDepot(Name1);
				
				if(val == -1) {
					continue;
				}
				
				if(ref == -2) {// New product
					//Check if 5 products exist in depot
					if(DepotArr[val].productCount()==5) {
						JOptionPane.showMessageDialog(null, "Depot "+DepotArr[val].readName()+" Is full. You can:\nRemove a product from this depot\nAdd to an existing product in this depot.");
						continue;
					}
					
					InputNewProduct(Name, val);
				}
				
				else {//existing product in ref depot count
					
					InputExistingProduct(Name, val);
				}
			}
		}	
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void removeProduct() {
		
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void listDepot() {
		
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void listProduct() {
		
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void lookForProduct() {
		
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void valueOfDepot() {
		
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void writeToFile() {
		
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void readfromFile() {
		
	}
	
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
	
//------------------------------------------------------------------
//------------------------------------------------------------------
	/**
	 * @param, depot String name
	 * @returns returns reference of existing depot
	 */
	public int findDepot(String Name) {
		
		for(int count = 0; count < 4; count++) {
			if(DepotArr[count].readName().equals(Name)) {
				return count;
			}
		}
		
		JOptionPane.showMessageDialog(null, "Depot "+Name+" does not exist");
		return -1;
	}
//------------------------------------------------------------------
//------------------------------------------------------------------
	/**
	 * @param, String Name, product, depot ref, product ref
	 * @returns Nothing
	 */
	public void InputNewProduct(String Name, int depotRef) {
		String Input;
		int quant;
		int val;
		double weight;
		double price;
		boolean exit = true;
		
		while(exit){
		
			Input = JOptionPane.showInputDialog(null, "Enter quantity of product:");
			
			val = isValid(Input);
			
			//null check
			if(val == 0) {
				return;
			}
			
			//verify
			if (val == 1) {
				continue;
			}
			
			Input = Input.replace(" ", "");
			
			quant = Integer.parseInt(Input);
			
			if(quant<=0) {
				JOptionPane.showMessageDialog(null, "No zeros or negatives permitted");
				continue;
			}
			
			Input = JOptionPane.showInputDialog(null, "Enter Weight of product:");
			
			val = isValid(Input);
			
			//null check
			if(val == 0) {
				return;
			}
			
			//verify
			if (val == 1) {
				continue;
			}
			
			Input = Input.replace(" ", "");
			
			weight = Double.parseDouble(Input);
			
			if(weight <= 0) {
				JOptionPane.showMessageDialog(null, "No zeros or negatives permitted");
				continue;
			}
			
			Input = JOptionPane.showInputDialog(null, "Enter Weight of product:");
			
			val = isValid(Input);
			
			//null check
			if(val == 0) {
				return;
			}
			
			//verify
			if (val == 1) {
				continue;
			}
			
			Input = Input.replace(" ", "");
			
			price = Double.parseDouble(Input);
			
			if(price <= 0) {
				JOptionPane.showMessageDialog(null, "No zeros or negatives permitted");
				continue;
			}
			
			//find free product 
			
			val = DepotArr[depotRef].findFreeProduct();
			
			if(val == -1) {
				continue;
			}
			
			DepotArr[depotRef].writeNameP(Name, val);
			DepotArr[depotRef].writeQuantP(quant, val);
			DepotArr[depotRef].writeWeightP(weight, val);
			DepotArr[depotRef].writePriceP(price, val);
			return;
		}
		
	}
//------------------------------------------------------------------
//------------------------------------------------------------------
	/**
	 * @param, String Name, product, depot ref, product ref
	 * @returns Nothing
	 */
	public void InputExistingProduct(String ProductName, int depotRef) {
		
		String Input;
		boolean exit = true;
		int val;
		int quant;
		
		while(exit) {
			
			Input = JOptionPane.showInputDialog(null, "Enter quantity of product:");
			
			val = isValid(Input);
			
			//null check
			if(val == 0) {
				return;
			}
			
			//verify
			if (val == 1) {
				continue;
			}
			
			Input = Input.replace(" ", "");
			
			quant = Integer.parseInt(Input);
			
			//To find what product
			val = DepotArr[depotRef].findProduct(ProductName);
			
			DepotArr[depotRef].writeQuantP((DepotArr[depotRef].readQuantP(val)+quant), val );
			
			return;
		}
	}

}
