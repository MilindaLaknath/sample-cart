package com.milinda.assessment.controller;

import com.milinda.assessment.dto.ItemDTO;
import com.milinda.assessment.service.ItemService;
import com.milinda.assessment.util.ErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDTO> saveItem(@RequestBody ItemDTO itemDTO) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(itemService.addItem(itemDTO), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.toString());
            responseEntity = new ResponseEntity(new ErrorMsg("Server Error",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable("id") Integer itemId) {
        ResponseEntity responseEntity;
        try {
            itemService.deleteItem(itemId);
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            log.error(e.toString());
            responseEntity = new ResponseEntity(new ErrorMsg("Server Error",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable("id") Integer itemId) {
        ResponseEntity responseEntity;
        try {
            ItemDTO itemDTO = itemService.getItem(itemId);
            responseEntity = new ResponseEntity<>(itemDTO, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.toString());
            responseEntity = new ResponseEntity(new ErrorMsg("Server Error",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping()
    public ResponseEntity<List<ItemDTO>> getItem() {
        ResponseEntity responseEntity;
        try {
            List<ItemDTO> itemDTOList = itemService.getItems();
            responseEntity = new ResponseEntity<>(itemDTOList, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.toString());
            responseEntity = new ResponseEntity(new ErrorMsg("Server Error",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/calc/{id}")
    public ResponseEntity<Double> calculateTotal(@PathVariable("id") Integer itemId, @Valid @Min(value = 1) @RequestParam("qty") Integer qty){
        ResponseEntity responseEntity;
        try {
            Double totVal = itemService.getTotalValue(itemId,qty);
            responseEntity = new ResponseEntity<>(totVal, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.toString());
            responseEntity = new ResponseEntity(new ErrorMsg("Server Error",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
