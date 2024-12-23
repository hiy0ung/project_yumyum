package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.order.response.OrderDetailResponseDto;
import org.example.springbootpractice.dto.ResponseDto;

import java.util.List;

public interface OrderDetailService {
    ResponseDto<List<OrderDetailResponseDto>> getOrderDetail(Long id);
}
