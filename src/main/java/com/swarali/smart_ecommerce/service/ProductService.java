package com.swarali.smart_ecommerce.service;

import com.swarali.smart_ecommerce.entity.Product;
import com.swarali.smart_ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // save product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
        // database will generate the unique id of product after saving the object
        // So, we need to return Product to use its id later
    }

    //get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Map<String, Long> getProductCountByStatus() {
        return productRepository.countByStatus().stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1]
                ));
    }
}
