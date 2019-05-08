package Assignment2;
//-----------------------------------------------------------------------------------------
/*
* Authors: Benjamin Young and Ryan McCormack
* Student ID's: c3330653 c3329771
* Email: BenjaminYoung7198@gmail.com  ryanmack@bigpond.com
* Requirements: 
*	Product.java
*/
//-----------------------------------------------------------------------------------------

public class Depot {
	
	//Variable/Object Types of class Depot
	private static Product[] ProductArr;
	private static String Name;
	
	public Depot() {
		
		ProductArr = new Product[5];
		
		Name = "";
		
		for(int count = 0; count < ProductArr.length; count++) {
			ProductArr[count] = new Product();
		}
	}
}
