package org.example.springbootpractice.dto.menu.request;

import lombok.Data;

@Data
public class MenuOptionDetailRequestDto {
    private Long menuOptionId;
    private String optionDetailName;
    private int additionalFee;
}