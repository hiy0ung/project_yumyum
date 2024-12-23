package org.example.springbootpractice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootpractice.entity.MenuOption;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuOptionResponseDto {
    private Long menuOptionId;
    private String optionName;

    public MenuOptionResponseDto(MenuOption menuOption) {
        this.menuOptionId = menuOption.getId();
        this.optionName = menuOption.getOptionName();
    }
}

