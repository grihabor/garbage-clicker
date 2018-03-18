package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 14.03.2018.
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
