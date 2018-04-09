package ru.sibur.android.garbagecollector;

import android.content.Context;

/**
 * Базовый класс для улучшений
 */ 

public class Upgrade extends ShopItem {
    int index;

    Upgrade(String name, int price, Storage storage, int upgradeIndex) {
       super(name, price, storage);
       this.index = upgradeIndex;
    }

    @Override
    void buy (Context context) {
        super.buy(context);

        storage.incrementShopItemCount(Constant.upgradeCountKey(index));
    }
}
