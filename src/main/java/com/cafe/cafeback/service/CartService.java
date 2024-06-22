package com.cafe.cafeback.service;

import com.cafe.cafeback.dto.CartDTO;
import com.cafe.cafeback.dto.Response;

public interface CartService {

    public Response<String> addToCart(CartDTO cartDTO );
}
