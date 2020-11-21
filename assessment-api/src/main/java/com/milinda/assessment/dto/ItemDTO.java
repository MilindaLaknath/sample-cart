package com.milinda.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude
public class ItemDTO {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "item_name")
    private String itemName;

    @JsonProperty(value = "cartoon_size")
    private Integer noOfUnitsInCartoon;

    @JsonProperty(value = "cartoon_price")
    private Double priceOfCartoon;

    @JsonProperty(value = "img_url")
    private String imageUrl;

    @JsonProperty(value = "single_item_Price_factor")
    private Double singleItemPriceFactor = 1.3;

    @JsonProperty(value = "discount_percentage")
    private Double discountPercentage = 0.1;

    @JsonProperty(value = "min_cartoon_for_discount")
    private Integer minCartoonForDiscount = 3;

}
