package com.example.webshopbackend.services;

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

    public ResponseEntity<Item> createItem(Item item) {
        Item itemToCreate = new Item();

        itemToCreate.setItemId(UUID.randomUUID());
        itemToCreate.setItemName(item.getItemName());
        itemToCreate.setItemCost(item.getItemCost());
        itemToCreate.setItemQuantity(item.getItemQuantity());
        itemToCreate.setItemEnum(item.getItemEnum());
        itemToCreate.setSoldOut(item.getSoldOut());

        itemRepository.save(itemToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Item> updateItem(UUID itemId, Item item) {

        if(itemId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(item == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Item> itemToUpdate = itemRepository.findById(itemId);

        if(itemToUpdate.isPresent()) {
            itemToUpdate.get().setItemName(item.getItemName());
            itemToUpdate.get().setItemCost(item.getItemCost());
            itemToUpdate.get().setItemQuantity(item.getItemQuantity());
            itemToUpdate.get().setItemEnum(item.getItemEnum());

            itemRepository.save(itemToUpdate.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new NullPointerException(String.format("Could not find item with ID: %s!", itemId));
        }

    }

    public ResponseEntity<Item> deleteItem(UUID itemId) {

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
