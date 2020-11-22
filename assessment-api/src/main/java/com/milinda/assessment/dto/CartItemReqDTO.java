package com.milinda.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@JsonInclude
@Validated
public class CartItemReqDTO {
    @JsonProperty(value = "item_id")
    Integer itemId;

    @JsonProperty(value = "quantity")
    @NotEmpty
    @Min(value = 1)
    Integer quantity;
}
