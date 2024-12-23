package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.order.response.OrderResponseDto;
import org.example.springbootpractice.dto.ResponseDto;

import java.util.List;

public interface OrderService {
    ResponseDto<List<OrderResponseDto>> getAllOrders();

    ResponseDto<OrderResponseDto> updateOrderState(Long id, String updateOrderState);
}