package com.example.finx.stock.api;

import com.example.finx.stock.entity.StockEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@AllArgsConstructor
@Getter
public class StockData {
    private List<StockElement> stockElements;

    @Getter
    public static class StockElement {
        private String stockName;
        private Map<String, DailyData> timeSeries;

        public StockElement(Object object, StockEntity stock) {
            this.stockName = stock.getStockName();
            this.timeSeries = setTimeSeries(object);
        }

        private Map<String, DailyData> setTimeSeries(Object object) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.valueToTree(object);

            JsonNode timeSeriesNode = jsonNode.get("Time Series (Daily)");
            Comparator<String> reverseComparator = Comparator.reverseOrder();
            Map<String, DailyData> timeSeries = new TreeMap<>(reverseComparator);
            Iterator<Map.Entry<String, JsonNode>> iterator = timeSeriesNode.fields();
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
}
