package ru.sibur.android.garbagecollector;

import org.json.JSONObject;

import static java.lang.Math.pow;

/**
 * Базовый класс для улучшений
 */ 

public class Upgrade extends ShopItem {
    private int index;

    Upgrade(JSONObject attrs, Storage storage, int upgradeIndex) {
        super(attrs, storage);
        this.index = upgradeIndex;
    }

    @Override
    String getCountKey() {
        return Constant.upgradeCountKey(index);
    }
    @Override
    long getPrice () {
        int count = getCount();
        long  price = (int)(
                basePrice
                *pow(Constant.UPGRADE_COST_INCREASE_MULTIPLIER,count)
                *pow(Constant.UPGRADE_COST_DECREASE_MULTIPLIER, this.storage.getShopItemCount(Constant.upgradeCountKey(2)))
        );


        return price;
    }
}
