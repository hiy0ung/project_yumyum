package org.example.springbootpractice.dto.menu.request;

import lombok.Data;

@Data
public class MenuCategoryRequestDto {
    private Long id;
    private String menuCategory;
    private int MenuCategorySequence;
}
