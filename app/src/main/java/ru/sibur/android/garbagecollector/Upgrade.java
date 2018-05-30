package ru.sibur.android.garbagecollector;

import static java.lang.Math.pow;

/**
 * Базовый класс для улучшений
 */ 

public class Upgrade extends ShopItem {
    private int index;
    Upgrade(String name, int basePrice, Storage storage, int upgradeIndex) {
       super(name, basePrice, storage);
       this.index = upgradeIndex;
    }

    @Override
    String getCountKey() {
        return Constant.upgradeCountKey(index);
    }

    @Override
    int getPrice () {
        int count = getCount();
        int price = (int)(
                basePrice
                *pow(1.15,count)
                *pow(0.85, this.storage.getShopItemCount(Constant.upgradeCountKey(2)))
        );


        return price;
    }
}
