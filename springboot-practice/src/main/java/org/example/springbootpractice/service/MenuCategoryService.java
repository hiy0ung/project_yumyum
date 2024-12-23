package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.menu.request.MenuCategoryRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuCategoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MenuCategoryService {
    ResponseDto<List<MenuCategoryResponseDto>> getAllMenuCategory();
    ResponseDto<List<MenuCategoryResponseDto>> updateSequenceCategory(MenuCategoryRequestDto dto);
}
