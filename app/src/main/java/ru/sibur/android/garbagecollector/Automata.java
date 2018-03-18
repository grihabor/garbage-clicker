package ru.sibur.android.garbagecollector;

import android.content.Context;

/**
 * Класс автомата
 * 
 * Этот класс позволяет создавать новые виды автоматов
 */

public class Automata extends ShopItem {

    Automata(String nameIN, int priceIn) {
        super(nameIN, priceIn);
    }

    @Override
    void buy (Context context) {
        super.buy(context);

        //пишет в sPref, что его купили
    }
}
