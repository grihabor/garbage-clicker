package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;

public class ShopItem {
    float price;
    String name;
    ShopItem(String nameIN, float priceIn){
        price = priceIn;
        name = nameIN;
    }
    public HashMap<String, String> getViewData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("Name", name);
        map.put("Price", "Стоимость : " + getPrice());
        return map;
    }

    void buy (Context context, OnMoneyUpdateListener listener) {
        SharedPreferences preferences = context.getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
        int MoneyNow = preferences.getInt(MainActivity.MONEY_KEY, 0);
        if (MoneyNow >= getPrice()) {
            apply(context);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(MainActivity.MONEY_KEY, MoneyNow - getPrice());
            editor.apply();

            listener.OnMoneyUpdate();
        }
        else {
            Toast.makeText(context, "не хватает средств", Toast.LENGTH_SHORT).show();
        }
    }

    void apply (Context context) {
        Toast.makeText(context, "спасибо за покупку", Toast.LENGTH_SHORT).show();
    }

    int getPrice () {
        return Math.round(price);
    }
}
