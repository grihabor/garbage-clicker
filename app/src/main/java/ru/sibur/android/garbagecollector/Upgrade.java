package ru.sibur.android.garbagecollector;

/**
 * Базовый класс для улучшений
 */ 

public class Upgrade extends ShopItem {
    private int index;

    Upgrade(String name, int initialPrice, Storage storage, int upgradeIndex) {
       super(name, initialPrice, storage);
       this.index = upgradeIndex;
    }

    @Override
    String getCountKey() {
        return  Constant.upgradeCountKey(index);
    }

    @Override
    int getPrice () {
        int count = storage.getShopItemCount(getCountKey());
        int outPrice = initialPrice;

        for (int i = 0; i < count; i++) {
            outPrice *= 1.30;
        }

        return outPrice;
    }
}
