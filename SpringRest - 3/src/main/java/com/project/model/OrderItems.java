package com.project.model;

import jakarta.persistence.*;

/**
 * OrderItems entity maps to "order_items" table.
 *
 * Notes (detailed):
 *  - orderItemId is the primary key and auto-generated.
 *  - quantity stores how many units of the product are in that order item.
 *  - ManyToOne relationship to Order: many order items can belong to one Order.
 *  - ManyToOne relationship to Product: many order items can reference one Product.
 *
 * Your inline comments are preserved exactly as you wrote them.
 *
 * My notes:
 *  - The JoinColumn names (order_Id and product_Id) should match your DB schema.
 *    Consider unifying naming style (snake_case lower) for consistency, e.g. order_id/product_id.
 *  - If you want a bidirectional relationship between Order and OrderItems, define a
 *    @OneToMany(mappedBy="order") in the Order class (not provided here).
 *  - Similarly, Product already has a @OneToMany(mappedBy="product") to this class.
 */
@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderItemId;

    private long quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // I have to know this is coming from which product
    @ManyToOne  // many people one item or many orderitems one product
    @JoinColumn(name = "product_id")
    private Product product;

    // ---------- Constructors ----------
    public OrderItems() {}

    public OrderItems(long quantity, Order order, Product product) {
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public OrderItems(long orderItemId, long quantity, Order order, Product product) {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // I have to know this is coming from which product
    public Product getProduct() {
        return product;
    }

    // many people one item or many orderitems one product
    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "orderItemId=" + orderItemId +
                ", quantity=" + quantity +
                ", order=" + (order != null ? order.getOrderId() : null) +
                ", product=" + (product != null ? product.getProductId() : null) +
                '}';
    }
}
