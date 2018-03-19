package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Элемент списка в фрагменте магазина
 */ 

public class ShopItem {
    int price;
    String name;
    
    ShopItem(String nameIN, int priceIn) {
        price = priceIn;
        name = nameIN;
    }
    
    public HashMap<String, String> getViewData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Name", name);
        map.put("Price", "Стоимость : " + getPrice() * MainActivity.MONEY_DISPLAY_COEFFICIENT);
        return map;
    }

    void tryToBuy (Context context, OnMoneyUpdateListener listener) {
        SharedPreferences preferences = context.getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
        int money = preferences.getInt(MainActivity.MONEY_KEY, 0);
        if (money >= getPrice()) {
            buy(context);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(MainActivity.MONEY_KEY, money - getPrice());
            editor.apply();

            listener.OnMoneyUpdate();
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
