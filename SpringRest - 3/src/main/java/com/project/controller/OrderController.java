package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
