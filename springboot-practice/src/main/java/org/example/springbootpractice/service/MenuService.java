package org.example.springbootpractice.service;

import jakarta.validation.Valid;
import org.example.springbootpractice.dto.menu.request.MenuRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuGetResponseDto;
import org.example.springbootpractice.dto.menu.response.MenuResponseDto;
import org.example.springbootpractice.dto.ResponseDto;
import java.util.List;


public interface MenuService {
    ResponseDto<MenuResponseDto> addMenu(@Valid MenuRequestDto dto);
    ResponseDto<List<MenuGetResponseDto>> getAllMenus();
    ResponseDto<MenuGetResponseDto> getMenusById(Long id);
    ResponseDto<MenuResponseDto> updateMenu(@Valid Long id, MenuRequestDto dto);
    ResponseDto<Void> deleteMenu(Long id);
}
