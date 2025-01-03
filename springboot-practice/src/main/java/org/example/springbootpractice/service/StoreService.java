package org.example.springbootpractice.service;

import jakarta.validation.Valid;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.store.request.StoreRequestDto;
import org.example.springbootpractice.dto.store.response.StoreResponseDto;

public interface StoreService {

    boolean findByStore(Long id);

    ResponseDto<StoreResponseDto> getStore(Long id);

    ResponseDto<StoreResponseDto> createStore(Long id, @Valid StoreRequestDto dto);

    ResponseDto<StoreResponseDto> updateStore(Long id, @Valid StoreRequestDto dto);

    ResponseDto<String> deleteStore(Long id);
}