package com.example.finx.Interests.controller;

import com.example.finx.Interests.dto.InterestsDto;
import com.example.finx.Interests.service.InterestedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InterestsController {

    private final InterestedService interestedService;

    @PostMapping("/interested")
    public void interested(@RequestBody InterestsDto interestsDto){
        interestedService.save(interestsDto);
    }


}
