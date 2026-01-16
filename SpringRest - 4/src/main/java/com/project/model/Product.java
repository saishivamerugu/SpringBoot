package com.project.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

/**
 * Product entity represents a product in the system.
 *
 * Notes (detailed):
 *  - This entity maps to the "product" table.
 *  - productId is the primary key and is auto-generated.
 *  - productName is unique and not nullable.
 *  - price and stock are required (non-nullable).
 *  - orderItems is a collection of OrderItems that reference this product.
 *    We use a bidirectional relationship: Product (one) <-> OrderItems (many).
 *
 * My notes:
 *  - I set cascade = CascadeType.ALL on orderItems so that when a Product is
 *    persisted/removed, associated OrderItems are managed automatically.
 *    Use this carefully in production (you might not want deletes to cascade).
 *  - I used FetchType.LAZY to avoid loading order items unless required.
 *  - equals() and hashCode() are based on productId which is the identifier.
 *    This is standard for JPA entities but be careful when using transient (non-persisted)
 *    entities in sets before they have an id — they may be considered equal or not as intended.
 */
@Entity
@Table(name = "product")
public class Product {

    // ---------- Fields ----------
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

    @Column(nullable = false)
    private int stock;

    // Relationship with OrderItems
    // mappedBy = "product" -> matches the 'product' field in OrderItems
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItems> orderItems;

    // ---------- Constructors ----------
    public Product() {}

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
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.stock = stock;
    }

    public Product(Long productId, String productName, double price, double discount, double rating,
                   boolean isAvailable, int stock, List<OrderItems> orderItems) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.stock = stock;
        this.orderItems = orderItems;
    }

    // ---------- Getters & Setters ----------
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

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

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    // ---------- Utility Methods ----------
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", rating=" + rating +
                ", isAvailable=" + isAvailable +
                ", stock=" + stock +
                '}';
    }

}
