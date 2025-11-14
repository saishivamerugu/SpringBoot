package com.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.project.dto.ProductRequestDto;
import com.project.dto.ProductResponseDto;
import com.project.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController { 

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    } 

    @PostMapping("/save")
    public ProductResponseDto save(@RequestBody ProductRequestDto dto) {
        return productService.save(dto);
    }
     
    @GetMapping("/get-products")
    public List<ProductResponseDto> getProducts(){
    	return productService.getProucts();
    }
    
    @GetMapping("/id/{id}")
    public ProductResponseDto getProductById(@PathVariable long id) {
    	return productService.getProduct(id);
    }
    
    @GetMapping
    public List<ProductResponseDto> getProductByName(@RequestParam String productName) {
        return productService.getProductByName(productName);
    } 
    
    @PostMapping("/save-all-products")
    public List<ProductResponseDto> saveAllProducts(@RequestBody List<ProductRequestDto> productRequestDtos){
    	return productService.saveAllProducts(productRequestDtos);
    } 
    
    @PostMapping("/update/{id}/{rating}")
    public ProductResponseDto updateProductRating(
            @PathVariable long id,
            @PathVariable double rating) {

        return productService.updateProductByRating(id, rating);
    }
    
    @DeleteMapping("/delete/{id}")
    public ProductResponseDto deleteProduct(@PathVariable long id) {
        return productService.deleteProduct(id);
    }
    


}
