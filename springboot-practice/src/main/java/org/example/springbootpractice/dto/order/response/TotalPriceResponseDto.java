package org.example.springbootpractice.dto.order.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TotalPriceResponseDto {
    private int totalPrice;
    private String orderMenuName;
    private Long menuId;

    public TotalPriceResponseDto(int totalPrice, String orderMenuName, Long menuId) {
        this.orderMenuName = orderMenuName;
        this.totalPrice = totalPrice;
        this.menuId = menuId;
    }
}
