package com.example.finx.stock.controller;

import com.example.finx.stock.api.StockClient;
import com.example.finx.stock.api.StockData;
import com.example.finx.stock.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "주식", description = "주식 관련 API")
@RequiredArgsConstructor
@RestController
public class StockController {
    private final StockService stockService;
    private final StockClient stockClient;

    @Operation(summary = "유저가 선호하는 분야의 주식 2개 정보 가져오기")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "주식 정보 가져오기 성공", content = @Content(schema = @Schema(implementation = StockData.class))),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/stock")
    public StockData getStock() {
        return stockService.getStock();
    }
}
