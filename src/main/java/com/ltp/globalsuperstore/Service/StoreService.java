package com.ltp.globalsuperstore.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;
import com.ltp.globalsuperstore.repository.StoreRepository;

public class StoreService {
    StoreRepository storeRepository = new StoreRepository();

    
    public int getIndexFromId(String id) {
        for (int i = 0; i < getItems().size(); i++) {
            if (getItems().get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }


    public List<Item> getItems() {
        return storeRepository.getItems();
    }

    public Item getItemById(String id){
        int index = getIndexFromId(id);
        return index == Constants.NOT_FOUND ? new Item() :getItems().get(index);
    }
    public void addItem(Item item){
        storeRepository.addItem(item);
    }
    public void updateItem(int index, Item item){
        storeRepository.updateItem(index, item);
    }


    public String submitForm(Item item){
        int index = getIndexFromId(item.getId());
        String status = Constants.SUCCESS_STATUS;
        if (index == Constants.NOT_FOUND) {
            addItem(item);
        } else if (within5Days(item.getDate(), getItems().get(index).getDate())) {
            updateItem(index, item);
        } else {
            status = Constants.FAILED_STATUS;
            return status;
        }
        return status;
    }

    

    public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }

}
