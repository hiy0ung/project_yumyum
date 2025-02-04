package org.example.springbootpractice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.store.request.StoreRequestDto;
import org.example.springbootpractice.dto.store.response.StoreResponseDto;
import org.example.springbootpractice.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.STORE)
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    private static final String GET_STORE = "/get";
    private static final String CREATE_STORE = "/create";
    private static final String UPDATE_STORE = "/update";
    private static final String DELETE_STORE = "/delete";

    @GetMapping()
    public Boolean findByStore(@AuthenticationPrincipal Long id) {
        boolean response = storeService.findByStore(id);
        return response;
    }

    @GetMapping(GET_STORE)
    public ResponseEntity<ResponseDto<StoreResponseDto>> getStore(@AuthenticationPrincipal Long id) {
        ResponseDto<StoreResponseDto> response = storeService.getStore(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(CREATE_STORE)
    public ResponseEntity<ResponseDto<StoreResponseDto>> createStore(@AuthenticationPrincipal Long id, @Valid @ModelAttribute StoreRequestDto dto) {
        System.out.println(id);
        ResponseDto<StoreResponseDto> response = storeService.createStore(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping(UPDATE_STORE)
    public ResponseEntity<ResponseDto<StoreResponseDto>> updateStore(@AuthenticationPrincipal Long id, @Valid @ModelAttribute StoreRequestDto dto) {
        ResponseDto<StoreResponseDto> response = storeService.updateStore(id, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DELETE_STORE)
    public ResponseEntity<ResponseDto<String>> deleteStore(@AuthenticationPrincipal Long id) {
        ResponseDto<String> response = storeService.deleteStore(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}