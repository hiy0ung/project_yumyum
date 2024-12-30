package org.example.springbootpractice.service;

import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.review.response.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ResponseDto<List<ReviewResponseDto>> getAllReviews(Long id);
}
