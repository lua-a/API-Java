package com.api.servicecontrol.controllers;

import com.api.servicecontrol.dtos.ItemDto;
import com.api.servicecontrol.models.ItemModel;
import com.api.servicecontrol.services.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/items")
public class ItemController {
    final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Object> saveItem(@RequestBody @Valid ItemDto itemDto) {
        var itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDto, itemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemModel));
    }

    @GetMapping
    public ResponseEntity<List<ItemModel>> getAllItem(){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneItem(@PathVariable(value = "id") UUID id){
        Optional<ItemModel> itemModelOptional = itemService.findById(id);
        if (!itemModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(itemModelOptional.get());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable(value = "id") UUID id){
        Optional<ItemModel> itemModelOptional = itemService.findById(id);
        if (!itemModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado.");
        }
        itemService.delete(itemModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ItemDto itemDto){
        Optional<ItemModel> itemModelOptional = itemService.findById(id);
        if (!itemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado.");
        }
        var itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDto, itemModel);
        itemModel.setId(itemModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(itemService.save(itemModel));
    }
}


