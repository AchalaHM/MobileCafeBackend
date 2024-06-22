package com.cafe.cafeback.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {

    private int userId;
    private List<CartItemDTO> items;
}
