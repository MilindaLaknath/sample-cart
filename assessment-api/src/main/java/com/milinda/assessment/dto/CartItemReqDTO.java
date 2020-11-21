package com.milinda.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude
public class CartItemReqDTO {
    @JsonProperty(value = "item_id")
    Integer itemId;

    @JsonProperty(value = "quantity")
    Integer quantity;
}
