package com.example.webshopbackend.services;

import com.example.webshopbackend.dto.ItemDTO;
import com.example.webshopbackend.entities.Item;
import com.example.webshopbackend.repositories.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getSpecificItem(UUID itemId) {
        Optional<Item> foundItem = itemRepository.findById(itemId);

        if(foundItem.isPresent()) {
            return foundItem.get();
        } else {
            throw new NullPointerException(String.format("Could not find item with ID: %s!", itemId));
        }
    }

    public ResponseEntity<Item> createItem(ItemDTO itemDTO) {
        Item item = new Item();

        item.setItemId(UUID.randomUUID());
        item.setItemName(itemDTO.itemName());
        item.setItemCost(itemDTO.itemCost());
        item.setItemQuantity(itemDTO.itemQuantity());
        item.setItemEnum(itemDTO.itemEnum());
        item.setSoldOut(itemDTO.soldOut());

        itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<ItemDTO> updateItem(UUID itemId, ItemDTO itemDTO) {

        if(itemId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(itemDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Item> itemToUpdate = itemRepository.findById(itemId);

        if(itemToUpdate.isPresent()) {
            itemToUpdate.get().setItemName(itemDTO.itemName());
            itemToUpdate.get().setItemCost(itemDTO.itemCost());
            itemToUpdate.get().setItemQuantity(itemDTO.itemQuantity());
            itemToUpdate.get().setItemEnum(itemDTO.itemEnum());

            itemRepository.save(itemToUpdate.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new NullPointerException(String.format("Could not find item with ID: %s!", itemId));
        }

    }

    public ResponseEntity<ItemDTO> deleteItem(UUID itemId) {

        if(itemId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Item> foundItem = itemRepository.findById(itemId);

        if(foundItem.isPresent()) {
            itemRepository.deleteById(itemId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new NullPointerException(String.format("Could not find item with ID: %s!", itemId));
        }
    }
}
