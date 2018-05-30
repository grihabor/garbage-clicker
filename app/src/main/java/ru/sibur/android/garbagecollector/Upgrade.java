package ru.sibur.android.garbagecollector;

import static java.lang.Math.pow;

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
    int getPrice () {
        int count = getCount();
        int price = (int)(
                basePrice
                *pow(Constant.UPGRADE_COST_INCREASE_MULTIPLIER,count)
                *pow(Constant.UPGRADE_COST_DECREASE_MULTIPLIER, this.storage.getShopItemCount(Constant.upgradeCountKey(2)))
        );


        return price;
    }
}
