package com.project.dto;

public class OrderRequestDTO {

    private long productId;
    
    private long quantity;
    
    private double price;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(long productId, long quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderRequestDTO(long quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderRequestDto {" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}



//user sends the product Id and quantity  
// we will accept a list of items of OrderRequestDto with particular items to accept multiple items to Order
	