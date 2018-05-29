package ru.sibur.android.garbagecollector;

import java.util.HashMap;

/**
 * Базовый класс для улучшений
 */ 

public class Upgrade extends ShopItem {
    private int index;

    Upgrade(String name, int basePrice, int iconId, Storage storage, int upgradeIndex) {
       super(name, basePrice, iconId, storage);
       this.index = upgradeIndex;
    }

    @Override
    String getCountKey() {
        return Constant.upgradeCountKey(index);
    }

    @Override
    public HashMap<String, Object> getViewData() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("Name", name);
        map.put("Price",getPrice());
        map.put("Img", R.drawable.item_icon);
        map.put("Qty", getCount());
        return (map);
    }

    @Override
    int getPrice () {
        int count = getCount();
        int price = basePrice;

        for (int i = 0; i < count; i++) {
            price *= 1.30;
        }

        return price;
    }
}
