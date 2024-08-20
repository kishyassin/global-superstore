package com.ltp.globalsuperstore.repository;

import java.util.ArrayList;
import java.util.List;

import com.ltp.globalsuperstore.Item;

public class StoreRepository {
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void updateItem(int index, Item item) {
        items.set(index, item);
    }

}
