package com.milinda.assessment.repository;

import com.milinda.assessment.model.Cart;
import com.milinda.assessment.model.CartItem;
import com.milinda.assessment.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
    List<CartItem> getByCart(Cart cart);
    Optional<CartItem> getByCartAndItem(Cart cart, Item item);
}
