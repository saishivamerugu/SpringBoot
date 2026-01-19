package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.dao.OrderRepository;
import com.project.dao.ProductRepository;
import com.project.dto.OrderItemResponseDto;
import com.project.dto.OrderRequestDTO;
import com.project.dto.OrderResponseDto;
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

    @Override
    @Transactional
	public OrderResponseDto placeOrder(List<OrderRequestDTO> orderRequestDTOList) {
		// i has to build an order by processing it neatly 
		// We got some information 
		// we has to save the information of Orders 
		// If i build the order object correctly the orderItems is filled correctly as 
		// we have the Cascade
		
		Order order = new Order();
		order.setStatus("Ordered");
		List<OrderItems> orderItems = new ArrayList<>();
		
		// iterate over the request DTO s
		for(OrderRequestDTO orderRequestDTO : orderRequestDTOList) {
			// there is a list where i get some items [{productId,quantity and price},{productId 2 quantity 5 and price 28000},{}]
			// now the above is the JSON and now i has to build the order so i has to build the order item 
			// Order item need product information i.e. product id . using the product id from json and use product repository and get the data using product id.. 
			Product product = productRepository.findById(orderRequestDTO.getProductId())
					.orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + orderRequestDTO.getProductId()));
			
			// EveryOrderItem have a quantity
			OrderItems orderItem = new OrderItems();
			if(product.getStock() >= orderRequestDTO.getQuantity()) {
				orderItem.setQuantity(orderRequestDTO.getQuantity());
	
				// set product reference on the order item
				orderItem.setProduct(product);
	
				// link the item back to the order (important for bidirectional mapping)
				orderItem.setOrder(order);
	
				// add built item to the list
				orderItems.add(orderItem);
				
				// update stock using the repository JPQL update method (requires @Modifying on repository)
				// product.getStock() - orderRequestDTO.getQuantity() may be int - long; JPQL accepts the long value
				productRepository.updateStock(product.getProductId(), product.getStock() - orderRequestDTO.getQuantity());
			} else {
                // optional: handle insufficient stock: here we throw an exception (you can change behaviour)
                throw new IllegalStateException("Insufficient stock for product id: " + product.getProductId());
            }
		}

		// attach items to order
		order.setOrderItems(orderItems);
		Order saveOrder = orderRepository.save(order);
		
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		orderResponseDto.setOrderId(saveOrder.getOrderId());
		orderResponseDto.setStatus(order.getStatus());
		List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
		double totalOrderAmout = 0, totalProductPrice = 0;
		// iterate over saved order items (each element is OrderItems)
		for (OrderItems orderItem : saveOrder.getOrderItems()) {
			OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
			// populate dto (adjust/extend if your DTO has more fields)
			orderItemResponseDto.setProductId(orderItem.getProduct().getProductId());
			orderItemResponseDto.setProductName(orderItem.getProduct().getProductName());
			orderItemResponseDto.setEachProductPrice(orderItem.getProduct().getPrice());
			orderItemResponseDto.setQuantity(orderItem.getQuantity());
			totalProductPrice = orderItem.getProduct().getPrice() * orderItem.getQuantity();
			orderItemResponseDto.setTotalProductPrice(totalProductPrice);
			totalOrderAmout += totalProductPrice ;
			// total = quantity * price 
			orderItemResponseDtoList.add(orderItemResponseDto);
		}
		
		orderResponseDto.setOrderItems(orderItemResponseDtoList);
		// set aggregated prices
		orderResponseDto.setOrderPrice(totalOrderAmout);
		orderResponseDto.setTotalAmount(totalOrderAmout);
		// returning a response (you can change to a better response once you save the order)
		return orderResponseDto;
	}
}

