package com.example.webshopbackend.entities;

import com.example.webshopbackend.enums.ItemEnums;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    private UUID itemId;
    private String itemName;
    private double itemCost;
    private double itemQuantity;
    @Enumerated(EnumType.STRING)
    ItemEnums itemEnum;
    private Boolean soldOut;
    private String url;

}
