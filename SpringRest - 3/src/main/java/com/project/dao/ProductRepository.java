package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByProductNameContaining(String productName); 
	

    @Modifying
    @Transactional
	@Query("update Product p set p.stock =:stock where p.productId=:productId")
	void updateStock(long productId, long  stock);
}
