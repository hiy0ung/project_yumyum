package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.rating.response.RatingMonthResponseDto;
import org.example.springbootpractice.dto.rating.response.RatingStatisticsResponseDto;
import org.example.springbootpractice.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.REVIEW)
@RequiredArgsConstructor
public class RatingController {
    private static final String getRating = "/rating";
    private static final String getAvgRating = "/rating/month";

    private final RatingService ratingService;

    @GetMapping(getRating)
    public ResponseEntity<ResponseDto<List<RatingStatisticsResponseDto>>> getReviewCountByRating(@AuthenticationPrincipal Long id) {
        ResponseDto<List<RatingStatisticsResponseDto>> response = ratingService.getReviewCountByRating(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(getAvgRating)
    public ResponseEntity<ResponseDto<List<RatingMonthResponseDto>>> getAvgRatingByMonth(@AuthenticationPrincipal Long id, @RequestParam String date) {
        ResponseDto<List<RatingMonthResponseDto>> response = ratingService.getAvgRatingByMonth(id, date);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}
