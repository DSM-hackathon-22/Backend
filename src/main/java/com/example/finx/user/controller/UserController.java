package com.example.finx.user.controller;

import com.example.finx.user.dto.UserDto;
import com.example.finx.user.jwt.TokenResponse;
import com.example.finx.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "유저",description = "유저 관련 API")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/user")
    @Operation(summary = "유저 회원 가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "회원 가입 성공",content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "500",description = "INTERNAL SERVER ERROR")
    })
    public TokenResponse signUp(@RequestBody UserDto userDto){
        return userService.signUp(userDto);
    }

    @PostMapping("/login")
    @Operation(summary = "유저 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "로그인 성공",content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public TokenResponse login(@RequestBody UserDto userDto){
        return userService.login(userDto);
    }
}
