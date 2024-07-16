package com.example.finx.user.controller;

import com.example.finx.user.dto.UserDto;
import com.example.finx.user.jwt.TokenResponse;
import com.example.finx.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public TokenResponse signUp(@RequestBody UserDto userDto){
        return userService.signUp(userDto);
    }
    @PostMapping("/login")
    public TokenResponse login(@RequestBody UserDto userDto){
        return userService.login(userDto);
    }
}
