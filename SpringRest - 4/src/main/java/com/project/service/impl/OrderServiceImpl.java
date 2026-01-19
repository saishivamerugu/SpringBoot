package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.OrderRepository;
import com.project.dao.ProductRepository;
import com.project.dao.OrderItemRepository;
import com.project.dto.OrderItemResponseDto;
import com.project.dto.OrderRequestDTO;
import com.project.dto.OrderResponseDto;
import com.project.exceptions.OrderNotFoundException;
import com.project.exceptions.OrderItemNotFoundException;
import com.project.model.Order;
import com.project.model.OrderItems;
import com.project.model.Product;
import com.project.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final ProductRepository productRepository = null;

    @Autowired
    private final OrderRepository orderRepository = null;
    
    @Autowired
    OrderItemRepository orderItemRepository = null;

    @Override
    @Transactional
    public OrderResponseDto placeOrder(List<OrderRequestDTO> orderRequestDTOList) {

        Order order = new Order();
        order.setStatus("Ordered");
        List<OrderItems> orderItems = new ArrayList<>();

        for (OrderRequestDTO orderRequestDTO : orderRequestDTOList) {

            Product product = productRepository.findById(orderRequestDTO.getProductId())
                    .orElseThrow(() ->
                            new IllegalArgumentException("Product not found with id: "
                                    + orderRequestDTO.getProductId()));

            if (product.getStock() < orderRequestDTO.getQuantity()) {
                throw new IllegalStateException(
                        "Insufficient stock for product id: " + product.getProductId());
            }

            OrderItems orderItem = new OrderItems();
            orderItem.setQuantity(orderRequestDTO.getQuantity());
            orderItem.setProduct(product);
            orderItem.setOrder(order);

            orderItems.add(orderItem);

            productRepository.updateStock(
                    product.getProductId(),
                    product.getStock() - orderRequestDTO.getQuantity()
            );
        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        return buildOrderResponseDtoFromOrder(savedOrder);
    }

    @Override
    public ResponseEntity<OrderResponseDto> getOrderInfo(long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No Order Found!..."));
        OrderResponseDto response = buildOrderResponseDtoFromOrder(order);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private OrderResponseDto buildOrderResponseDtoFromOrder(Order order) {

        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(order.getOrderId());
        response.setStatus(order.getStatus());

        List<OrderItemResponseDto> items = new ArrayList<>();
        double totalAmount = 0;

        for (OrderItems orderItem : order.getOrderItems()) {

            OrderItemResponseDto dto = new OrderItemResponseDto();
            dto.setProductId(orderItem.getProduct().getProductId());
            dto.setProductName(orderItem.getProduct().getProductName());
            dto.setEachProductPrice(orderItem.getProduct().getPrice());
            dto.setQuantity(orderItem.getQuantity());

            double totalProductPrice =
                    orderItem.getProduct().getPrice() * orderItem.getQuantity();

            dto.setTotalProductPrice(totalProductPrice);
            totalAmount += totalProductPrice;

            items.add(dto);
        }

        response.setOrderItems(items);
        response.setOrderPrice(totalAmount);
        response.setTotalAmount(totalAmount);

        return response;
    }

    @Override
    public ResponseEntity<OrderResponseDto> cancelItem(long orderItemId) {

        OrderItems orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("No Order Item Found..."));
        orderItemRepository.delete(orderItem);
        long productId = orderItem.getProduct().getProductId();
        int stock = orderItem.getProduct().getStock();
        productRepository.updateStock(productId, stock);
        return ResponseEntity.noContent().build();
    }

}


