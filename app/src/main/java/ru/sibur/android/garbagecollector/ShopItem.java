package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;

import static ru.sibur.android.garbagecollector.Constant.MONEY_DISPLAY_COEFFICIENT;

/**
 * Элемент списка в фрагменте магазина
 */ 

public class ShopItem {
    int price;
    String name;
    Storage storage;
    
    ShopItem(String nameIN, int priceIn, Storage storage) {
        price = priceIn;
        name = nameIN;
        this.storage = storage;
    }
    
    public HashMap<String, String> getViewData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Name", name);
        map.put("Price", "Стоимость : " + getPrice() * Constant.MONEY_DISPLAY_COEFFICIENT);
        return map;
    }

    void tryToBuy (Context context, Storage storage) {
        if (storage.getMoney() >= getPrice()) {
            storage.addMoney(-getPrice());

            buy(context);
        } else {
            Toast.makeText(context, "не хватает средств", Toast.LENGTH_SHORT).show();
        }
    }

    void buy (Context context) {
        Toast.makeText(context, "спасибо за покупку", Toast.LENGTH_SHORT).show();
    }

    int getPrice () {
        return price;
    }
}
