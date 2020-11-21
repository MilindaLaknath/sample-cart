package com.milinda.assessment.repository;

import com.milinda.assessment.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Integer> {
}
