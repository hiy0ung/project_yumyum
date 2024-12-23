package org.example.springbootpractice.dto.stat.response;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsTimeResponseDto {
    private LocalDate date;
    private Integer hour;
    private Long revenue;
}
