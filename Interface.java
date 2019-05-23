package Assignment2;

import javax.swing.JOptionPane;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

//-----------------------------------------------------------------------------------------
/*
* Authors: Benjamin Young and Ryan McCormack
* Student ID's: c3330653 c3329771
* Email: BenjaminYoung7198@gmail.com  ryanmack@bigpond.com
* Requirements: 
* 	javax.swing.JOptionPane
*	java.io.PrintWriter
*	java.io.FileNotFoundException
*	java.io.File
*	java.util.Scanner
*	Depot.java
* ______________________________
* |****************************|
* |* Method Information Below!*|
* |____________________________|
* 
* Information on @param and @return can be found at the respective method decoration.
* 
* FLOW CONTROLL/SUB-METHODS(Methods used specifically for other methods):
* 		-Main: Creates Interface object 'GUI' and runs the Interface.
* 		-Run(): Begins main flow control of program, calls menu method and responds to
* 				its return accordingly.
* 		-mainMenu(): Displays menu choices to user, and requests input of numbers (1-10) to execute respective
* 				 menu choice
* 
* 		-addDepot(): Governs flow control for the addDepot command. Checks for available depot space
* 					 then requests Name from user input and adds depot if it doen't already exist.
* 
* 		-removeDepot(): Governs flow control for the removeDepot command. Checks to see if depots already exist, the 
* 						requests user input for depot name to delete. Prints existing depots onto GUI.
* 
* 		-addProduct(): Governs flow control for the addProduct command. Checks to see if other depots exist.
* 					   requests user input for product name, scans to see if product exists, the requests name of depot to add product to.
* 					   If depot is full, it will notify user with error.
* 			-InputNewProduct(): Will request user input for weight, price, quantity values, and assign to new product object in depot.
* 			-InputExistingProduct(): Will request user input for quantity value, and assign to depot, using values 
* 									 from existing product
* 
* 		-removeProduct(): Governs flow control for the removeProduct command. Checks to see if other depots exist.
* 					      Requests user input for depot name, existing depots are printed to GUI. Then lists products in depot, 
* 						  requesting user input for product name. Then prints number of that product in that depot and requests
* 						  value at which to take away from.
* 
* 		-listDepot(): prints to GUI, existing depots. Checks to see if other depots exist.
* 			-depotListText(): Formats required depots to print as a simple string.
* 
* 		-listProduct(): Governs flow control for the listProduct command. Checks to see if other depots exist.
* 						requests user input for depot name, then lists products within that depot.
* 			-printProductInfo(): Generates string of product info
* 
* 		-lookForProduct(): Requests user input for product name. Then scans all products in all depots, and stores
* 						   results on 2D boolean array. True if name matches. Prints up true results.
* 			-productSearchRes(): Generates string of successful product search results. Notify's user if no successful
* 								 results were found.
* 
* 		-valueOfDepot(): Requests user input for depot name. Calculates value of depot.
* 			-sumOfDepotVal(): Calculates sum of a depots value
* 
* 		-writeToFile(): Requests user input for file name, notifies user that this will overwrite data in that file it 
* 						it already exists. Notifies user if FileNotFoundException error is thrown.
* 			-finalFilePrint(): generates connected Strings generated from finalProductPrint():
* 			-finalProductPrint(): generates strings containing information of required product. Formatted for file data.
* 
* 		-readFromFile(): Requests user input for file name, notifies user if file not found, and will display an error if format is not correct 
* 						 on a given line. Will skip corrupted line and continue reading.
* 			-proccessLine(): Takes a line of characters in file, and begins processing data. Begins by removing useless material (-Depot),
* 							 and segregates information on line into an array. It then determines information and decides the following:
* 							 -line contains information on empty depot
* 							 -line contains information of depot and new product
* 							 -line contains information of depot and existing product
* 							 -line has incorrect format and is regarded as corrupted data, must be skipped
* 				-fileExistingProduct(): Takes data from product that already exists and assigns to product in requested depot
* 										Accounts for the fact that the product may exist in current depot.
* 				-fileNewProduct(); Takes data to begin the creation of a new product in requested depot.
* 		
* OTHER METHODS:
* 		-isValid(): Takes string and checks to see if a usable input is provided.
* 
* 		-freeDepotCount(): returns int based on number of available depots
* 
* 		-avaliableDepot(): returns int of a free depot reference value, returns -1 if none exist
* 
* 		-doesDepotExist(): returns true if depot exists, false if otherwise
* 
* 		-findDepot(): returns int value of depot reference
* 
* 		-printDepotList(): returns string of depot information if they exist
*TODO: Debug program
*TODO: Add main method comments
*TODO: Add functionality and fix grammar
*/
//-----------------------------------------------------------------------------------------

/*
 * Class: Interface
 * Interface Object parameters:
 * 	-Depot array "DepotArr"
 * 	-MAX_D final int.
 * 	-MAX_P final int.
 */
public class Interface {
	
	//Variable/Object Types of class Interface
	private Depot[] DepotArr;
	private static final int MAX_D = 4;//Depot array max limit
	private static final int MAX_P = 5;//Product array max limit
	/*
	* Changing final variables will work for additional depots/products however, these additional products/depots wont be printed to GUI.
	* I can accommodate for this by modifying String methods such as 'productSearchRes'.
	* NOTE: Default should remain at MAX_D = 4
	* 								 MAX_P = 5 //as given in project description
	*/
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
		DepotArr = new Depot[MAX_D];
		
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
				readFromFile();
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
			JOptionPane.showMessageDialog(null, MAX_D+" depots already exist!", "Notice", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		while(exit) {
			Name = JOptionPane.showInputDialog(null, "Enter name of Depot you wish to add:\nNote: \n-All characters will become lowercase.\n-Spaces will be replaced with '_'.");
			
			val = isValid(Name);//is it valid
			
			if(val == 0) {//Did they click cancel
				exit = false;
				continue;
			}
			else if(val == 1) {
				continue;
			}
			
			Name = Name.toLowerCase();
			
			Name = Name.replace(" ", "_");
			
			if(Name.contains(" "))
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
	/**
	 * @param Nothing
	 * @return Nothing
	 * 
	 */
	public void removeDepot() {
		//Declare variables
		String Name;
		boolean exit = true;
		int val;
		
		//See if depot exists
		if(freeDepotCount() == MAX_D) {
			JOptionPane.showMessageDialog(null, "No depots exist!", "Notice", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//while loop
		while (exit) {
			
			//User input
			Name = JOptionPane.showInputDialog(null, "Enter name of depot to remove.\nExisting depots:\n"+printDepotList(0)+printDepotList(1)+printDepotList(2)+printDepotList(3));
			
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
			
			Name = Name.replace(" ", "_");
			
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
	/**
	 * Flow control for addProduct menu option
	 */
	public void addProduct() {
		
		//Declare variables
		String Name;
		String Name1;
		boolean exit1 = true;
		int val;
		int refP = -2;
		int depotReference = -1;
		
		//See if depots exist
		if(freeDepotCount() == MAX_D) {
			JOptionPane.showMessageDialog(null, "No depots exist!", "Notice", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		while(exit1) {
			
			refP = -2;
			
			//User Input product Name
			Name = JOptionPane.showInputDialog(null, "Enter name of product you wish to add:\nNote: \n-All characters will become lowercase.\n-Spaces will be replaced with '_'.");
			
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
			
			Name = Name.replace(" ", "_");

			//Look for existing product
			for(int refD = 0; refD < MAX_D; refD++) {
				
				if(DepotArr[refD].findProduct(Name) != -1) {
					
					refP = DepotArr[refD].findProduct(Name);//Keep product reference of last product
					
					//Notify if it exists
					JOptionPane.showMessageDialog(null, "Product "+Name+" Exists in "+DepotArr[refD].readName()+" with value of $"+DepotArr[refD].readPriceP(refP)+" and weight of "+DepotArr[refD].readWeightP(refP)+"Kg.\nData has been coppied, only quantity is needed");
					
					//keep depot reference
					depotReference = refD;
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
			
			Name1 = Name1.replace(" ", "_");

			//look for depot
			val = findDepot(Name1);
			
			if(val == -1) {
				continue;
			}
			
			if(refP == -2) {// New product
				//Check if 5 products exist in depot
				if(DepotArr[val].productCount()==5) {
					JOptionPane.showMessageDialog(null, "Depot "+DepotArr[val].readName()+" Is full. You can:\nRemove a product from this depot\nAdd to an existing product in this depot.");
					continue;
				}
				
				InputNewProduct(Name, val);
			}
			
			else {//existing product in ref depot count
				
				InputExistingProduct(refP, findDepot(DepotArr[depotReference].readName()), val);
			}
		}	
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------
	/**
	 * Flow control for removeProduct menu option
	 */
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
			if(freeDepotCount() == MAX_D) {
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
			
			Name1 = Name1.replace(" ", "_");
	
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
			
			Name2 = Name2.replace(" ", "_");

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
			
			//Because string changed to a different data type is from user input, a NumberFormatException can occur
			try {
				quant = Integer.parseInt(Quant);
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error:\n"+e.toString()+"\nPlease enter valid input!","ERROR",JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
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
	/**
	 * Flow control for listDepot menu option
	 */
	public void listDepot() {
		int depot1P = DepotArr[0].productCount();
		int depot2P = DepotArr[1].productCount();
		int depot3P = DepotArr[2].productCount();
		int depot4P = DepotArr[3].productCount();
		
		if(freeDepotCount() == MAX_D) {
			JOptionPane.showMessageDialog(null, "No depots exist.", "list Depot", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(null, "Existing Depots:\n"+depotListText(0, depot1P)+depotListText(1, depot2P)+depotListText(2, depot3P)+depotListText(3, depot4P), "Depot List", JOptionPane.INFORMATION_MESSAGE);
	}
	//this small method is used to get String data for listDepot
	public String depotListText(int refD, int Pnum) {
		if(DepotArr[refD].readName().equals("")) {
			return "";
		}
		
		return "- "+ DepotArr[refD].readName() + " has "+Pnum+" products.\n";
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------
	/**
	 * Flow control for listProduct menu Option
	 */
	public void listProduct() {
		
		String Name;
		int val;
		boolean exit = true;
		
		while(exit) {
			//look if depots exist
			if(freeDepotCount() == MAX_D) {
				JOptionPane.showMessageDialog(null, "No depots exist.", "list Depot", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			//User input
			Name = JOptionPane.showInputDialog(null, "Enter name of depot you want to look at.\nExisting depots:\n"+printDepotList(0)+printDepotList(1)+printDepotList(2)+printDepotList(3));
			
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
			
			Name = Name.replace(" ", "_");
			
			//look for depot
			val = findDepot(Name);
			
			if(val == -1) {
				continue;
			}
			
			JOptionPane.showMessageDialog(null, "Depot " + DepotArr[val].readName()+" contains the following products:\n"+printProductInfo(val, 0)+printProductInfo(val, 1)+printProductInfo(val, 2)+printProductInfo(val, 3)+printProductInfo(val, 4), "Product List", JOptionPane.INFORMATION_MESSAGE);
			
			return;
		}
	}
	/**
	 * 
	 * @param refD, int Depot Array reference 
	 * @param refP, int Product array reference
	 * @return String, that contains info on depot refD, product refP
	 */
	public String printProductInfo(int refD, int refP) {
		if(DepotArr[refD].readNameP(refP).equals("")) {
			return "";
		}
		
		return "- Product "+ DepotArr[refD].readNameP(refP) +" has price $"+DepotArr[refD].readPriceP(refP)+ " weight "+DepotArr[refD].readWeightP(refP)+"kg, and quantity "+DepotArr[refD].readQuantP(refP)+"\n";
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------
/**
 * Flow Control for lookForProduct menu option
 */
	public void lookForProduct() {
		//Variables
		boolean[][] list = new boolean[MAX_D][MAX_P];
		
		for(int count1 = 0; count1 < MAX_D; count1++) {
			for(int count2 = 0; count2 < MAX_P; count2++) {
				list[count1][count2] = false;
			}
		}
		
		String Name;
		boolean exit = true;
		int val;
		int refD;
		int refP;
		
		//See if a depot exists
		if(freeDepotCount() == MAX_D) {
			JOptionPane.showMessageDialog(null, "No depots exist.", "list Depot", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//See if product exists
		if(DepotArr[0].productCount() == 0 && DepotArr[1].productCount() == 0 && DepotArr[2].productCount() == 0 && DepotArr[3].productCount() == 0) {
			JOptionPane.showMessageDialog(null, "No products exist.", "List Depot", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		while(exit) {
			
			Name = JOptionPane.showInputDialog(null, "Enter name of product: ");
			
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
			
			Name = Name.replace(" ", "_");

			//List all matched search results in boolean array
			for(refD = 0; refD < MAX_D; refD++) {
				
				for(refP = 0; refP < MAX_P; refP++) {
					
					if(DepotArr[refD].readNameP(refP).equals(Name)) {
						
						list[refD][refP] = true;
					}
				}
			}
			
			//Convert boolean array into respective string
			if(productSearchRes(list).equals("")) {
				continue;
			}
			
			JOptionPane.showMessageDialog(null, productSearchRes(list), "Product Search Results", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}
	
//------------------------------------------------------------------	
	/**
	 * 
	 * @param list, boolean array containing information of which result came up true
	 * @return Information on products that returned true on boolean array
	 */
	public String productSearchRes(boolean [][] list) {
		String Final = "";
		
		for(int refD = 0; refD < MAX_D; refD++) {
			for(int refP = 0; refP < MAX_P; refP++) {
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
	/**
	 * Flow Control of valueOfDepot menu choice
	 */
	public void valueOfDepot() {
		String Name;
		boolean exit = true;
		int val;
		int refD;
		
		if(freeDepotCount() == MAX_D) {
			JOptionPane.showMessageDialog(null, "No depots exist.", "list Depot", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		while(exit) {
			
			Name = JOptionPane.showInputDialog(null, "Enter name of depot: \nExisting depots:\n"+printDepotList(0)+printDepotList(1)+printDepotList(2)+printDepotList(3));
			
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
			
			Name = Name.replace(" ", "_");

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
	/**
	 * Flow control of write to file menu option
	 */
	public void writeToFile() {
		//variables/Objects
		String FileName = "";
		PrintWriter OutputStream;
		int val = 0;
		
		FileName = JOptionPane.showInputDialog(null, "Enter name of file you wish to create & save data to.\nPlease include '.txt' extension.\nNOTICE: If inputted file name already exists, file data will be wiped!!\nYou have been warned!", "File Name", JOptionPane.WARNING_MESSAGE);
		
		val = isValid(FileName);
		
		//null check
		if(val == 0) {
			return;
		}
		
		//verify
		if (val == 1) {
			return;
		}
		
		//try/catch block for file initialization
		try {
			OutputStream = new PrintWriter(FileName);
		} 
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERROR:\nFILE "+FileName+" INITIALISATION ERROR!\nError with file, present within 'writeToFile' method","ERROR",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//Development of String object to print line by line
		//Note: Apparently \n does not work hence it needs to be solved line by line
		
		for(int refD = 0; refD < MAX_D; refD++) {
			
			//If depot does not exist, skip this process
			if(DepotArr[refD].readName().equals("")) {
				continue;
			}
			
			//If depot exists it needs to be printed to file
			for(int refP = 0; refP < MAX_P; refP++) {
				
				if(DepotArr[refD].productCount() == 0) {//If no products exist in it it needs to be printed once
					OutputStream.println(DepotArr[refD].readName()+"-Depot");
					refP = MAX_P;
					continue;
				}
				
				if(DepotArr[refD].readNameP(refP).equals("")) {//If product doen't exist here go back
					continue;
				}
				
				if(!DepotArr[refD].readName().equals("")) {
					OutputStream.println(FinalFilePrint(refD,refP));//print depot and respective product info
				}
			}
		}
		
		OutputStream.close();
		
		JOptionPane.showMessageDialog(null, "Data successfully saved to "+FileName, "Notice", JOptionPane.INFORMATION_MESSAGE);
	}
//------------------------------------------------------------------
	/**
	 * 
	 * @param refD, Reference variable to depot object
	 * @return String that needs to be printed to file
	 */
	public String FinalFilePrint(int refD, int refP) {
		String FinalString = "";
		
		
		FinalString = FinalString.concat(DepotArr[refD].readName()+"-Depot "+FileProductPrint(refD, refP));
		
		return FinalString;
	}
	
	/**
	 * 
	 * @param refD, reference to depot array
	 * @return String that contains respective depot product information
	 */
	public String FileProductPrint(int refD, int refP) {
		String FinalString = "";
			
		if(!DepotArr[refD].readNameP(refP).equals("")) {
			FinalString = FinalString.concat(DepotArr[refD].readNameP(refP)+" "+DepotArr[refD].readPriceP(refP)+" "+DepotArr[refD].readWeightP(refP)+" "+DepotArr[refD].readQuantP(refP));
		}
		
		return FinalString;
	}

	
//------------------------------------------------------------------
//------------------------------------------------------------------
	/**
	 * Flow control of readFromFile menu option
	 */
	public void readFromFile() {
		//Variables/Objects
		String FileName = "";
		String Line;
		Scanner InputStream;
		int iteration = 1;
		
		int val = 0;
		
		//Request file name
		FileName = JOptionPane.showInputDialog(null, "Enter name of file you wish to load.\nPlease include '.txt' extension.", "File Name", JOptionPane.INFORMATION_MESSAGE);
		
		val = isValid(FileName);
		
		//null check
		if(val == 0) {
			return;
		}
		
		//verify
		if (val == 1) {
			return;
		}
		
		//try/catch block for fileStream initialization
		try {
			InputStream = new Scanner(new File(FileName));
		} 
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "ERROR:\nFILE '"+FileName+"' INITIALIZATION ERROR!\nError present within 'readFromFile' method\nCause: Incorrect file name.","ERROR",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!InputStream.hasNext()) {
			JOptionPane.showMessageDialog(null, "ERROR:\nFILE "+FileName+" READING ERROR!\nError with file, present within 'readFromFile' method\nNo data Present","ERROR",JOptionPane.ERROR_MESSAGE);
			InputStream.close();
			return;
		}
		
		//File is processed line by line
		while(InputStream.hasNextLine()) {
			iteration++;
			//Get line to process
			Line = InputStream.nextLine();
			//put line in method processor
			processLine(Line, iteration);
		}
		
		InputStream.close();
	}
//------------------------------------------------------------------
	/**
	 * 
	 * @param Line, String taken from single line of file
	 * @param iteration, Used to track file data for error messages
	 */
	public void processLine(String Line, int iteration) {
		String DepotName = "";
		String ProductName = "";
		double price = -1;
		double weight = -1;
		int quant = 0;
		int atRefP = -1;
		int atRefD = -1;
		int depotAt = -1;
		
		//Eliminating useless info
		Line = Line.toLowerCase();
		Line = Line.replace("-depot", "");
		Line = Line.replace("  ", " ");//Replace any double spaces with single (Just in case)
		
		String [] words = Line.split(" ", 5);
		
		if(words.length < 2) {//If only depot name is present due to its lack of products
			DepotName = words[0];
		}
		
		else {//If depot and product both exist
			try{
				DepotName = words[0];
				ProductName = words[1];
				price = Double.parseDouble(words[2]);
				weight = Double.parseDouble(words[3]);
				quant = Integer.parseInt(words[4]);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}	
		}
		
		if(DepotName.replace(" ", "").equals("")) {
			JOptionPane.showMessageDialog(null, "invalid depot name, skipping line "+iteration,"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//Now data is stored, we need to find out 'what to do'
		//Firstly we need to see if 'DepotName' already exists
		for(int refD = 0; refD < MAX_D; refD++) {
			
			if(DepotArr[refD].readName().equals(DepotName)) {
				depotAt = refD;
			}
		}
		
		//Secondly we need to see if product exists in any depot only if product is listed
		if(words.length == 5) {
			for(int refD = 0; refD < MAX_D; refD++){
				
				for(int refP = 0; refP < MAX_P; refP++) {
					
					if(DepotArr[refD].readNameP(refP).equals(ProductName)) {
						atRefP = refP;
						atRefD = refD;
					}
				}
			}
		}
		
		//If depot does not exist we need to make one
		if(depotAt == -1) {
			
			//Find free depot spot
			depotAt = avalableDepot();
			
			if(depotAt == -1) {
				JOptionPane.showMessageDialog(null, "No Depot spots avaliable, skipping line "+iteration,"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			DepotArr[depotAt].writeName(DepotName);
			
			if(words.length < 2) {
				return;
			}
		}
		
		//Now if depot and product is listed,Note: new depot is already written.
		if(words.length == 5) {//ie product info listed
			//if product exists
			if(atRefP != -1) {
				FileExistingProduct(atRefD,atRefP,depotAt,quant);
				return;
			}
			//if product is new
			FileNewProduct(depotAt, weight, price, quant, ProductName);
		}
	}
//------------------------------------------------------------------
	/**
	 * 
	 * @param refD, The array address of Depot
	 * @param refP, The array address of Product
	 * @param refd, The array address of Depot we want to put product in
	 * @param quant, The quantity of the product
	 * @return NOTHING
	 * This method has been separated from LineProccess as to help structure the code.
	 */
	public void FileExistingProduct(int refD/*address of product*/, int refP/*address of product*/, int refd/*Depot we add to*/, int quant) {
		String Name = DepotArr[refD].readNameP(refP);//existing product name
		double price = DepotArr[refD].readPriceP(refP);
		double weight = DepotArr[refD].readWeightP(refP);
		int freeSpace = -1;
		
		//We need to see if product exists in current depot
		for(int count = 0; count < MAX_P; count++) {
			if(DepotArr[refd].readNameP(count).equals(Name)) {
				//We just need to add quant
				DepotArr[refd].writeQuantP(DepotArr[refd].readQuantP(count) + quant, count);
				return;
			}
		}
		
		//Otherwise we need to find free product space
		freeSpace = DepotArr[refd].findFreeProduct();
		
		if(freeSpace == -1) {
			JOptionPane.showMessageDialog(null, "Depot "+DepotArr[refd].readName()+" Has no free product spots, line will now be skiped.", "Notice", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//Add data to product
		DepotArr[refd].writeNameP(Name, freeSpace);
		DepotArr[refd].writeQuantP(quant, freeSpace);
		DepotArr[refd].writePriceP(price, freeSpace);
		DepotArr[refd].writeWeightP(weight, freeSpace);
		return;
	}

//------------------------------------------------------------------
	/**
	 * 
	 * @param refd, int: Depot array reference of depot we want to add product to
	 * @param weight, double: weight value of product object
	 * @param price, double: price variable of product object
	 * @param quant, int: quant variable of product object
	 * @param Name, String: Name variable of product object
	 * @return NOTHING
	 * This method has been separated from LineProccess as to help structure the code.
	 */
	public void FileNewProduct(int refd/*Depot we add to*/, double weight, double price, int quant, String Name) {
		int freeSpot = -1;
		//Find free product spot
		freeSpot = DepotArr[refd].findFreeProduct();
		
		if(freeSpot == -1) {
			JOptionPane.showMessageDialog(null, "Depot "+DepotArr[refd].readName()+" Has no free product spots, line will now be skiped.", "Notice", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		DepotArr[refd].writeNameP(Name, freeSpot);
		DepotArr[refd].writeQuantP(quant, freeSpot);
		DepotArr[refd].writePriceP(price, freeSpot);
		DepotArr[refd].writeWeightP(weight, freeSpot);
		return;
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
		for(int count = 0; count < MAX_D; count++) {
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
		
		for(int count = 0; count < MAX_D; count++) {
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
	 * @param, String Name, product, depot ref, product ref.
	 * @returns Nothing.
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
			
			//Because string changed to a different data type is from user input, a NumberFormatException can occur
			try {
				quant = Integer.parseInt(Input);
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error:\n"+e.toString()+"\nPlease enter valid input!","ERROR",JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
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
			
			//Because string changed to a different data type is from user input, a NumberFormatException can occur
			try {
				weight = Double.parseDouble(Input);
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error:\n"+e.toString()+"\nPlease enter valid input!","ERROR",JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
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
			
			//Because string changed to a different data type is from user input, a NumberFormatException can occur
			try {	
				price = Double.parseDouble(Input);
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error:\n"+e.toString()+"\nPlease enter valid input!","ERROR",JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
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
	public void InputExistingProduct(int ProductRef/*product ref it is from*/, int DepotRef/*from depot it is from*/, int depotRef/*To add to*/) {
		
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
			
			//Because string changed to a different data type is from user input, a NumberFormatException can occur
			try {
				quant = Integer.parseInt(Input);
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error:\n"+e.toString()+"\nPlease enter valid input!","ERROR",JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
			//get data from first depot
			price = DepotArr[DepotRef].readPriceP(ProductRef);
			
			weight = DepotArr[DepotRef].readWeightP(ProductRef);
			
			Name = DepotArr[DepotRef].readNameP(ProductRef);
			
			//Find which product to add to
			
			//see if it already exists
			for(count = 0; count < MAX_P; count++) {
				
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
	/**
	 * 
	 * @param refD, int reference value based on selected depot
	 * @return String that either prints Product information if it exists, or returns "" if it doesn't
	 */
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
/*				 *
 *	     	    **
 *        /\   ***
 *		 /  \ |  |
 *      /    \|  |
 * 	   /	  \  |
 *    /		   \ |
 *   /			\|
 *  /			 \
 * /______________\
 * |  _        _  |
 * | |_|	  |	| |
 * |__________|_|_|
 * You found the house!
 * It only seems to be a house in eclipse...
*/