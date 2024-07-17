package com.example.finx.news;

import com.example.finx.news.dto.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NewsController {
    private final AboutNews aboutNews;

    @GetMapping("/news")
    public NewsResponse news() {
        NewsResponse newsResponse = aboutNews.getNews("", "","IT", 6);
        newsResponse.getItems().forEach(item -> {
            item.setDescription(item.getDescription().replace("<b>", ""));
            item.setDescription(item.getDescription().replace("</b>", ""));
        });
        return newsResponse;
    }
}
