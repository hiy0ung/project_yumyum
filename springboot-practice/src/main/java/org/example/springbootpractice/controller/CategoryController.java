package org.example.springbootpractice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.category.request.CreateCategoryRequestDto;
import org.example.springbootpractice.dto.category.request.UpdateCategoryRequestDto;
import org.example.springbootpractice.dto.category.response.CategoryResponseDto;
import org.example.springbootpractice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.CATEGORY)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private static final String CREATE_CATEGORY = "/create";
    private static final String UPDATE_CATEGORY = "/update";
    private static final String DELETE_CATEGORY = "/delete";

    @GetMapping()
    public ResponseEntity<ResponseDto<List<CategoryResponseDto>>> getCategories(@AuthenticationPrincipal String userId, @PathVariable Long id) {
        ResponseDto<List<CategoryResponseDto>> response = categoryService.getCategories(userId, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(CREATE_CATEGORY)
    public ResponseEntity<ResponseDto<CategoryResponseDto>> createCategory(@AuthenticationPrincipal String userId, @Valid @RequestBody CreateCategoryRequestDto dto) {
        ResponseDto<CategoryResponseDto> response = categoryService.createCategory(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(UPDATE_CATEGORY)
    public  ResponseEntity<ResponseDto<CategoryResponseDto>> updateCategory(@Valid @RequestBody UpdateCategoryRequestDto dto) {
        ResponseDto<CategoryResponseDto> response = categoryService.updateCategory(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DELETE_CATEGORY)
    public ResponseEntity<ResponseDto<Void>> deleteCategory(@RequestParam Long id) {
        ResponseDto<Void> response = categoryService.deleteCategory(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
