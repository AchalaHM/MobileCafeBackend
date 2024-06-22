package com.cafe.cafeback.service.impl;

import com.cafe.cafeback.dto.CartDTO;
import com.cafe.cafeback.dto.CartItemDTO;
import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.entity.Cart;
import com.cafe.cafeback.entity.CartItems;
import com.cafe.cafeback.entity.MobilesCategory;
import com.cafe.cafeback.entity.User;
import com.cafe.cafeback.repository.CartRepository;
import com.cafe.cafeback.repository.MobileCategoryRepository;
import com.cafe.cafeback.repository.UserRepository;
import com.cafe.cafeback.service.CartService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final MobileCategoryRepository mobileCategoryRepository;

    Logger logger = Logger.getLogger(CartServiceImpl.class);

    public CartServiceImpl(UserRepository userRepository, CartRepository cartRepository, MobileCategoryRepository mobileCategoryRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.mobileCategoryRepository = mobileCategoryRepository;
    }

    @Override
    public Response<String> addToCart(CartDTO cartDTO ) {
        try {
            Optional<User> userOptional = userRepository.findById(cartDTO.getUserId());
            if (userOptional.isEmpty()){
                return new Response<>(1002, "User not found" , null);
            }

            Optional<Cart> cartOptional = cartRepository.findByUser(userOptional.get());
            Cart cart;

            if (cartOptional.isEmpty()){
                cart = new Cart();
                cart.setUser(userOptional.get());
                cart.setTotalPrice(0.0);
            } else {
                cart = cartOptional.get();
            }

            double totalPrice = 0.0;

            List<CartItemDTO> itemDTOs = new ArrayList<>();
            for (CartItemDTO itemDTO : cartDTO.getItems()) {

                Optional<MobilesCategory> mobilesCategory = mobileCategoryRepository.findById(itemDTO.getMobileId());

                if(mobilesCategory.isEmpty()){
                    return new Response<>(1002, "Mobile category not found" , null);
                }

                MobilesCategory mobile = mobilesCategory.get();

                CartItems cartItem = new CartItems();
                cartItem.setCart(cart);
                cartItem.setMobilesCategory(mobile);
                cartItem.setQuantity(itemDTO.getQuantity());
                cartItem.setPrice(mobile.getPrice() * itemDTO.getQuantity());
                cart.getItems().add(cartItem);

                totalPrice += cartItem.getPrice();

                CartItemDTO savedItemDTO = new CartItemDTO();
                savedItemDTO.setMobileId(mobile.getId());
                savedItemDTO.setQuantity(cartItem.getQuantity());
                itemDTOs.add(savedItemDTO);
            }

            cart.setTotalPrice(totalPrice);
            cart = cartRepository.save(cart);

            CartDTO responseCartDTO = new CartDTO();
            responseCartDTO.setUserId(cart.getUser().getId());
            responseCartDTO.setItems(itemDTOs);

            logger.info("Items added to cart successfully");
            return new Response<>(1000, "Items added to cart successfully", responseCartDTO.toString());
        }catch (Exception e){
            logger.info("Error happen while add to cart");
            return  new Response<>(1001, "Error happen while add to cart" , null);
        }
    }
}
