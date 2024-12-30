package org.example.springbootpractice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.stat.response.StatsMenuResponseDto;
import org.example.springbootpractice.repository.StatsMenuRepository;
import org.example.springbootpractice.service.StatsMenuService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsMenuServiceImpl implements StatsMenuService {

    private final StatsMenuRepository statsMenuRepository;

    @Override
    public ResponseDto<List<StatsMenuResponseDto>> getTodaySalesByOrderDate(Long id) {
        List<StatsMenuResponseDto> data = null;

        List<Object[]> convertDto;
        try {
            convertDto = statsMenuRepository.findTodayTotalPriceAndQuantityByOrderDate(id);
            data = convertDto.stream()
                    .map(dto -> new StatsMenuResponseDto(
                            ((java.sql.Date) dto[0]).toLocalDate(),
                            (String) dto[1],
                            ((Long) dto[2]).intValue(),
                            ((BigDecimal) dto[3]).longValue()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<StatsMenuResponseDto>> getDaySalesByOrderDate(String orderDate, Long id) {
        List<StatsMenuResponseDto> data = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate localDate = LocalDate.parse(orderDate, formatter);

            int year = localDate.getYear();
            int month = localDate.getMonthValue();
            int day = localDate.getDayOfMonth();

            List<Object[]> convertDto = statsMenuRepository.findDayTotalPriceAndQuantityByOrderDate(year, month, day, id);
            data = convertDto.stream()
                    .map(dto -> new StatsMenuResponseDto(
                            ((java.sql.Date) dto[0]).toLocalDate(),
                            (String) dto[1],
                            ((Long) dto[2]).intValue(),
                            ((BigDecimal) dto[3]).longValue()
                    )).collect(Collectors.toList());



        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<StatsMenuResponseDto>> getMonthSalesByOrderDate(String orderDate, Long id) {
        List<StatsMenuResponseDto> data = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate localDate = LocalDate.parse(orderDate, formatter);

            int year = localDate.getYear();
            int month = localDate.getMonthValue();

            List<Object[]> convertDto = statsMenuRepository.findMonthTotalPriceAndQuantityByOrderDate(year, month, id);
            data = convertDto.stream()
                    .map(dto -> new StatsMenuResponseDto(
                            ((java.sql.Date) dto[0]).toLocalDate(),
                            (String) dto[1],
                            ((Long) dto[2]).intValue(),
                            ((BigDecimal) dto[3]).longValue()
                    )).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
