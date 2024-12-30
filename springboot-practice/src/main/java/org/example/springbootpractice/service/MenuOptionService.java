package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.menu.request.MenuOptionRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuOptionResponseDto;
import org.example.springbootpractice.dto.ResponseDto;

public interface MenuOptionService {
    ResponseDto<MenuOptionResponseDto> addMenuOption(MenuOptionRequestDto dto, Long id);

    ResponseDto<MenuOptionResponseDto> updateMenuOption(MenuOptionRequestDto dto, Long optionId, Long id);

    ResponseDto<Void> deleteMenuOption(Long optionId, Long id);
}
