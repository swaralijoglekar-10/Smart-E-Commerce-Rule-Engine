package com.swarali.smart_ecommerce.repository;

import com.swarali.smart_ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p.status, COUNT(p) from Product p group by p.status")
    List<Object[]> countByStatus();
}
