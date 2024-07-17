package com.example.finx.news;

import com.example.finx.Interests.entity.InterestedEntity;
import com.example.finx.Interests.repository.InterestedRepository;
import com.example.finx.config.FinxProperties;
import com.example.finx.news.dto.NewsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "뉴스", description = "뉴스 관련 API")
@RequiredArgsConstructor
@RestController
public class NewsController {
    private final AboutNews aboutNews;
    private final FinxProperties finxProperties;
    private final InterestedRepository interestedRepository;

    @GetMapping("/news")
    @Operation(summary = "뉴스 제공")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "뉴스 제공 성공", content = @Content(schema = @Schema(implementation = NewsResponse.class))),
        @ApiResponse(responseCode = "400", description = "BED REQUEST"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public NewsResponse news() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        InterestedEntity interestedEntity = interestedRepository.findByUserId(Long.valueOf(userId));

        NewsResponse newsResponse = aboutNews.getNews(finxProperties.getNaverClientId(), finxProperties.getNaverSecretKey(), interestedEntity.getKeyword(), 6);
        newsResponse.getItems().forEach(item -> {
            item.setDescription(item.getDescription().replace("<b>", ""));
            item.setDescription(item.getDescription().replace("</b>", ""));
        });
        return newsResponse;
    }
}
