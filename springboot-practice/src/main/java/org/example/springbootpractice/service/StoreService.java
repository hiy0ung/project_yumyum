package org.example.springbootpractice.service;

import jakarta.validation.Valid;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.store.request.StoreRequestDto;
import org.example.springbootpractice.dto.store.response.StoreResponseDto;

public interface StoreService {

    boolean findByStore(String userId);

    ResponseDto<StoreResponseDto> getStore(String userId);

    ResponseDto<StoreResponseDto> createStore(String userId, @Valid StoreRequestDto dto);

    ResponseDto<StoreResponseDto> updateStore(String userId, @Valid StoreRequestDto dto);

    ResponseDto<Void> deleteStore(String userId);
}
