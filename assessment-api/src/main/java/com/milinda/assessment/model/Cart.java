package com.milinda.assessment.model;

import com.milinda.assessment.dto.CartDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer cartId;

    @Column(name = "total")
    Double total=0.0;

    public CartDTO toDTO(){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(getCartId());
        cartDTO.setItems(new ArrayList<>());
        cartDTO.setTotal(getTotal());
        return cartDTO;
    }

}
