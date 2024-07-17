package com.example.finx.news.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class NewsResponse {
    private List<Item> items;

    @Getter
    @Setter
    public static class Item {
        private String link;
        private String description;
    }

}
