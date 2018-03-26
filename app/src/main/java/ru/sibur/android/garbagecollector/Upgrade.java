package ru.sibur.android.garbagecollector;

import android.content.Context;

/**
 * Базовый класс для улучшений
 */ 

public class Upgrade extends ShopItem {
    int index;

    Upgrade(String nameIN, int priceIn, Storage storage, int upgradeNum) {
       super(nameIN, priceIn, storage);
       this.index = upgradeNum;
    }

    @Override
    void buy (Context context) {
        super.buy(context);

        storage.incrementShopItemCount(Constant.upgradeCountKey(index));
    }
}
