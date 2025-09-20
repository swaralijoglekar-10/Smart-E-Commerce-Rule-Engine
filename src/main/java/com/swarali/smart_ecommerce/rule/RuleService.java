package com.swarali.smart_ecommerce.rule;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RuleService {
    
    private final RuleRepository ruleRepository; 
    
    public List<Rule> getAllRules(){
        return ruleRepository.findAll();
    }
    
    public void saveRule(Rule rule){
        ruleRepository.save(rule);
    }
}
