package com.milinda.assessment.controller;

import com.milinda.assessment.dto.CartItemReqDTO;
import com.milinda.assessment.dto.CartDTO;
import com.milinda.assessment.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping()
    ResponseEntity<CartDTO> getCart(){
        return new ResponseEntity<CartDTO>(cartService.getCart(0),HttpStatus.OK);
    }

    @GetMapping("/{cart_id}")
    ResponseEntity<CartDTO> getCart(@PathVariable("cart_id") Integer cartId){
        return new ResponseEntity<CartDTO>(cartService.getCart(cartId),HttpStatus.OK);
    }

    @PostMapping("/{cart_id}")
    ResponseEntity<CartDTO> addTOCart(@PathVariable("cart_id") Integer cartId, @RequestBody CartItemReqDTO addItemReq){
        CartDTO cartDTO = cartService.addItemToCart(cartId, addItemReq.getItemId(), addItemReq.getQuantity());
        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{cart_id}/{item_id}")
    ResponseEntity<CartDTO> removeFromCart(@PathVariable("cart_id") Integer cartId, @PathVariable("item_id") Integer itemId){
        CartDTO cartDTO = cartService.removeItemFromCart(cartId, itemId);
        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.OK);
    }

}
