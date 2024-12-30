package org.example.springbootpractice.dto.rating.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingStatisticsResponseDto {
    private int rating;
    private Long reviewCount;
}

