package com.project.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id") 
	private long orderId;
	
	private String status;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItems> orderItems;
	
	public Order() { }
	
	public Order(String status, List<OrderItems> orderItems) {
		this.status = status;
		this.orderItems = orderItems;
	}
	
	public Order(long orderId, String status, List<OrderItems> orderItems) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.orderItems = orderItems;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	} 

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", status=" + status + "]";
	}
}
