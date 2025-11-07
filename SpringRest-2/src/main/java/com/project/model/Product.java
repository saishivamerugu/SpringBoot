package com.project.model;

import jakarta.persistence.*;
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(nullable = false, unique = true)
    private String productName;

    @Column(nullable = false)
    private double price;

    private double discount;
    private double rating;
    private boolean isAvailable;
    private int stock;

    public Product() {}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product(String productName, double price, double discount, double rating,
                   boolean isAvailable, int stock) {
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.stock = stock;
    }

    
    public Product(Long productId, String productName, double price, double discount, double rating,
			boolean isAvailable, int stock) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.discount = discount;
		this.rating = rating;
		this.isAvailable = isAvailable;
		this.stock = stock;
	}

	public String getProductName() {
        return productName;
    }

    // ✅ FIXED SETTER
    public void setProductName(String productName) {
        this.productName = productName;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
