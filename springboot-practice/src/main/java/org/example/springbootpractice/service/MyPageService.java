package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.user.request.UserRequestDto;
import org.example.springbootpractice.dto.user.response.UserResponseDto;

public interface MyPageService {
    ResponseDto<UserResponseDto> getAllInfo(Long id);

    ResponseDto<UserResponseDto> updateUserInfo(Long id, UserRequestDto dto);

    ResponseDto<Void> deleteUserInfo(Long id);
}
