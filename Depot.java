package Assignment2;
//-----------------------------------------------------------------------------------------
/*
* Authors: Benjamin Young and Ryan McCormack
* Student ID's: c3330653 c3329771
* Email: BenjaminYoung7198@gmail.com  ryanmack@bigpond.com
* Requirements: 
*	Product.java
*
* METHOD LIST:
* 	Constructors/Close():
* 		-Depot(): Creates product array and initializes each one. Then sets Name to ""
* 		-close(): Sets Name to "" and closes Products
* 		
* 	Getters & Setters:
* 		NAME:
* 			-writeName(): Changes String variable to string @param
* 			-readName(): Returns String variable Name
* 		Product Data:
* 			-writeNameP(): Takes String and product reference @param
* 			-readnameP(): Takes @param product reference, and returns name.
* 			-writeQuantP(): Takes @param int quant and int reference to change product quant.
* 							If quant drops below 1, will return 2 if product needs to be closed
* 			-readQuanrP(): Takes int reference and returns product quant value
* 			-writePriceP(): Takes @param int reference and double price to change price variable
* 			-readPriceP(): returns price variable
* 			-writeWeightP(): takes @param int reference and double weight to change weight
* 			-readWeightP(): Takes @param refrence and returns the double value
* 	Other:
* 		-findProduct(): returns reference value of product that has name given in @param
* 		-productCount(): returns number of existing products
* 		-findFreeProduct(): returns reference value of free product, returns -1 if no free products.
*/
//-----------------------------------------------------------------------------------------

public class Depot {
	
	//Variable/Object Types of class Depot
	private Product[] ProductArr;
	private String Name;
	private static final int MAX_P = 5;//Product array max limit
	
	//Constructor
	public Depot() {
		
		ProductArr = new Product[MAX_P];
		
		Name = "";
		
		for(int count = 0; count < ProductArr.length; count++) {
			ProductArr[count] = new Product();
		}
	}
	
	//Closer
	public void close() {
		
		Name = "";
		
		for(int count = 0; count < ProductArr.length; count++) {
			ProductArr[count].close();
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
	 * @return 2 if product deleted, 1 if otherwise
	 * 
	 */
	public int writeQuantP(int quant, int reference) {
		int result;
		result = ProductArr[reference].writeQuant(quant);
		if(result == 2) {
			return 2;
		}
		return 1;
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
	public void writePriceP(double price, int reference) {
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
	public void writeWeightP(double weight, int reference) {
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
	
//------------------------------------------------------------------
//***************PRODUCT GETTERS AND SETTERS************************
//------------------------------------------------------------------
	/**
	 * @param Name of a product
	 * @returns Int on product reference, -1 if doesn't exist
	 * 
	 */

	public int findProduct(String Name) {
		
		for(int count = 0; count < MAX_P; count++) {
			if(ProductArr[count].readName().equals(Name)) {
				return count;
			}
		}
		
		return -1;
	}
	
//------------------------------------------------------------------
//------------------------------------------------------------------
	//Product Weight getters and setters
		
	/**
	 * @param 
	 * @return 
	 * 
	 */
	public int productCount() {
		int val = 0;
		for(int count = 0; count < MAX_P; count ++) {
			if(!(ProductArr[count].readName().equals(""))) {
				val++;
			}
		}
		return val;
	}

//------------------------------------------------------------------
//------------------------------------------------------------------
	/**
	 * @param 
	 * @return, pointer to free product
	 */
	
	public int findFreeProduct() {
		
		for(int count = 0; count < MAX_P; count++) {
			if(ProductArr[count].readName().equals("")) {
				return count;
			}
		}
		
		return -1;
	}
}
