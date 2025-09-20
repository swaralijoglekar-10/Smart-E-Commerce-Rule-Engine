package com.swarali.smart_ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String sku; 
    private String name; 
    private int stockLevel;
    private double price;
    private String status;
    private String currency = "INR";

    public Product(String sku, String name, int stockLevel, double price, String status, String currency) {
        this.sku = sku;
        this.name = name;
        this.stockLevel = stockLevel;
        this.price = price;
        this.status = status;
        this.currency = (currency == null) ? "INR" : currency;
    }
}
