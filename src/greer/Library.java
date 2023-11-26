package greer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Library {
	
	int newSKU = 1;
	ArrayList<Book> myList = new ArrayList<Book>();
	
	public Library() {
		loadData();
	}
	
	public void createBookEntry(String title, double price, int quantity) {
		Book createdBook = new Book(newSKU, title, price, quantity);
		newSKU++;
		addBook(createdBook);
	}
	
	public void addBook(Book book){
		myList.add(book);
		saveData();
	}
	
	public void removeBook(int SKU) {
		
		Book bookToRemove = getBook(SKU);
		if(bookToRemove != null) {
			myList.remove(bookToRemove);
			System.out.println("Book successfully removed");
			saveData();
			return;
		}
		
		System.out.println("No book found with that SKU");
		
	}
	
	public Book getBook(int SKU) {
		
		for(int i = 0; i < myList.size(); i++) {
			if(myList.get(i).getSKU() == SKU) {
				return myList.get(i);
			}
		}
		
		return null;
		
	}
	
	public ArrayList<Book> getInventoryList() {
		return myList;
	}
	
	public void saveData() {
		String filePath = "C:\\Users\\hunte\\eclipse-workspace\\Software Engineering\\Homework 8\\assets\\data.txt";
		
		File file = new File(filePath);
		
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(myList);
            System.out.println("ArrayList has been serialized and written to the file.");

        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
		
		String skuStore = "C:\\Users\\hunte\\eclipse-workspace\\Software Engineering\\Homework 8\\assets\\SKU.txt";
		File skuStoreFile = new File(skuStore);
		
		// Write the integer to the file
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(skuStoreFile))) {
            dos.writeInt(newSKU);
            System.out.println("Integer has been written to the file.");

        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
        
	}
	
	public void loadData() {
		// Specify the file path
        String filePath = "C:\\Users\\hunte\\eclipse-workspace\\Software Engineering\\Homework 8\\assets\\data.txt";

        // Deserialize the ArrayList from the file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            // Cast the read object to ArrayList
            myList = (ArrayList<Book>) ois.readObject();

            // Print the deserialized ArrayList
            System.out.println("Deserialized ArrayList: " + myList);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
            e.printStackTrace();
        }
        
     // Specify the file path
        String skuStore = "C:\\Users\\hunte\\eclipse-workspace\\Software Engineering\\Homework 8\\assets\\SKU.txt";
        File skuStoreFile = new File(skuStore);

        // Read the integer from the file
        try (DataInputStream dis = new DataInputStream(new FileInputStream(skuStoreFile))) {
            newSKU = dis.readInt();
            System.out.println("Read integer from file: " + newSKU);

        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: " + e.getMessage());
            e.printStackTrace();
        }
	}
	
}
