package com.milinda.assessment.service;

import com.milinda.assessment.dto.CartDTO;

public interface CartService {

    CartDTO getCart(Integer cartId);

    CartDTO addItemToCart(Integer cartId, Integer itemId, Integer quantity);

    CartDTO removeItemFromCart(Integer cartId, Integer itemId);

}
