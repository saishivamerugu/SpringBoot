package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.project.dao.ProductRepository;
import com.project.dto.ProductRequestDto;
import com.project.dto.ProductResponseDto;
import com.project.model.Product;
import com.project.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto save(ProductRequestDto dto) {

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setDiscount(dto.getDiscount());
        product.setStock(dto.getStock());
        product.setAvailable(true);
        product.setRating(4.5);
        Product saved = productRepository.save(product);
        ProductResponseDto response = new ProductResponseDto();
        BeanUtils.copyProperties(saved, response);
        return response;
    }
    
    @Override
    public List<ProductResponseDto> getProucts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponse = buildProductResponseList(products);
        return productResponse;
    }

	private List<ProductResponseDto> buildProductResponseList(List<Product> products) {
		List<ProductResponseDto> productResponse = new ArrayList<>();
        for (Product product : products) {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            BeanUtils.copyProperties(product, productResponseDto);  
            productResponse.add(productResponseDto);
        }
		return productResponse;
	} 

    @Override
    public ProductResponseDto getProduct(long id) {
        Product product = productRepository.findById(id).get();
        ProductResponseDto dto = new ProductResponseDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

	@Override
	public List<ProductResponseDto> getProductByName(String productName) {
		List<Product> byProductNameContaining = productRepository.findByProductNameContaining(productName);
		List<ProductResponseDto> productResponse = buildProductResponseList(byProductNameContaining);
        return productResponse;
	}

	@Override
	public List<ProductResponseDto> saveAllProducts(List<ProductRequestDto> productRequestDtos) {
		List<Product> products = new ArrayList<>();
		
		for(ProductRequestDto productRequestDto : productRequestDtos) {
			Product product = new Product();
			BeanUtils.copyProperties(productRequestDto, product);
			product.setAvailable(true);
		    product.setRating(4.5);
			products.add(product);
		}
		List<Product> savedProducts = productRepository.saveAll(products);
		List<ProductResponseDto> productResponseList = buildProductResponseList(savedProducts);
		return productResponseList; 
	}

	@Override
	public ProductResponseDto updateProductByRating(long id, double rating) {
	    Optional<Product> optionalProduct = productRepository.findById(id);
	    if (optionalProduct.isPresent()) {
	        Product product = optionalProduct.get();
	        product.setRating(rating);
	        Product updated = productRepository.save(product);
	        ProductResponseDto productResponseDto = new ProductResponseDto();
	        BeanUtils.copyProperties(updated, productResponseDto);
	        return productResponseDto;
	    }
	    return new ProductResponseDto();
	}
	
	@Override
	public ProductResponseDto deleteProduct(long id) {
	    Optional<Product> optionalProduct = productRepository.findById(id);
	    ProductResponseDto productResponseDto = new ProductResponseDto();
	    if (optionalProduct.isPresent()) {
	        BeanUtils.copyProperties(optionalProduct.get(), productResponseDto);
	        productRepository.deleteById(id);
	        return productResponseDto;  
	    }
	    return new ProductResponseDto();
	}


}
