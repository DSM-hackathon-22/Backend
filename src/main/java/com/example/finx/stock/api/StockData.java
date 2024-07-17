package com.example.finx.stock.api;

import com.example.finx.stock.entity.StockEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Getter
public class StockData {
    private MetaData metaData;
    private Map<String, DailyData> timeSeries;

    public StockData(Object object, StockEntity stock) {
        this.metaData = new MetaData(stock.getStockName());
        this.timeSeries = setTimeSeries(object);
    }

    private Map<String, DailyData> setTimeSeries(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        JsonNode timeSeriesNode = jsonNode.get("Time Series (Daily)");
        Iterator<Map.Entry<String, JsonNode>> iterator = timeSeriesNode.fields();
        Map<String, DailyData> timeSeries = new HashMap<>();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            String date = entry.getKey();
            JsonNode dailyDataNode = entry.getValue();

            DailyData dailyData = new DailyData();
            dailyData.setOpen(dailyDataNode.get("1. open").asText());
            dailyData.setHigh(dailyDataNode.get("2. high").asText());
            dailyData.setClose(dailyDataNode.get("4. close").asText());

            timeSeries.put(date, dailyData);
        }
        return timeSeries;
    }

    @Getter
    @AllArgsConstructor
    public static class MetaData {
        private String stockName;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DailyData {
        private String open;
        private String high;
        private String close;
    }
}
