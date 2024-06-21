package com.example.webshopbackend.controllers;

import com.example.webshopbackend.dto.ItemDTO;
import com.example.webshopbackend.entities.Item;
import com.example.webshopbackend.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{itemId}")
    public Item getSpecificItem(@PathVariable UUID itemId) {
        return itemService.getSpecificItem(itemId);
    }

    @PostMapping()
    public ResponseEntity<Item> createItem(@RequestBody ItemDTO itemDTO) {
        return itemService.createItem(itemDTO);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable UUID itemId, @RequestBody ItemDTO itemDTO) {
        return itemService.updateItem(itemId, itemDTO);
    }

    @DeleteMapping("/{itemId]")
    public ResponseEntity<ItemDTO> deleteItem(@PathVariable UUID itemId) {
        return itemService.deleteItem(itemId);
    }
}
