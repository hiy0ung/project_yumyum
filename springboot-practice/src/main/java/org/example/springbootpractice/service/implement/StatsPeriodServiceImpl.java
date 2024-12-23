package org.example.springbootpractice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.stat.response.DailySalesResponseDto;
import org.example.springbootpractice.dto.stat.response.MonthSalesResponseDto;
import org.example.springbootpractice.dto.stat.response.YearSalesResponseDto;
import org.example.springbootpractice.repository.StatsPeriodRepository;
import org.example.springbootpractice.service.StatsPeriodService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsPeriodServiceImpl implements StatsPeriodService {
    private final StatsPeriodRepository periodRepository;

    @Override
    public ResponseDto<List<DailySalesResponseDto>> findDailySales(String orderDate) {
        List<DailySalesResponseDto> data = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();

            List<Object[]> convertDto = periodRepository.findDailySales(year, month);

            data = convertDto.stream()
                    .map(dto -> new DailySalesResponseDto(
                                    ((java.sql.Date) dto[0]).toLocalDate(),
                                    ((Long) dto[1]).intValue()
                            )
                    ).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<MonthSalesResponseDto>> findMonthSales(String orderDate) {
        List<MonthSalesResponseDto> data = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();
            List<Object[]> convertDto = periodRepository.findMonthSales(year, month);

            data = convertDto.stream()
                    .map(dto -> new MonthSalesResponseDto(
                            ((Integer) dto[0]),
                            ((Integer) dto[1]),
                            ((Long) dto[2]).intValue()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<YearSalesResponseDto>> findYearSales(String orderDate) {
        List<YearSalesResponseDto> data = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            List<Object[]> convertDto = periodRepository.findYearSales(year);

            data = convertDto.stream()
                    .map(dto -> new YearSalesResponseDto(
                            ((Integer) dto[0]),
                            ((Long) dto[1]).intValue()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}