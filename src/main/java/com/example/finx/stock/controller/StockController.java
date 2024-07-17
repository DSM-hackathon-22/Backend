package com.example.finx.stock.controller;

import com.example.finx.stock.api.StockClient;
import com.example.finx.stock.api.StockData;
import com.example.finx.stock.service.StockService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StockController {
    private final StockService stockService;
    private final StockClient stockClient;

    @GetMapping("/stock")
    public Object getStock() {
        return stockClient.getStock("", "", "");
//        return stockService.getStock();
    }
}
