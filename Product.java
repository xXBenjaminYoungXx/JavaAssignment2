package Assignment2;
//-----------------------------------------------------------------------------------------
/*
* Authors: Benjamin Young and Ryan McCormack
* Student ID's: c3330653 c3329771
* Email: BenjaminYoung7198@gmail.com  ryanmack@bigpond.com
*/
//-----------------------------------------------------------------------------------------
public class Product {
	//Object Variables
	private String Name;
	private int quantity;
	private double price;
	private double weight;
	
	//Constructor
	public Product() {
		Name = "";
		quantity = 0;
		price = 0;
		weight = 0;
		return;
	}
	
	//Closer
	public void close() {
		Name = "";
		quantity = 0;
		price = 0;
		weight = 0;
		return;
	}
	
	//------------------------------------------------------------------
	//------------------------------------------------------------------
	//Name getters/Setters

	/**
	 * @param String Name
	 * @return Nothing
	 * 
	 */
	public void writeName(String name) {
		Name = name;
		return;
	}
	
	/**
	 * @param Nothing
	 * @returns String Name
	 * 
	 */
	public String readName() {
		return Name;
	}
	
	//------------------------------------------------------------------
	//------------------------------------------------------------------
	//quantity getters/Setters

	/**
	 * @param Int quant
	 * @return 2 for product closed, 1 for product quant changed to value above 0
	 * 
	 */
	public int writeQuant(int quant) {
		quantity = quant;
		if(quantity <=0) {
			this.close();
			return 2;
		}
		return 1;
	}
	
	/**
	 * @param String Name
	 * @returns Nothing
	 * 
	 */
	public int readQuant() {
		return quantity;
	}
	
	//------------------------------------------------------------------
	//------------------------------------------------------------------
	//price getters/Setters

	/**
	 * @param double price
	 * @return Nothing
	 * 
	 */
	public void writePrice(double Price) {
		price = Price;
		return;
	}
	
	/**
	 * @param Nothing
	 * @returns double price
	 * 
	 */
	public double readPrice() {
		return price;
	}
	
	//------------------------------------------------------------------
	//------------------------------------------------------------------
	//Weight getters/Setters

	/**
	 * @param double weight
	 * @return Nothing
	 * 
	 */
	public void writeWeight(double Weight) {
		weight = Weight;
	}
	
	/**
	 * @param Nothing
	 * @returns double weight
	 * 
	 */
	public double readWeight() {
		return weight;
	}
	
	//------------------------------------------------------------------
	//------------------------------------------------------------------

}
