package com.milinda.assessment.service.impl;

import com.milinda.assessment.model.Item;
import com.milinda.assessment.repository.ItemRepository;
import com.milinda.assessment.service.ItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    private static final double DELTA = 0.001;

    @MockBean
    private ItemRepository itemRepository;

    @Autowired
    private ItemService service;

    @Test
    public void testTotal(){
        Item item = getItem();
        Mockito.when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));
        Double totVal = service.getTotalValue(item.getId(),25);
        Assert.assertEquals(231.875,totVal.doubleValue(),DELTA);
    }

    private Item getItem(){
        Item item = new Item();
        item.setId(1);
        item.setItemName("Test Penguin Ear");
        item.setPriceOfCartoon(175.00);
        item.setNoOfUnitsInCartoon(20);
        item.setSingleItemPriceFactor(1.3);
        item.setDiscountPercentage(0.3);
        item.setMinCartoonForDiscount(3);
        return item;
    }

}
