package ru.sibur.android.garbagecollector;

import android.content.Context;

/**
 * Класс автомата
 * 
 * Этот класс позволяет создавать новые виды автоматов
 */

public class Automata extends ShopItem {
    int automataNum;

    Automata(String nameIN, int priceIn, Storage storage, int automataNum) {
        super(nameIN, priceIn, storage);
        this.automataNum = automataNum;
    }

    @Override
    void buy (Context context) {
        super.buy(context);

        storage.incrementShopItemCount(Constant.AUTOMATA_COUNT_NAMES[automataNum]);
    }
}
