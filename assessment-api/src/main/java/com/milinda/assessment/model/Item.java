package com.milinda.assessment.model;


import com.milinda.assessment.dto.ItemDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "no_of_units_in_cartoon")
    private Integer noOfUnitsInCartoon;

    @Column(name = "price_of_cartoon")
    private Double priceOfCartoon;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "single_item_price_factor")
    private Double singleItemPriceFactor = 1.3;

    @Column(name = "discount_percentage")
    private Double discountPercentage = 0.1;

    @Column(name = "min_cartoon_for_discount")
    private Integer minCartoonForDiscount = 3;

    public ItemDTO toDTO(){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(getId());
        itemDTO.setItemName(getItemName());
        itemDTO.setNoOfUnitsInCartoon(getNoOfUnitsInCartoon());
        itemDTO.setPriceOfCartoon(getPriceOfCartoon());
        itemDTO.setImageUrl(getImageUrl());
        itemDTO.setSingleItemPriceFactor(getSingleItemPriceFactor());
        itemDTO.setDiscountPercentage(getDiscountPercentage());
        itemDTO.setMinCartoonForDiscount(getMinCartoonForDiscount());
        return itemDTO;
    }

}