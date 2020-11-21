package com.milinda.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.milinda.assessment.model.CartItem;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude
public class CartDTO {
    @JsonProperty(value = "cart_id")
    Integer cartId;

    @JsonProperty(value = "items")
    List<CartItem> items;

    @JsonProperty(value = "total")
    Double total;

}
