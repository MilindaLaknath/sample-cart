package com.milinda.assessment.service;

import com.milinda.assessment.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    ItemDTO addItem(ItemDTO itemDTO);

    void deleteItem(Integer itemId);

    ItemDTO getItem(Integer itemId);

    List<ItemDTO> getItems();

    Double getTotalValue(Integer itemId, Integer quantity);

}
