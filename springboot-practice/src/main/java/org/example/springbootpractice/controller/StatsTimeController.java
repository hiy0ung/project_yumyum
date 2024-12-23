package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.stat.response.StatsTimeResponseDto;
import org.example.springbootpractice.service.implement.StatsTimeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.STATS)
@RequiredArgsConstructor
public class StatsTimeController {

    private final StatsTimeServiceImpl statsTimeService;

    public static final String GET_STATS_TIME = "/time/{orderDate}";

    @GetMapping(GET_STATS_TIME) // 2007-12-03T10:15:30
    public ResponseEntity<ResponseDto<List<StatsTimeResponseDto>>> getRevenueByOrderDate(
            @PathVariable String orderDate,
            @AuthenticationPrincipal String userId
    ) {
        ResponseDto<List<StatsTimeResponseDto>> response = statsTimeService.getRevenueByOrderDate(orderDate);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
