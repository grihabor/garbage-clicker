package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

/**
 * Created by Олег on 23.03.2018.
 */

public class StateStorage extends Storage {
    SharedPreferences sPref;
    SharedPreferences.Editor editor;

    StateStorage (Context context, String prefName) {
        sPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        editor = sPref.edit();
    }


    synchronized void addMoney(int amount) {
        int newMoney = amount + getMoney();
        editor.putInt(MainActivity.MONEY_KEY, newMoney);
        editor.apply();
    }

    int getMoney() {
        return sPref.getInt(MainActivity.MONEY_KEY, 0);
    }

    void addShopItemPurchase(String itemName) {
        int newItemPurchaseNum = 1 + sPref.getInt(itemName, 0);
        editor.putInt(MainActivity.MONEY_KEY, newItemPurchaseNum);
        editor.apply();
    }

    int getPurchases(String itemName) {
        return sPref.getInt(itemName, 0);
    }

    long updateAutomataThreadActionTime() {
        long prevTime = sPref.getLong(AutomataThread.LAST_UPDATE_NAME, (new Date()).getTime());
        long currentTime = (new Date()).getTime();
        editor.putLong(AutomataThread.LAST_UPDATE_NAME, currentTime);
        return currentTime - prevTime;
    }
}
