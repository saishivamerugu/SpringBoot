package com.project.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.dto.OrderRequestDTO;
import com.project.dto.OrderResponseDto;

public interface OrderService {

    public OrderResponseDto placeOrder(List<OrderRequestDTO> orderRequestDTOList);

	public ResponseEntity<OrderResponseDto> getOrderInfo(long orderId);

	public ResponseEntity<OrderResponseDto> cancelItem(long orderItemId);
	


}
