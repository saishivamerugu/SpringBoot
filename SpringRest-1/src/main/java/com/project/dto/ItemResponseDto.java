package com.project.dto;

public class ItemResponseDto {

	private long itemId;
	
	private String itemName;
	
	private double price;
	
	private double discount;
	
	private String rating;
	
	private boolean isAvailable;
	 
	private int stock;

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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ItemResponseDto [itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", discount="
				+ discount + ", rating=" + rating + ", isAvailable=" + isAvailable + ", stock=" + stock + "]";
	}

	public ItemResponseDto(long itemId, String itemName, double price, double discount, String rating,
			boolean isAvailable, int stock) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.discount = discount;
		this.rating = rating;
		this.isAvailable = isAvailable;
		this.stock = stock;
	}

	public ItemResponseDto() {
		
	}
	
	
	
}
