package org.example.springbootpractice.service;

import jakarta.validation.Valid;
import org.example.springbootpractice.dto.auth.request.*;
import org.example.springbootpractice.dto.auth.response.*;
import org.example.springbootpractice.dto.ResponseDto;

public interface AuthService {
    ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseDto<UserIdDuplicationCheckResponseDto> userIdDuplicationCheck(@Valid UserIdDuplicationCheckRequestDto dto);
    ResponseDto<UserBusinessNumberDuplicationCheckResponseDto> userBusinessNumberDuplicationCheck(@Valid UserBusinessNumberDuplicationCheckRequestDto dto);
    ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto dto);
    ResponseDto<UserEmailDuplicationCheckResponseDto> userEmailDuplicationCheck(@Valid UserEmailDuplicationCheckRequestDto dto);
}
