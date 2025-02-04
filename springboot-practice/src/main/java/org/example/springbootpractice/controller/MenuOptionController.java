package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.menu.request.MenuOptionRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuOptionResponseDto;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.service.MenuOptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.MENU_OPTION)
@RequiredArgsConstructor
public class MenuOptionController {

    private final MenuOptionService menuOptionService;

    public static final String OPTION_POST_ADD = "/add";
    public static final String OPTION_PUT_ID = "/update/{optionId}";
    public static final String OPTION_DELETE_ID = "/delete/{optionId}";

    // 추가
    @PostMapping(OPTION_POST_ADD)
    public ResponseEntity<ResponseDto<MenuOptionResponseDto>> addMenuOption(
            @RequestBody MenuOptionRequestDto dto,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<MenuOptionResponseDto> response = menuOptionService.addMenuOption(dto, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 수정
    @PutMapping(OPTION_PUT_ID)
    public ResponseEntity<ResponseDto<MenuOptionResponseDto>> updateMenuOption(
            @RequestBody MenuOptionRequestDto dto,
            @PathVariable Long optionId,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<MenuOptionResponseDto> response = menuOptionService.updateMenuOption(dto, optionId, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 삭제
    @DeleteMapping(OPTION_DELETE_ID)
    public ResponseEntity<ResponseDto<Void>> deleteMenuOption(
            @PathVariable Long optionId,
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<Void> response = menuOptionService.deleteMenuOption(optionId, id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
