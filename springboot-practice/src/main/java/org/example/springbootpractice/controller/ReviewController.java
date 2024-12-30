package org.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ApiMappingPattern;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.review.response.ReviewResponseDto;
import org.example.springbootpractice.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.REVIEW) // /api/v1/reviews
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private  static final String GET_REVIEWS = "/";

    @GetMapping(GET_REVIEWS)
    public ResponseEntity<ResponseDto<List<ReviewResponseDto>>> getAllOrders(
            @AuthenticationPrincipal Long id
    ) {
        ResponseDto<List<ReviewResponseDto>> response = reviewService.getAllReviews(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}

