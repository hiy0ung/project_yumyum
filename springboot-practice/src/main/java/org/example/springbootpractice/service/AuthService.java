package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.auth.request.LoginRequestDto;
import org.example.springbootpractice.dto.auth.request.SignUpRequestDto;
import org.example.springbootpractice.dto.auth.response.LoginResponseDto;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.auth.response.SignUpResponseDto;

public interface AuthService {
    ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto);

    ResponseDto<LoginResponseDto> login(LoginRequestDto dto);
}
