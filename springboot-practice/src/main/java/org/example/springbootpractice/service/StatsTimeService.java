package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.stat.response.QuantityStatsTimeResponseDto;
import org.example.springbootpractice.dto.stat.response.RevenueStatsTimeResponseDto;

import java.util.List;

public interface StatsTimeService {
    ResponseDto<List<RevenueStatsTimeResponseDto>> getRevenueByHour(String orderDate, Long id);
    ResponseDto<List<QuantityStatsTimeResponseDto>> getQuantityByHour(String orderDate, Long id);
}