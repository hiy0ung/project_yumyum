package org.example.springbootpractice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.menu.request.MenuRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuGetResponseDto;
import org.example.springbootpractice.dto.menu.response.MenuResponseDto;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU)
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    public static final String MENU_POST_ADD = "/add";
    public static final String MENU_GET_LIST = "/";
    public static final String MENU_GET_ID = "/{menuId}";
    public static final String MENU_PUT_UPDATE = "/update/{menuId}";
    public static final String MENU_DELETE = "/delete/{menuId}";


    // 메뉴 추가
    @PostMapping(MENU_POST_ADD)
    public ResponseEntity<ResponseDto<MenuResponseDto>> addMenu(@Valid @RequestBody MenuRequestDto dto, @AuthenticationPrincipal Long id) {
        ResponseDto<MenuResponseDto> result = menuService.addMenu(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 메뉴 조회
    @GetMapping(MENU_GET_LIST)
    public ResponseEntity<ResponseDto<List<MenuGetResponseDto>>> getAllMenus(@AuthenticationPrincipal Long id) {
        ResponseDto<List<MenuGetResponseDto>> result = menuService.getAllMenus(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 특정 ID 메뉴 조회
    @GetMapping(MENU_GET_ID)
    public ResponseEntity<ResponseDto<MenuGetResponseDto>> getMenusById(@PathVariable Long menuId, @AuthenticationPrincipal Long id) {
        ResponseDto<MenuGetResponseDto> result = menuService.getMenusById(menuId, id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 메뉴 수정
    @PutMapping(MENU_PUT_UPDATE)
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu(
            @Valid @PathVariable Long menuId,
            @RequestBody MenuRequestDto dto,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<MenuResponseDto> result = menuService.updateMenu(menuId, dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 메뉴 삭제
    @DeleteMapping(MENU_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteMenu(@PathVariable Long menuId, @AuthenticationPrincipal Long id) {
        ResponseDto<Void> result = menuService.deleteMenu(menuId, id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}