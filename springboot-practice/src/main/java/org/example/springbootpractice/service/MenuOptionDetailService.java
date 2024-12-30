package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.menu.request.MenuOptionDetailRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuOptionDetailResponseDto;
import org.example.springbootpractice.dto.ResponseDto;

public interface MenuOptionDetailService {
    ResponseDto<MenuOptionDetailResponseDto> addOptionDetail(MenuOptionDetailRequestDto dto, Long id);

    ResponseDto<MenuOptionDetailResponseDto> updateOptionDetail(MenuOptionDetailRequestDto dto, Long optionDetailId, Long id);

    ResponseDto<Void> deleteOptionDetail(Long optionDetailId, Long id);
}
