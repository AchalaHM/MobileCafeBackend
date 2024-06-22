package com.cafe.cafeback.controller;

import com.cafe.cafeback.dto.CartDTO;
import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("addToCart")
    public ResponseEntity<Response<String>> addToCart(@RequestBody CartDTO cartDTO){
        return ResponseEntity.ok(cartService.addToCart(cartDTO));
    }
}
