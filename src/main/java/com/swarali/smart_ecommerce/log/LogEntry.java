package com.swarali.smart_ecommerce.log;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogEntry {
    private String timestamp; 
    private String message;
}
