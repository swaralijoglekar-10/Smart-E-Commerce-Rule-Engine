package com.swarali.smart_ecommerce.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyService {

    @Value("${api.currency.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Fetches the exchange rate from one currency to another using an external API.
     *
     * @param fromCurrency The currency code to convert from (e.g., "INR").
     * @param toCurrency   The currency code to convert to (e.g., "USD").
     * @return The conversion rate as a Double, or null if the API call fails.
     */
    public Double getExchangeRate(String fromCurrency, String toCurrency) {
        try {
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + fromCurrency + "/" + toCurrency;
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);

            if (response != null && response.has("conversion_rate")) {
                return response.get("conversion_rate").asDouble();
            }
        } catch (Exception e) {
            log.error("Failed to get exchange rate: {}", e.getMessage());
        }
        return null;
    }
}