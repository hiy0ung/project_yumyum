package org.example.springbootpractice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.stat.response.QuantityStatsTimeResponseDto;
import org.example.springbootpractice.dto.stat.response.RevenueStatsTimeResponseDto;
import org.example.springbootpractice.repository.StatsTimeRepository;
import org.example.springbootpractice.service.StatsTimeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsTimeServiceImpl implements StatsTimeService {
    private final StatsTimeRepository statsTimeRepository;

    @Override
    public ResponseDto<List<RevenueStatsTimeResponseDto>> getRevenueByHour(String orderDate, Long id) {
        List<RevenueStatsTimeResponseDto> data = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();
            int day = localDateTime.getDayOfMonth();

            List<Object[]> convertDto = statsTimeRepository.findRevenueByHour(year, month, day, id);

            data = convertDto.stream()
                    .map(dto -> new RevenueStatsTimeResponseDto(
                            ((java.sql.Date) dto[0]).toLocalDate(),
                            (Integer) dto[1],
                            ((BigDecimal) dto[2]).longValue()
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<QuantityStatsTimeResponseDto>> getQuantityByHour(String orderDate, Long id) {
        List<QuantityStatsTimeResponseDto> data = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(orderDate, formatter);

            int year = localDateTime.getYear();
            int month = localDateTime.getMonthValue();
            int day = localDateTime.getDayOfMonth();

            List<Object[]> convertDto = statsTimeRepository.findOrderQuantityByHour(year, month, day, id);

            data = convertDto.stream()
                    .map(dto -> new QuantityStatsTimeResponseDto(
                            ((java.sql.Date)dto[0]).toLocalDate(),
                            (Integer) dto[1],
                            (Long) dto[2]
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
