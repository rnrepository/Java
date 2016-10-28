/************************************************************************************************
 * 
 * Simple stock managing program
 * 
 ************************************************************************************************/
import java.util.*;
import java.io.*;
public class BookStock {

	
	static Scanner kb = new Scanner(System.in); //global keyboard input
	
	static ArrayList<bookClass> books = new ArrayList<bookClass>(); //create a global array for the object bookClass
	
	
	public static void main(String[] args) throws IOException
	{
		addFile();
		mainMenu();
	}
		
	
		//main menu created as a method so it can be called after each function of the program
		public static void mainMenu() throws IOException
		{
	
		int menuChoice;
		
		System.out.println("\n------------------------------------------------------");
		System.out.println("---------------Book Stock System----------------------");
		System.out.println("------------------------------------------------------\n");
		System.out.println("Pick a choice from the menu\n");
		System.out.println("1. Show stock");
		System.out.println("2. Add stock");
		System.out.println("3. Search for a book");
		System.out.println("4. Edit stock");
		System.out.println("5. Delete stock");
		System.out.println("6. Exit program");

		menuChoice = kb.nextInt();
		
		
		switch (menuChoice) {

			case 1:
				displayStock();
				mainMenu();
				break;
	
			case 2:
				addStock();
				mainMenu();
				break;
				
			case 3:
				searchStock();
				mainMenu();
				break;
				
			case 4:
				alterStock();
				mainMenu();
				break;
				
			case 5:
				deleteStock();
				mainMenu();
				break;
	
			case 6:
				System.out.println("Goodbye!");
				System.exit(0);

			default:
				System.out.println("\nInvalid choice"); 
				mainMenu();
			}
	}
	

	//this method reads the file and adds it into the ArrayList
	public static void addFile() throws IOException
	{
		
		File bookFile = new File ("BookStock.txt");
		BufferedReader book = new BufferedReader(new FileReader(bookFile)); //reads the file into a Buffered Reader
		String line = ""; //creates a line of string for reading data
		
		while ((line = book.readLine()) != null)  //while book has next line
		{ 
			bookClass addBook = new bookClass(line); //Create new object called addbook
			books.add(addBook);	//adds object into ArrayList so it can be read for displaying
		}
		
		book.close(); //closes the buffered reader
	}
	
	
	//This method reads the ArrayList for the book stock and displays it in an organised manner
	public static void displayStock() throws IOException
	{
	
		String bName;
		double price, quantity;
		
		System.out.println("------------------Current books in stock-------------------");
		System.out.printf(" %-3s %-30s  %-10s   %-10s %n"," ", "Book Name", "Price ", "Quantity");
		System.out.println("-----------------------------------------------------------");
		
				
		//reads the ArrayList data that was pushed retrieved from the addFile method
		for (int i =0; i < books.size(); i++)
		{
			bName = books.get(i).bookName;
			price = books.get(i).price;
			quantity = books.get(i).quantity;
			
			System.out.printf("%-3s  %-30s  %-10s   %-10s %n", i+1, bName, price, quantity);
		}
	}
	
	//this function adds the book stock into the BookStock.txt file
	//it also adds it into the ArrayList called books so the values are updated when user displays stock
	public static void addStock() throws IOException
	{
		
		String bName;
		double price, quant; //Declare variables
		
		File bookFile = new File("BookStock.txt"); 
		FileWriter stock = new FileWriter(bookFile, true);
		BufferedWriter addStock = new BufferedWriter(stock); //used to append stock into BookStock.txt
		
		
		System.out.println("------Add books to stock------\n");
		System.out.println("How many books would you like to add?"); //finds out how many books user wants to add
		int amount = kb.nextInt();
		
		for (int i =0; i < amount; i++) //iterates the amount of books to add
		{
			System.out.println("Enter book name " + (i+1) + ": ");
			kb.nextLine(); //eats the next line so the book name can have spaces
			bName = kb.nextLine(); 

			System.out.println("Enter the price: ");
			price = kb.nextDouble();
			
			System.out.println("Enter the quantity: ");
			quant = kb.nextDouble();
			
			//creates a new bookClass object to add to the arraylist
			bookClass addBookToArray = new bookClass(bName, price, quant); 
			books.add(addBookToArray); //adds the book to the arraylist
			
			addStock.write(bName + ", " + price + ", " + quant); //writes the book details to the end of the external file
			addStock.newLine();
			
		
			System.out.println("Book added!"); //so user knows the book was successfully added

		}
		addStock.close();
		
		
	}
	
	
	//searches for books based on user input
	public static void searchStock() throws IOException
	{
		//Search function for the application
		String search;
		
		System.out.println("---Search for a book---\n");
		System.out.println("What book would you like to search for?" );
		kb.nextLine();
		search = kb.nextLine();
		
		System.out.println("Search that contains " + "'" + search + "'\n");
		System.out.printf("%-30s  %-10s   %-10s %n", "Book Name", "Price ", "Quantity");
		System.out.println("------------------------------------------------------");
	
		//searches array for the keyword
		for(int i = 0; i<books.size(); i++){
			if ((books.get(i).bookName).contains(search))
			{
				System.out.printf("%-30s  %-10s   %-10s %n", books.get(i).bookName, books.get(i).price, books.get(i).quantity);
			}
		}

	}
	
	
	//searches for stock user wishes to modify then alters the quantity
	public static void alterStock() throws IOException
	{
		String search;
		
		System.out.println("---Alter Stock---\n\n");
		System.out.println("Search by book to alter stock\n");
		
		System.out.println("What book would you like to search for?" );
		kb.nextLine();
		search = kb.nextLine();
		
		System.out.println("Search that contains " + "'" + search + "'\n");
		System.out.printf("%-30s  %-10s   %-10s %n", "Book Name", "Price ", "Quantity");
		System.out.println("------------------------------------------------------");
	
		//iterates through ArrayList to search for the stock to modify
		for(int i = 0; i<books.size(); i++){
			if ((books.get(i).bookName).contains(search))
			{
				System.out.printf("%-30s  %-10s   %-10s %n", books.get(i).bookName, books.get(i).price, books.get(i).quantity);
				
				
				System.out.println("\nChange the quantity to: ");
				double q = kb.nextDouble();
				
				//replace the quantity of the selected book with the new quantity (q)
				bookClass update = new bookClass(books.get(i).bookName, books.get(i).price, q);
				books.set(i, update);
				System.out.printf("%-30s  %-10s   %-10s %n", books.get(i).bookName, books.get(i).price, books.get(i).quantity);
				
			}
			
		}
		
		
	}
	
	//searches stock then removes selected book
	public static void deleteStock() throws IOException
	{

		String search;
		
		System.out.println("---Delete Stock---\n\n");
		System.out.println("Search by book to remove stock");
		
		System.out.println("What book would you like to search for?\n" );
		kb.nextLine();
		search = kb.nextLine();
		
		System.out.println("Search that contains " + "'" + search + "'\n");
		System.out.printf("%-30s  %-10s   %-10s %n", "Book Name", "Price ", "Quantity");
		System.out.println("------------------------------------------------------");
	
		//iterates through ArrayList to search for the stock to delete
		for(int i = 0; i<books.size(); i++){
			if ((books.get(i).bookName).contains(search))
			{
				System.out.printf("%-30s  %-10s   %-10s %n", books.get(i).bookName, books.get(i).price, books.get(i).quantity);
			
				System.out.println("Would you like to remove the stock " + books.get(i).bookName + "? y/n");
				String confirm = kb.nextLine();
			
				//confirms if user wants to remove the stock of the selected book
				if (confirm.equalsIgnoreCase("y"))
				{
					books.remove(i);
					System.out.println("Stock deleted!");
				}
				
				else
					System.out.println("Did not delete stock!");
			}
			
		}
		
		
	}
	
	
	

}
