package ru.sibur.android.garbagecollector;

import android.content.Context;

/**
 * Базовый класс для улучшений
 */ 

public class Upgrade extends ShopItem {
    int upgradeNum;

    Upgrade(String nameIN, int priceIn, Storage storage, int upgradeNum) {
       super(nameIN, priceIn, storage);
       this.upgradeNum = upgradeNum;
    }

    @Override
    void buy (Context context) {
        super.buy(context);

        storage.incrementShopItemCount(Constant.UPGRADE_COUNT_NAMES[upgradeNum]);
    }
}
