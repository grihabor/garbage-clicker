package ru.sibur.android.garbagecollector;

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
        map.put("Price", "Стоимость : "+String.valueOf(price));
        return map;
    }
}
