package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.OrderRequestDTO;
import com.project.dto.OrderResponseDto;
import com.project.model.Order;
import com.project.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/buy") // to place the order
	public OrderResponseDto placeOrder(@RequestBody List<OrderRequestDTO> orderRequestDTOs) {
		return orderService.placeOrder(orderRequestDTOs);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponseDto> getOrderInfo(@PathVariable(name="orderId") long orderId) {
		return orderService.getOrderInfo(orderId);
	}
	
	@DeleteMapping("/cancel")
	public ResponseEntity<OrderResponseDto> cancelItem(
	        @RequestParam(name = "orderItemId") long orderItemId) {

	    return orderService.cancelItem(orderItemId);
	}
}
  