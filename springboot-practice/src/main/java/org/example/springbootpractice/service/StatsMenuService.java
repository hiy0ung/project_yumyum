package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.stat.response.StatsMenuResponseDto;
import java.util.List;


public interface StatsMenuService {
    ResponseDto<List<StatsMenuResponseDto>> getTodaySalesByOrderDate(Long id);

    ResponseDto<List<StatsMenuResponseDto>> getDaySalesByOrderDate(String orderDate, Long id);

    ResponseDto<List<StatsMenuResponseDto>> getMonthSalesByOrderDate(String orderDate, Long id);
}