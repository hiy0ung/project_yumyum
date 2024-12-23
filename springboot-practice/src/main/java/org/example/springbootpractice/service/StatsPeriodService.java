package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.stat.response.DailySalesResponseDto;
import org.example.springbootpractice.dto.stat.response.MonthSalesResponseDto;
import org.example.springbootpractice.dto.stat.response.YearSalesResponseDto;

import java.util.List;

public interface StatsPeriodService {
    ResponseDto<List<DailySalesResponseDto>> findDailySales(String orderDate);

    ResponseDto<List<MonthSalesResponseDto>> findMonthSales(String orderDate);

    ResponseDto<List<YearSalesResponseDto>> findYearSales(String orderDate);

}
