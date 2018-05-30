package ru.sibur.android.garbagecollector;

import static java.lang.Math.pow;

/**
 * Класс автомата
 * 
 * Этот класс позволяет создавать новые виды автоматов
 */

public class Automata extends ShopItem {
    private int index;

    Automata(String name, int basePrice, Storage storage, int automataIndex) {
        super(name, basePrice, storage);
        this.index = automataIndex;
    }

    @Override
    int getPrice () {
        int count = getCount();
        int price = (int) (basePrice
                * pow(1.15,count)
                *pow(0.85, this.storage
                .getShopItemCount(Constant.upgradeCountKey(1))));
        return price;
    }

    @Override
    String getCountKey() {
        return Constant.automataCountKey(index);
    }
}
