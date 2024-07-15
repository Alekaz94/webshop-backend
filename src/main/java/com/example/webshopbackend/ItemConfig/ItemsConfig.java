package com.example.webshopbackend.ItemConfig;

import com.example.webshopbackend.entities.Item;
import com.example.webshopbackend.enums.ItemEnums;
import com.example.webshopbackend.repositories.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class ItemsConfig {

    @Bean
    CommandLineRunner commandLineRunner(ItemRepository itemRepository) {
        return args -> {
            List<Item> items = new ArrayList<>();

            String[] itemNameList = {
                    "Jacket",
                    "Pants",
                    "Shorts",
                    "Socks",
                    "Underwear",
                    "T-shirt",
                    "Sneakers",
                    "Boots",
                    "Shirt",
                    "Slippers",
                    "Swimwear"
            };

            ItemEnums[] itemEnumsList = {
                    ItemEnums.JACKET,
                    ItemEnums.PANTS,
                    ItemEnums.SHORTS,
                    ItemEnums.SOCKS,
                    ItemEnums.UNDERWEAR,
                    ItemEnums.T_SHIRTS,
                    ItemEnums.SNEAKERS,
                    ItemEnums.BOOTS,
                    ItemEnums.SHIRT,
                    ItemEnums.SLIPPERS,
                    ItemEnums.SWIMWEAR
            };

            double[] itemCostList = {
                    20,
                    20,
                    10,
                    5,
                    7,
                    10,
                    50,
                    75,
                    50,
                    10,
                    15
            };

            for(int i = 0; i < itemNameList.length; i++) {
                UUID id = UUID.randomUUID();
                String itemName = itemNameList[i];
                double itemCost = itemCostList[i];
                double itemQuantity = 2000;
                ItemEnums itemnum = itemEnumsList[i];
                Boolean soldOut = false;
                String url = "";

                Item item = new Item(id, itemName, itemCost, itemQuantity, itemnum, soldOut, url);

                items.add(item);
            }

            itemRepository.saveAll(items);
        };
    }
}
