package org.example.springbootpractice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.auth.request.LoginRequestDto;
import org.example.springbootpractice.dto.auth.request.SignUpRequestDto;
import org.example.springbootpractice.dto.auth.response.LoginResponseDto;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.auth.response.SignUpResponseDto;
import org.example.springbootpractice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    public static final String SIGN_UP_PATH = "/signUp";
    public static final String LOGIN_PATH = "/login";

    @PostMapping(SIGN_UP_PATH)
    public ResponseEntity<ResponseDto<SignUpResponseDto>> signUp(@Valid @RequestBody SignUpRequestDto dto) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto dto) {
        ResponseDto<LoginResponseDto> response = authService.login(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }


}
