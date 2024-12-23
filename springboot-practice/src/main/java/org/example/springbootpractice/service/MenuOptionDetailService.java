package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.menu.request.MenuOptionDetailRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuOptionDetailResponseDto;
import org.example.springbootpractice.dto.ResponseDto;

public interface MenuOptionDetailService {
    ResponseDto<MenuOptionDetailResponseDto> addOptionDetail(MenuOptionDetailRequestDto dto);

    ResponseDto<MenuOptionDetailResponseDto> updateOptionDetail(MenuOptionDetailRequestDto dto, Long id);

    ResponseDto<Void> deleteOptionDetail(Long id);
}
