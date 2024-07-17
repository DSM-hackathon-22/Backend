package com.example.finx.news;

import com.example.finx.news.dto.NewsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "finx", url = "https://openapi.naver.com/v1/search/news.json")
public interface AboutNews{
    @GetMapping
    NewsResponse getNews(
            @RequestHeader("X-Naver-Client-Id") String clientId,
            @RequestHeader("X-Naver-Client-Secret") String secretKey,
            @RequestParam String query
    );
}
