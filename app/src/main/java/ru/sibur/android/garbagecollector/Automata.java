package ru.sibur.android.garbagecollector;

import android.content.Context;

/**
 * Класс автомата
 * 
 * Этот класс позволяет создавать новые виды автоматов
 */

public class Automata extends ShopItem {
    int index;

    Automata(String name, int price, Storage storage, int automataIndex) {
        super(name, price, storage);
        this.index = automataIndex;
    }

    @Override
    void buy (Context context) {
        super.buy(context);

        storage.incrementShopItemCount(Constant.automataCountKey(index));
    }
}
