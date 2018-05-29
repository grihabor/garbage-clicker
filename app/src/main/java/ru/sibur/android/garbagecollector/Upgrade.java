package ru.sibur.android.garbagecollector;

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
        int price = basePrice;

        for (int i = 0; i < count; i++) {
            price *= 1.30;
        }

        return price;
    }
}
