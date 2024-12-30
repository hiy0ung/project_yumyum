package org.example.springbootpractice.service;

import jakarta.validation.Valid;
import org.example.springbootpractice.dto.menu.request.MenuRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuGetResponseDto;
import org.example.springbootpractice.dto.menu.response.MenuResponseDto;
import org.example.springbootpractice.dto.ResponseDto;
import java.util.List;


public interface MenuService {
    ResponseDto<MenuResponseDto> addMenu(@Valid MenuRequestDto dto, Long id);
    ResponseDto<List<MenuGetResponseDto>> getAllMenus(Long id);
    ResponseDto<MenuGetResponseDto> getMenusById(Long menuId, Long id);
    ResponseDto<MenuResponseDto> updateMenu(@Valid Long menuId, MenuRequestDto dto, Long id);
    ResponseDto<Void> deleteMenu(Long menuId, Long id);
}
