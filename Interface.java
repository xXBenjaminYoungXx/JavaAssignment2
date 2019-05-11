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
			Name = JOptionPane.showInputDialog(null, "Enter name of depot to remove.\nExisting depots:\n"+printDepotList(0)+printDepotList(1)+printDepotList(2)+printDepotList(3));
			
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
		int val;
		int ref = -2;
		int depotReference = -1;
		
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
			for(int count = 0; count < 4; count++) {
				
				if(DepotArr[count].findProduct(Name) != -1) {
					
					ref = DepotArr[count].findProduct(Name);//Keep product reference of last product
					
					//Notify if it exists
					JOptionPane.showMessageDialog(null, "Product "+Name+" Exists in "+DepotArr[count].readName()+" with value of $"+DepotArr[count].readPriceP(ref)+" and weight of "+DepotArr[count].readWeightP(ref)+"Kg.\nData has been coppied, only quantity is needed");
					
					//keep depot reference
					depotReference = count;
				}
			}
			
			//User input Depot name
			//User Input product Name
			Name1 = JOptionPane.showInputDialog(null, "Enter name of Depot you wish to add the product to:\nExisting depots:\n"+printDepotList(0)+printDepotList(1)+printDepotList(2)+printDepotList(3));
			
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
				
				InputExistingProduct(ref, findDepot(DepotArr[depotReference].readName()), val);
			}
		}	
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void removeProduct() {
		//Declare variables
		String Name1;
		String Name2;
		String Quant;
		boolean exit = true;
		int refP;
		int refD;
		int val;
		int quant;
		
		while(exit) {
			
			//Look look for existing depot
			if(freeDepotCount() == 4) {
				JOptionPane.showMessageDialog(null, "No depots exist!", "Notice", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			//Request user input, depot
			Name1 = JOptionPane.showInputDialog(null, "Enter name of Depot:\nExisting depots:\n"+printDepotList(0)+printDepotList(1)+printDepotList(2)+printDepotList(3));
			
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
	
			refD = findDepot(Name1);
			
			if(refD == -1) {
				continue;
			}
			
			if(DepotArr[refD].productCount() == 0) {
				JOptionPane.showMessageDialog(null, "No products exist in "+DepotArr[refD].readName());
				continue;
			}
			
			//request user input product
			Name2 = JOptionPane.showInputDialog(null, "Enter name of product you wish to delete:\nExisting products:\n"+printProductList(refD, 0)+printProductList(refD, 1)+printProductList(refD, 2)+printProductList(refD, 3)+printProductList(refD, 4));
			
			val = isValid(Name2);
			
			//null check
			if(val == 0) {
				return;
			}
			
			//verify
			if (val == 1) {
				continue;
			}
			
			Name2 = Name2.toLowerCase();
			
			refP = DepotArr[refD].findProduct(Name2);
			//Look for product existence
			if(refP == -1) {
				JOptionPane.showMessageDialog(null, "Product does not exist", "Error", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
			//Request value to delete product
			Quant = JOptionPane.showInputDialog(null, "Enter quantity of product you wish to remove:\nCurrent quantity: "+DepotArr[refD].readQuantP(refP));
			
			val = isValid(Quant);
			
			//null check
			if(val == 0) {
				return;
			}
			
			//verify
			if (val == 1) {
				continue;
			}
			
			Quant = Quant.replace(" ", "");
			
			quant = Integer.parseInt(Quant);
			
			if(quant > DepotArr[refD].readQuantP(refP)) {
				JOptionPane.showMessageDialog(null, "You ented a value greater than the number of the product present!\nPlease enter valid value!");
				continue;
			}
			
			if(quant <= 0) {
				JOptionPane.showMessageDialog(null, "You ented a value equal to or less than 0!\nPlease enter valid value!");
				continue;
			}
			
			val = DepotArr[refD].writeQuantP(DepotArr[refD].readQuantP(refP) - quant, refP);
			
			if(val == 2) {
				JOptionPane.showMessageDialog(null, "Product has been removed from "+ DepotArr[refD].readName());
			}
			
			return;
		}
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void listDepot() {
		int depot1P = DepotArr[0].productCount();
		int depot2P = DepotArr[1].productCount();
		int depot3P = DepotArr[2].productCount();
		int depot4P = DepotArr[3].productCount();
		
		if(freeDepotCount() == 4) {
			JOptionPane.showMessageDialog(null, "No depots exist.", "list Depot", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(null, "Existing Depots:\n"+depotListText(0, depot1P)+depotListText(1, depot2P)+depotListText(2, depot3P)+depotListText(3, depot4P), "Depot List", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String depotListText(int refD, int Pnum) {
		if(DepotArr[refD].readName().equals("")) {
			return "";
		}
		
		return "- "+ DepotArr[refD].readName() + " has "+Pnum+" products.\n";
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void listProduct() {
		
		String Name;
		int val;
		boolean exit = true;
		
		while(exit) {
			//look if depots exist
			if(freeDepotCount() == 4) {
				JOptionPane.showMessageDialog(null, "No depots exist.", "list Depot", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			//User input
			Name = JOptionPane.showInputDialog(null, "Enter name of depot you want to look at.\nExisting depots:\n"+printDepotList(0)+printDepotList(1)+printDepotList(2)+printDepotList(3));
			
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
			
			JOptionPane.showMessageDialog(null, "Depot " + DepotArr[val].readName()+" contains the following products:\n"+printProductInfo(val, 0)+printProductInfo(val, 1)+printProductInfo(val, 2)+printProductInfo(val, 3)+printProductInfo(val, 4), "Product List", JOptionPane.INFORMATION_MESSAGE);
			
			return;
		}
	}
	
	public String printProductInfo(int refD, int refP) {
		if(DepotArr[refD].readNameP(refP).equals("")) {
			return "";
		}
		
		return "- Product "+ DepotArr[refD].readNameP(refP) +" has price $"+DepotArr[refD].readPriceP(refP)+ " weight "+DepotArr[refD].readWeightP(refP)+"kg, and quantity "+DepotArr[refD].readQuantP(refP)+"\n";
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void lookForProduct() {
		//Variables
		boolean[][] list = new boolean[4][5];
		
		for(int count1 = 0; count1 < 4; count1++) {
			for(int count2 = 0; count2 < 5; count2++) {
				list[count1][count2] = false;
			}
		}
		String Name;
		boolean exit = true;
		int val;
		int refD;
		int refP;
		
		if(freeDepotCount() == 4) {
			JOptionPane.showMessageDialog(null, "No depots exist.", "list Depot", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		if(DepotArr[0].productCount() == 5 && DepotArr[1].productCount() == 5 && DepotArr[2].productCount() == 5 && DepotArr[3].productCount() == 5) {
			JOptionPane.showMessageDialog(null, "No products exist.", "List Depot", JOptionPane.INFORMATION_MESSAGE);
		}
		while(exit) {
			
			Name = JOptionPane.showInputDialog(null, "Enter name of product: ");
			
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
			
			for(refD = 0; refD < 4; refD++) {
				for(refP = 0; refP < 5; refP++) {
					if(DepotArr[refD].readNameP(refP).equals(Name)) {
						list[refD][refP] = true;
					}
				}
			}
			
			if(productSearchRes(list).equals("")) {
				continue;
			}
			JOptionPane.showMessageDialog(null, productSearchRes(list), "Product Search results", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}
	
	public String productSearchRes(boolean [][] list) {
		String Final = "";
		
		for(int refD = 0; refD < 4; refD++) {
			for(int refP = 0; refP < 5; refP++) {
				if(list[refD][refP] == true) {
					Final = Final.concat("Product "+DepotArr[refD].readNameP(refP)+" exists in depot "+DepotArr[refD].readName()+" with quantity "+DepotArr[refD].readQuantP(refP)+"\n");
				}
			}
		}
		
		if(Final.equals("")) {
			JOptionPane.showMessageDialog(null, "Product does not exist in any depot");
			return Final;
		}
		return Final;
	}
//------------------------------------------------------------------
//------------------------------------------------------------------

	public void valueOfDepot() {
		String Name;
		boolean exit = true;
		int val;
		int refD;
		
		if(freeDepotCount() == 4) {
			JOptionPane.showMessageDialog(null, "No depots exist.", "list Depot", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		while(exit) {
			
			Name = JOptionPane.showInputDialog(null, "Enter name of depot: \nExisting depots:\n"+printDepotList(0)+printDepotList(1)+printDepotList(2)+printDepotList(3));
			
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
			
			refD = findDepot(Name);
			
			if(refD == -1) {
				continue;
			}
			
			JOptionPane.showMessageDialog(null, "Depot "+DepotArr[refD].readName()+" has a culmative product value of: $"+sumOfDepotVal(refD), "Depot Value", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}
	
	public double sumOfDepotVal(int refD) {
		return DepotArr[refD].readPriceP(0) * DepotArr[refD].readQuantP(0) + DepotArr[refD].readPriceP(1) * DepotArr[refD].readQuantP(1) + DepotArr[refD].readPriceP(2) * DepotArr[refD].readQuantP(2) + DepotArr[refD].readPriceP(3) * DepotArr[refD].readQuantP(3) + DepotArr[refD].readPriceP(4) * DepotArr[refD].readQuantP(4);
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
			
			Input = JOptionPane.showInputDialog(null, "Enter price of product:");
			
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
	public void InputExistingProduct(int ProductRef/*product ref*/, int DepotRef/*from depot it is from*/, int depotRef/*To add to*/) {
		
		String Input;
		String Name;
		boolean exit = true;
		int val;
		int quant;
		int count;
		double price;
		double weight;
		
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
			
			//get data from first depot
			price = DepotArr[DepotRef].readPriceP(ProductRef);
			
			weight = DepotArr[DepotRef].readWeightP(ProductRef);
			
			Name = DepotArr[DepotRef].readNameP(ProductRef);
			
			//Find which product to add to
			
			//see if it already exists
			for(count = 0; count < 5; count++) {
				
				if(DepotArr[depotRef].readNameP(count).equals(DepotArr[DepotRef].readNameP(ProductRef))) { // It already exists in depot
					
					DepotArr[depotRef].writeQuantP((DepotArr[depotRef].readQuantP(count)+quant), count);
					
					DepotArr[depotRef].writePriceP(price, count);
					
					DepotArr[depotRef].writeWeightP(weight, count);
					
					return;
				}
			}
			
			//otherwise we need to find new product spot
			count = DepotArr[depotRef].findFreeProduct();
			
			DepotArr[depotRef].writeNameP(Name, count);
			DepotArr[depotRef].writeQuantP(quant, count);
			DepotArr[depotRef].writePriceP(price, count);
			DepotArr[depotRef].writeWeightP(weight, count);
			
			return;
		}
	}

//------------------------------------------------------------------
//------------------------------------------------------------------
	public String printDepotList(int refD) {
		if(DepotArr[refD].readName().equals("")) {
			return "";
		}
		
		return "- "+ DepotArr[refD].readName() +"\n";
	}
	
	public String printProductList(int refD, int refP) {
		if(DepotArr[refD].readNameP(refP).equals("")) {
			return "";
		}
		
		return "- "+ DepotArr[refD].readNameP(refP) +"\n";
	}
}
