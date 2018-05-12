package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArrayMap;

import java.util.Date;


/**
 * Created by RedSnail on 23.03.2018.
 */

public class StateStorage extends Storage implements SharedPreferences.OnSharedPreferenceChangeListener {
    SharedPreferences sPref;
    ArrayMap<String, OnDBChangeListener> listenerMap = new ArrayMap<>();

    StateStorage (Context context, String prefName) {
        sPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        sPref.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    void addOnDBChangeListener (String key, OnDBChangeListener listener) {
        listenerMap.put(key, listener);
    }

    @Override
    synchronized void addMoney(int amount) {
        SharedPreferences.Editor editor = sPref.edit();

        int newMoney = amount + getMoney();
        editor.putInt(Constant.MONEY_KEY, newMoney);
        editor.apply();
    }

    @Override
    int getMoney() {
        return sPref.getInt(Constant.MONEY_KEY, 0);
    }

    @Override
    synchronized void incrementShopItemCount(String itemCountKey) {
        SharedPreferences.Editor editor = sPref.edit();

        int newItemPurchaseNum = 1 + getShopItemCount(itemCountKey);
        editor.putInt(itemCountKey, newItemPurchaseNum);
        editor.apply();
    }

    @Override
    int getShopItemCount(String itemCountKey) {
        return sPref.getInt(itemCountKey, 0);
    }

    @Override
    synchronized void updateAutomataThreadActionTime(AutomataMoneyCalculator calculator) {
        SharedPreferences.Editor editor = sPref.edit();

        long prevTime = sPref.getLong(Constant.LAST_UPDATE_NAME, (new Date()).getTime());
        long currentTime = (new Date()).getTime();
        editor.putLong(Constant.LAST_UPDATE_NAME, currentTime);
        editor.apply();
        addMoney(calculator.calculateMoney(currentTime - prevTime));
    }

    /*
    * ВАЖНО: Объект хранит слабый референс на объект listener,
    * поэтому мы имплементим этот метод в классе StateStorage и передаем ссылку на него
    * https://developer.android.com/reference/android/content/SharedPreferences.html#registerOnSharedPreferenceChangeListener(android.content.SharedPreferences.OnSharedPreferenceChangeListener)
    * */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        for (ArrayMap.Entry entry : listenerMap.entrySet()) {
            if (entry.getKey().equals(key)) {
                ((OnDBChangeListener) entry.getValue()).onDBChange();
            }
        }
    }
}
