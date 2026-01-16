package com.project.service;

import java.util.List;

import com.project.dto.ProductRequestDto;
import com.project.dto.ProductResponseDto;

public interface ProductService {
	
    ProductResponseDto save(ProductRequestDto productRequestDto);

	List<ProductResponseDto> getProucts();
 
	ProductResponseDto getProduct(long id);

	List<ProductResponseDto> getProductByName(String productName);

	List<ProductResponseDto> saveAllProducts(List<ProductRequestDto> productRequestDtos);

	ProductResponseDto updateProductByRating(long id, double rating);

	ProductResponseDto deleteProduct(long id); 
   
}
   