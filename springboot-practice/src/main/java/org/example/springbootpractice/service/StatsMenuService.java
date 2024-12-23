package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.stat.response.StatsMenuResponseDto;
import java.util.List;


public interface StatsMenuService {
    ResponseDto<List<StatsMenuResponseDto>> getTodaySalesByOrderDate();

    ResponseDto<List<StatsMenuResponseDto>> getDaySalesByOrderDate(String orderDate);

    ResponseDto<List<StatsMenuResponseDto>> getMonthSalesByOrderDate(String orderDate);
}