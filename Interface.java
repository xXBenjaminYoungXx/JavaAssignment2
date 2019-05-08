package Assignment2;
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
	}
	//------------------------------------//
	
	public void run() {
		
		//Initialize depot array
		DepotArr = new Depot[4];
		
		//Initialize depot objects
		for(int count = 0; count < DepotArr.length; count++) {
			DepotArr[count] = new Depot();
		}
		
		
	}
}
