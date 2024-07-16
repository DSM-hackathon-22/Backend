package com.example.finx.stock.api;

import lombok.Getter;

import java.util.List;

@Getter
public class TimeSeries {
    private List<StockDate> stockDates;

    @Getter
    public static class StockDate {
        private String open;
        private String high;
        private String low;
    }
}
