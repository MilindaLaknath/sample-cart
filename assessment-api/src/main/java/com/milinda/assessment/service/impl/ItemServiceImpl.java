package com.milinda.assessment.service.impl;

import com.milinda.assessment.dto.ItemDTO;
import com.milinda.assessment.model.Item;
import com.milinda.assessment.repository.ItemRepository;
import com.milinda.assessment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    @Transactional
    public ItemDTO addItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setItemName(itemDTO.getItemName());
        item.setNoOfUnitsInCartoon(itemDTO.getNoOfUnitsInCartoon());
        item.setPriceOfCartoon(itemDTO.getPriceOfCartoon());
        item.setImageUrl(itemDTO.getImageUrl());
        return itemRepository.save(item).toDTO();
    }

    @Override
    public void deleteItem(Integer itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public ItemDTO getItem(Integer itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            return item.get().toDTO();
        } else {
            return new Item().toDTO();
        }
    }

    @Override
    public List<ItemDTO> getItems() {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        itemRepository.findAll().forEach(item -> itemDTOS.add(item.toDTO()));
        return itemDTOS;
    }

    @Override
    public Double getTotalValue(Integer itemId, Integer quantity) {
        if (quantity<=0){
            return 0.0;
        }
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();

            Integer cartons = quantity / item.getNoOfUnitsInCartoon();
            Integer singles = quantity % item.getNoOfUnitsInCartoon();

            Double cartonAmount = cartons * item.getPriceOfCartoon();
            Double singleAmount = singles * (item.getPriceOfCartoon() / item.getNoOfUnitsInCartoon()) * item.getSingleItemPriceFactor();

            return cartonAmount + singleAmount;
        } else {
            return 0.0;
        }
    }

}
