package com.example.finx.Interests.controller;

import com.example.finx.Interests.dto.InterestsDto;
import com.example.finx.Interests.service.InterestedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.TableGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "관심 분야", description = "관심 분야 관련 API")
@RestController
@RequiredArgsConstructor
public class InterestsController {

    private final InterestedService interestedService;
    @PostMapping("/interested")
    @Operation(summary = "관심 분야 저장")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "저장 성공"),
            @ApiResponse(responseCode = "400", description = "BED REQUEST"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public void interested(@RequestBody InterestsDto interestsDto){
        interestedService.save(interestsDto);
    }


}
