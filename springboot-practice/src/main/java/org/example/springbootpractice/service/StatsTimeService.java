package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.stat.response.StatsTimeResponseDto;

import java.util.List;

public interface StatsTimeService {
    ResponseDto<List<StatsTimeResponseDto>> getRevenueByOrderDate(String orderDate);
}