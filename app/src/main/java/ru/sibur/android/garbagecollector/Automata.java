package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.widget.ImageView;

/**
 * Класс автомата
 * 
 * Этот класс позволяет создавать новые виды автоматов
 */

public class Automata extends ShopItem {
    int index;


    Automata(String nameIN, int priceIn, int img, Storage storage, int automataIndex) {
        super(nameIN, priceIn, storage);
        this.index = automataIndex;
    }

    @Override
    void buy (Context context) {
        super.buy(context);

        storage.incrementShopItemCount(Constant.automataCountKey(index));
    }
}
