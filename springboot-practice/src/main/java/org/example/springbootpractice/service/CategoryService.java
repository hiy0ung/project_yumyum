package org.example.springbootpractice.service;

import jakarta.validation.Valid;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.category.request.CreateCategoryRequestDto;
import org.example.springbootpractice.dto.category.request.UpdateCategoryRequestDto;
import org.example.springbootpractice.dto.category.response.CategoryResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {
    ResponseDto<List<CategoryResponseDto>> getCategories(String userId, @PathVariable Long id);

    ResponseDto<CategoryResponseDto> createCategory(String userId, @Valid CreateCategoryRequestDto dto);

    ResponseDto<CategoryResponseDto> updateCategory(@Valid UpdateCategoryRequestDto dto);

    ResponseDto<Void> deleteCategory(Long id);
}
