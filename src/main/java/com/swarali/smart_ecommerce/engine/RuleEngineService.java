package com.swarali.smart_ecommerce.engine;

import com.swarali.smart_ecommerce.entity.Product;
import com.swarali.smart_ecommerce.log.LogEntry;
import com.swarali.smart_ecommerce.rule.Rule;
import com.swarali.smart_ecommerce.rule.RuleService;
import com.swarali.smart_ecommerce.service.CurrencyService;
import com.swarali.smart_ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RuleEngineService {  // orchestrator to connect product data with rule data

    private final ProductService productService;
    private final RuleService ruleService;
    private final CurrencyService currencyService;

    public List<LogEntry> executeRules() {

        log.info("-------Starting rule execution engine-------");
        List<Product> products = productService.getAllProducts();
        List<Rule> rules = ruleService.getAllRules();
        List<LogEntry> logEntries = new ArrayList<>();

        for (Product product : products) {
            boolean ruleMatched = false;
            for (Rule rule : rules) {
                if (evaluateCondition(product, rule)) {
                    String logMessage = "Rule Matched! Product: " + product.getName() + " - Rule: " + rule.getRuleDescription();
                    logEntries.add(new LogEntry(LocalDateTime.now().toString(), logMessage));
                    log.info(logMessage);
                    // Perform the action
                    performAction(product, rule.getActionString(), logEntries);
                    ruleMatched = true;
                }
            }

            if (!ruleMatched || product.getStatus() == null) {
                // default status ACTIVE
                product.setStatus("ACTIVE");
                productService.saveProduct(product);
                String logMessage = "ACTION: Setting status of " + product.getName() + " to " + "ACTIVE";
                logEntries.add(new LogEntry(LocalDateTime.now().toString(), logMessage));
                log.info(logMessage);
            }
        }
        log.info("--------Rule execution engine finished-------");
        return logEntries;
    }

    private boolean evaluateCondition(Product product, Rule rule) {
        double fieldValue = 0.0;
        if ("stockLevel".equals(rule.getFieldName())) {
            fieldValue = product.getStockLevel();
        }
        
        else if("price".equals(rule.getFieldName())) {
            fieldValue = product.getPrice();
        }

        switch (rule.getOperator()) {
            case ">":
                return fieldValue > rule.getThresholdValue();
            case "<":
                return fieldValue < rule.getThresholdValue();
            case ">=":
                return fieldValue >= rule.getThresholdValue();
            case "<=":
                return fieldValue <= rule.getThresholdValue();
            case "==":
                return fieldValue == rule.getThresholdValue();
            default:
                log.warn("Unknown operator: {}", rule.getOperator());
                return false;
        }
    }

    private void performAction(Product product, String action, List<LogEntry> logEntries) {

        if (action.startsWith("SET_STATUS")) {
            String newStatus = action.replace("SET_STATUS:", "");
            product.setStatus(newStatus);
            productService.saveProduct(product);
            String logMessage = "ACTION: Setting status of " + product.getName() + " to " + newStatus;
            logEntries.add(new LogEntry(LocalDateTime.now().toString(), logMessage));
            log.info(logMessage);
        }

        else if (action.equals("CONVERT_TO_USD")) {
            Double rate = currencyService.getExchangeRate(product.getCurrency(), "USD");
            if (rate != null) {
                double newPrice = product.getPrice() * rate;
                product.setPrice(newPrice);
                product.setCurrency("USD");
                productService.saveProduct(product);

                String logMessage = "ACTION: Converted " + product.getName() + " price from " + product.getCurrency() + " to USD. New price: " + String.format("%.2f", newPrice) + " USD";
                logEntries.add(new LogEntry(LocalDateTime.now().toString(), logMessage));
                log.info(logMessage);
            } else {
                log.error("Failed to convert currency for product: {}", product.getName());
            }
        }

    }
}
