package greer;

import java.io.Serializable;

public class Book implements Serializable {
	
	private int SKU;
	private String title;
	private double price;
	private int quantity;
	
	public Book(int SKU, String title, double price, int quantity) {
		this.SKU = SKU;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
	}

	public int getSKU() {
		return SKU;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

}
