package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.rating.response.RatingMonthResponseDto;
import org.example.springbootpractice.dto.rating.response.RatingStatisticsResponseDto;

import java.util.List;

public interface RatingService {
    ResponseDto<List<RatingStatisticsResponseDto>> getReviewCountByRating(Long id);

    ResponseDto<List<RatingMonthResponseDto>> getAvgRatingByMonth(Long id, String date);
}
