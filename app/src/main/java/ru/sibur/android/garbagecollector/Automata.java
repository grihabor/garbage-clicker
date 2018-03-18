package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 14.03.2018.
 */

public class Automata extends ShopItem {
    private int power;

    Automata(String nameIN, int priceIn, int Power)
    {
        super(nameIN, priceIn);
        this.power = Power;
    }

    @Override
    void buy (Context context) {
        super.buy(context);

        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);

        int CurrentPower = sharedPreferences.getInt(AutomataThread.POWER_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(AutomataThread.POWER_NAME, CurrentPower + power);
        editor.apply();
    }
}
