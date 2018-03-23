package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArrayMap;

import java.util.Date;

/**
 * Created by Олег on 23.03.2018.
 */

public class StateStorage extends Storage {
    SharedPreferences sPref;
    SharedPreferences.Editor editor;
    ArrayMap<String, OnDBChangeListener> listenerMap = new ArrayMap<>();

    StateStorage (Context context, String prefName) {
        sPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        sPref.registerOnSharedPreferenceChangeListener((sharedPreferences, key) -> {
            for (ArrayMap.Entry entry : listenerMap.entrySet()) {
                if (entry.getKey().equals(key)) {
                    ((OnDBChangeListener) entry.getValue()).OnDBChange();
                }
            }
        });
        editor = sPref.edit();
    }

    void setOnDBChangeListener (String key, OnDBChangeListener listener) {
        editor.apply();
        listenerMap.put(key, listener);
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

    synchronized void addShopItemCount(String itemName) {
        int newItemPurchaseNum = 1 + sPref.getInt(itemName, 0);
        editor.putInt(MainActivity.MONEY_KEY, newItemPurchaseNum);
        editor.apply();
    }

    int getShopItemCount(String itemName) {
        return sPref.getInt(itemName, 0);
    }

    synchronized void updateAutomataThreadActionTime(AutomataMoneyCalculator calculator) {
        long prevTime = sPref.getLong(AutomataThread.LAST_UPDATE_NAME, (new Date()).getTime());
        long currentTime = (new Date()).getTime();
        editor.putLong(AutomataThread.LAST_UPDATE_NAME, currentTime);
        editor.apply();
        addMoney(calculator.calculateMoney(currentTime - prevTime));
    }
}
