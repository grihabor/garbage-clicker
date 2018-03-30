package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Элемент списка в фрагменте магазина
 */ 

public abstract class ShopItem {
    int basePrice;
    String name;
    Storage storage;
    
    ShopItem(String name, int basePrice, Storage storage) {
        this.basePrice = basePrice;
        this.name = name;
        this.storage = storage;
    }
    
    public HashMap<String, String> getViewData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Name", name);
        map.put("Price", "Стоимость : " + Constant.formatMoney(getPrice()));
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

    private void buy(Context context) {
        Toast.makeText(context, "спасибо за покупку", Toast.LENGTH_SHORT).show();
        storage.incrementShopItemCount(getCountKey());
    }

    int getPrice () {
        return basePrice;
    }

    abstract String getCountKey ();

    int getCount () {
        return storage.getShopItemCount(getCountKey());
    }

    void setOnCountChangeListener (OnDBChangeListener listener) {
        storage.addOnDBChangeListener(getCountKey(), listener);
    }
}
