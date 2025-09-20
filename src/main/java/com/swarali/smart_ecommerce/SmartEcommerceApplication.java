package com.swarali.smart_ecommerce;

import com.swarali.smart_ecommerce.entity.Product;
import com.swarali.smart_ecommerce.rule.Rule;
import com.swarali.smart_ecommerce.rule.RuleService;
import com.swarali.smart_ecommerce.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SmartEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartEcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner ruleRunner(RuleService ruleService, ProductService productService) {
        return args -> {

            List<Product> products = productService.getAllProducts();
            for (Product p : products) {
                if (p.getCurrency() == null) {
                    p.setCurrency("INR");
                    productService.saveProduct(p);
                }
            }

            // Ensure the rules don't get added multiple times
            if (ruleService.getAllRules().isEmpty()) {
                System.out.println("Adding sample rules...");
                ruleService.saveRule(new Rule(null, "stockLevel", "<", 10.0, "SET_STATUS:LOW_STOCK", "Alert when product stock is below 10 units."));
                ruleService.saveRule(new Rule(null, "stockLevel", ">", 50.0, "SET_STATUS:HIGH_STOCK", "Set status for high stock"));
                //ruleService.saveRule(new Rule(null, "stockLevel", ">=", 10.0, "SET_STATUS:ACTIVE", "Set status for normal stock levels"));
                //ruleService.saveRule(new Rule(null, "stockLevel", "<=", 50.0, null, null));
                //above rule is already handled

                ruleService.saveRule(new Rule(null, "price", ">", 5000.0, "CONVERT_TO_USD", "Convert prices over 5000 INR to USD"));
            }

        };
    }

}
