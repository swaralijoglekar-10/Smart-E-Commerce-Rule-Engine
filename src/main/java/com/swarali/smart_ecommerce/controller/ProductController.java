package com.swarali.smart_ecommerce.controller;

import com.swarali.smart_ecommerce.engine.RuleEngineService;
import com.swarali.smart_ecommerce.entity.Product;
import com.swarali.smart_ecommerce.log.LogEntry;
import com.swarali.smart_ecommerce.rule.RuleService;
import com.swarali.smart_ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final RuleEngineService ruleEngineService;
    private final RuleService ruleService;
    private List<LogEntry> ruleLog = new ArrayList<>();

    @Autowired
    public ProductController(ProductService productService, RuleEngineService ruleEngineService, RuleService ruleService) {
        this.productService = productService;
        this.ruleEngineService = ruleEngineService;
        this.ruleService = ruleService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    //add product form
    @GetMapping("/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("ruleLog", ruleLog);
        model.addAttribute("productCounts", productService.getProductCountByStatus());
        return "dashboard";
    }

    @PostMapping("/dashboard/run")
    public String runRules() {
        ruleLog = ruleEngineService.executeRules();
        return "redirect:/products/dashboard";
    }
    
    @GetMapping("/rules")
    public String listRules(Model model) {
        model.addAttribute("rules", ruleService.getAllRules()); 
        return "rules";
    }
}
