package com.project.service;

import java.util.List;

import com.project.dto.OrderRequestDTO;
import com.project.dto.OrderResponseDto;

public interface OrderService {

    public OrderResponseDto placeOrder(List<OrderRequestDTO> orderRequestDTOList);

}
