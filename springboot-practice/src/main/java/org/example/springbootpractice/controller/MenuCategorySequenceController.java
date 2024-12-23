package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.menu.request.MenuCategoryRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuCategoryResponseDto;
import org.example.springbootpractice.service.MenuCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.CATEGORY)
@RequiredArgsConstructor
public class MenuCategorySequenceController {
    private final MenuCategoryService menuCategoryService;
    private static final String MENU_CATEGORY_GET = "/get";
    private static final String MENU_CATEGORY_SEQUENCE = "/sequence";

    @GetMapping(MENU_CATEGORY_GET)
    public ResponseEntity<ResponseDto<List<MenuCategoryResponseDto>>> getMenuCategory() {
        ResponseDto<List<MenuCategoryResponseDto>> response = menuCategoryService.getAllMenuCategory();
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(MENU_CATEGORY_SEQUENCE)
    public ResponseEntity<ResponseDto<List<MenuCategoryResponseDto>>> updateMenuCategorySequence(@RequestBody MenuCategoryRequestDto dto) {
        ResponseDto<List<MenuCategoryResponseDto>> response = menuCategoryService.updateSequenceCategory(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

}
