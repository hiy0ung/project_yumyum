package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.menu.request.MenuOptionRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuOptionResponseDto;
import org.example.springbootpractice.dto.ResponseDto;

public interface MenuOptionService {
    ResponseDto<MenuOptionResponseDto> addMenuOption(MenuOptionRequestDto dto);

    ResponseDto<MenuOptionResponseDto> updateMenuOption(MenuOptionRequestDto dto, Long id);

    ResponseDto<Void> deleteMenuOption(Long id);
}
