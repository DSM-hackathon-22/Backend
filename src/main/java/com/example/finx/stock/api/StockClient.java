package com.example.finx.stock;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock_client", url = "https://www.alphavantage.co")
public interface StockClient {
    @GetMapping("/query")
    Object getStock(
        @RequestParam String function,
        @RequestParam String symbol
    );
}
