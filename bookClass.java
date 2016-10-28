	/************************************************************************************************
 * Author: Rachel Nasr

 ************************************************************************************************/

public class bookClass {
	
	String bookName; //these are not private as they will be used in the driver program
	double price, quantity;
	
	
	public bookClass (String tokenize) //read a line (string) and split the data into 3 seperate variables
	{
		String[] stock = tokenize.split(",", 3); //splits the value of the line 3 times to seperate the data
		
		bookName = stock[0]; //book name is the first value, so it is assigned to the right variable
		price = Double.parseDouble(stock[1]);
		quantity = Double.parseDouble(stock[2]);
		
	}
	
	public bookClass (String bName, double price1, double quant) //in case user wants to manually add stock
	{
		bookName = bName;
		price = price1;
		quantity = quant;
	}
	
	public String BookName()
	{
		return bookName;
	}
	
	public double price()
	{
		return price;
	}

	public double quantity()
	{
		return quantity;
	}
}
