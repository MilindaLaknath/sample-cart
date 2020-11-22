package com.milinda.assessment.service.impl;

import com.milinda.assessment.dto.CartDTO;
import com.milinda.assessment.model.Cart;
import com.milinda.assessment.model.CartItem;
import com.milinda.assessment.model.Item;
import com.milinda.assessment.repository.CartItemRepository;
import com.milinda.assessment.repository.CartRepository;
import com.milinda.assessment.repository.ItemRepository;
import com.milinda.assessment.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    @Transactional
    public CartDTO getCart(Integer cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            List<CartItem> cartItems = cartItemRepository.getByCart(optionalCart.get());
            CartDTO cartDTO = optionalCart.get().toDTO();
            cartDTO.setItems(cartItems);
            return cartDTO;
        } else {
            return cartRepository.save(new Cart()).toDTO();
        }
    }

    @Override
    @Transactional
    public CartDTO addItemToCart(Integer cartId, Integer itemId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId).get();
        Item item = itemRepository.findById(itemId).get();
        Optional<CartItem> optionalCartItem = cartItemRepository.getByCartAndItem(cart, item);
        CartItem cartItem;
        if (optionalCartItem.isPresent()) {
            cartItem = optionalCartItem.get();
            cart.setTotal(cart.getTotal() - cartItem.getTotPrice());
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setItem(item);
        }
        cartItem.setCartons(quantity / item.getNoOfUnitsInCartoon());
        cartItem.setSingles(quantity % item.getNoOfUnitsInCartoon());

        Double cartonAmount = cartItem.getCartons() * item.getPriceOfCartoon();
        Double singleAmount = cartItem.getSingles() * (item.getPriceOfCartoon() / item.getNoOfUnitsInCartoon()) * item.getSingleItemPriceFactor();

        cartItem.setAmount(cartonAmount + singleAmount);

        if (cartItem.getCartons() >= item.getMinCartoonForDiscount()) {
            cartItem.setDiscount(cartItem.getAmount() * item.getDiscountPercentage());
        }
        cartItem.setTotPrice(cartItem.getAmount() - cartItem.getDiscount());

        cart.setTotal(cart.getTotal() + cartItem.getTotPrice());

        cartItemRepository.save(cartItem);

        return getCart(cartId);
    }

    @Override
    @Transactional
    public CartDTO removeItemFromCart(Integer cartId, Integer itemId) {
        Cart cart = cartRepository.findById(cartId).get();
        Item item = itemRepository.findById(itemId).get();
        Optional<CartItem> optionalCartItem = cartItemRepository.getByCartAndItem(cart, item);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cart.setTotal(cart.getTotal() - cartItem.getTotPrice());
            cartItemRepository.delete(cartItem);
        }
        return getCart(cartId);
    }

}
