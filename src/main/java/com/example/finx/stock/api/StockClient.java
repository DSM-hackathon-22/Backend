package com.example.finx.stock.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stockclient", url = "https://www.alphavantage.co")
public interface StockClient {
    @GetMapping("/query")
    Object getStock(
        @RequestParam("function") String function,
        @RequestParam("symbol") String symbol,
        @RequestParam("apikey") String apikey
    );
}
