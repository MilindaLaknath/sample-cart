package com.milinda.assessment.controller;

import com.milinda.assessment.dto.ItemDTO;
import com.milinda.assessment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDTO> saveItem(@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.addItem(itemDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable("id") Integer itemId) {
        itemService.deleteItem(itemId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable("id") Integer itemId) {
        ItemDTO itemDTO = itemService.getItem(itemId);
        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ItemDTO>> getItem() {
        List<ItemDTO> itemDTOList = itemService.getItems();
        return new ResponseEntity<>(itemDTOList, HttpStatus.OK);
    }

    @GetMapping("/calc/{id}")
    public ResponseEntity<Double> calculateTotal(@PathVariable("id") Integer itemId, @Valid @Min(value = 1) @RequestParam("qty") Integer qty){
        Double totVal = itemService.getTotalValue(itemId,qty);
        return new ResponseEntity<>(totVal, HttpStatus.OK);
    }

}
