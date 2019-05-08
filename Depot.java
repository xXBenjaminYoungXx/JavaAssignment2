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
	
//------------------------------------------------------------------
//------------------------------------------------------------------
	//Name getters/Setters
	
	/**
	 * @param Nothing
	 * @return String Name
	 * 
	 */
	public void writeName(String name) {
		Name = name;
	}
	
	/**
	 * @param String Name
	 * @returns Nothing
	 * 
	 */
	public String readName() {
		return Name;
	}
	
//------------------------------------------------------------------
//***************PRODUCT GETTERS AND SETTERS************************
//------------------------------------------------------------------
	//Product Name getters and setters

	/**
	 * @param string product name, int reference, to identify which product
	 * @return Nothing
	 * 
	 */
	public void writeNameP(String name, int reference) {
		ProductArr[reference].writeName(name);
		return;
	}
	
	/**
	 * @param refrence to product
	 * @returns Name of referenced product
	 * 
	 */
	public String readNameP(int reference) {
		return ProductArr[reference].readName();
	}
//------------------------------------------------------------------
//------------------------------------------------------------------
	//Product Quantity getters and setters
	
	/**
	 * @param int product quant, int reference, to identify which product
	 * @return Nothing
	 * 
	 */
	public void writeQuantP(int quant, int reference) {
		ProductArr[reference].writeQuant(quant);
		return;
	}
	
	/**
	 * @param refrence to product
	 * @returns Quant of referenced product
	 * 
	 */
	public int readQuantP(int reference) {
		return ProductArr[reference].readQuant();
	}
//------------------------------------------------------------------
//------------------------------------------------------------------
	//Product Price getters and setters
	
	/**
	 * @param double product price, int reference, to identify which product
	 * @return Nothing
	 * 
	 */
	public void writePriceP(int price, int reference) {
		ProductArr[reference].writePrice(price);
		return;
	}
	
	/**
	 * @param refrence to product
	 * @returns price of referenced product
	 * 
	 */
	public double readPriceP(int reference) {
		return ProductArr[reference].readPrice();
	}
//------------------------------------------------------------------
//------------------------------------------------------------------
	//Product Weight getters and setters
	
	/**
	 * @param double product weight, int reference, to identify which product
	 * @return Nothing
	 * 
	 */
	public void writeWeightP(int weight, int reference) {
		ProductArr[reference].writeWeight(weight);
		return;
	}
	
	/**
	 * @param refrence to product
	 * @returns Weight of referenced product
	 * 
	 */
	public double readWeightP(int reference) {
		return ProductArr[reference].readWeight();
	}
}
