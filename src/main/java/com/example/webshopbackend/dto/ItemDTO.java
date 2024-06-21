package com.example.webshopbackend.dto;

import com.example.webshopbackend.enums.ItemEnum;

public record ItemDTO(String itemName, double itemCost, double itemQuantity, ItemEnum itemEnum, Boolean soldOut) {
}
