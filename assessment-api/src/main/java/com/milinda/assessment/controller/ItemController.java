package com.milinda.assessment.controller;

import com.milinda.assessment.dto.ItemDTO;
import com.milinda.assessment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping
    ResponseEntity<ItemDTO> saveItem(@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity(itemService.addItem(itemDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteItem(@PathVariable("id") Integer itemId) {
        itemService.deleteItem(itemId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    ResponseEntity<ItemDTO> getItem(@PathVariable("id") Integer itemId) {
        ItemDTO itemDTO = itemService.getItem(itemId);
        return new ResponseEntity<ItemDTO>(itemDTO, HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<ItemDTO>> getItem() {
        List<ItemDTO> itemDTOList = itemService.getItems();
        return new ResponseEntity<List<ItemDTO>>(itemDTOList, HttpStatus.OK);
    }

    @GetMapping("/calc/{id}")
    ResponseEntity<Double> calculateTotal(@PathVariable("id") Integer itemId, @RequestParam("qty") Integer qty){
        Double totVal = itemService.getTotalValue(itemId,qty);
        return new ResponseEntity<Double>(totVal, HttpStatus.OK);
    }

}
