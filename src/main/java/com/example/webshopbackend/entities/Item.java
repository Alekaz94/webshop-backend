package com.example.webshopbackend.entities;

import com.example.webshopbackend.enums.ItemEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Item {

    @Id
    private UUID itemId;
    private String itemName;
    private double itemCost;
    private double itemQuantity;
    @Enumerated(EnumType.STRING)
    private ItemEnum itemEnum;
    private Boolean soldOut;

    public Item(String itemName, double itemCost, double itemQuantity, ItemEnum itemEnum, Boolean soldOut) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemQuantity = itemQuantity;
        this.itemEnum = itemEnum;
        this.soldOut = soldOut;
    }

    public Item() {

    }
}
