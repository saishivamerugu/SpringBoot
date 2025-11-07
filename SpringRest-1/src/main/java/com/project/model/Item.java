package com.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Item { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long itemId;
	
	@Column(nullable=false,unique=true)  
	private String itemName;
	
	@Column(nullable=false)
	private double price;
	
	private double discount;
	
	@Column(nullable=false)
	private int stock;
	
	@Column(nullable=false)
	private boolean isAvailable;
	
	private double rating;

	public Item(String itemName, double price, double discount, int stock, boolean isAvailable,
			double rating) {
		super(); 
		this.itemName = itemName;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.isAvailable = isAvailable;
		this.rating = rating;
	}
	
	public Item() {
		
	}
	
	
	public Item(long itemId,String itemName, double price, double discount, int stock, boolean isAvailable,
			double rating) {
		super(); 
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.isAvailable = isAvailable;
		this.rating = rating;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", discount=" + discount
				+ ", stock=" + stock + ", isAvailable=" + isAvailable + ", rating=" + rating + "]";
	}
	
}
