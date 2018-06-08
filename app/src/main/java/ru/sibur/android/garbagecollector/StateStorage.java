package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArrayMap;

import java.math.BigInteger;
import java.util.Date;


/**
 * Created by RedSnail on 23.03.2018.
 */

public class StateStorage extends Storage implements SharedPreferences.OnSharedPreferenceChangeListener {
    SharedPreferences sPref;
    ArrayMap<String, OnDBChangeListener> listenerMap = new ArrayMap<>();
    static Context context;

    StateStorage (Context context, String prefName) {
        sPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        this.context = context;
        // see details in onSharedPreferenceChanged method
        sPref.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    void addOnDBChangeListener (String key, OnDBChangeListener listener) {
        listenerMap.put(key, listener);
    }

    @Override
    int getIdByName(String name) {
        int id =  context.getResources().getIdentifier("ru.sibur.android.garbagecollector.dev.debug:drawable/"+name,"drawable", "ru.sibur.android.garbagecollector.dev.debug");
        return id;
    }


    synchronized BigInteger getBigInteger(String key, BigInteger defVal) {
        return new BigInteger(sPref
                .getString(key, defVal.toString(Constant.S_PREF_NUMERAL_SYSTEM)));
    }

    synchronized void putBigInteger (String key, BigInteger bigInteger) {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(key, bigInteger.toString(Constant.S_PREF_NUMERAL_SYSTEM));
        editor.apply();
    }

    @Override
    synchronized void addMoney(BigInteger amount) {
        BigInteger money = getMoney();
        BigInteger totalMoney = getTotalMoney();

        putBigInteger(Constant.MONEY_KEY, money.add(amount));
        putBigInteger(Constant.TOTAL_MONEY_EARNED_KEY, totalMoney.add(amount));
    }

    @Override
    boolean enoughMoney(BigInteger price) {
        return (getBigInteger(Constant.MONEY_KEY, BigInteger.ZERO)
                .compareTo(price) >= 0);
    }

    @Override
    BigInteger getMoney() {
        return getBigInteger(Constant.MONEY_KEY, BigInteger.ZERO);
    }

    @Override
    BigInteger getTotalMoney() {
        return getBigInteger(Constant.TOTAL_MONEY_EARNED_KEY, BigInteger.ZERO);
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

        BigInteger timeDifference = BigInteger.valueOf(currentTime - prevTime);
        BigInteger addedMoney = calculator.calculateMoney(timeDifference);
        addMoney(addedMoney);
    }

    synchronized void setMusicShouldBe(boolean shouldBe) {
        SharedPreferences.Editor editor = sPref.edit();

        editor.putBoolean(Constant.MUSIC_SHOULD_BE_KEY, shouldBe);
        editor.apply();
    }
    synchronized boolean getMusicShouldBe(){
        return sPref.getBoolean(Constant.MUSIC_SHOULD_BE_KEY,false);
    }
    synchronized void setSoundsShouldBe(boolean shouldBe) {
        SharedPreferences.Editor editor = sPref.edit();

        editor.putBoolean(Constant.SOUNDS_SHOULD_BE_KEY, shouldBe);
        editor.apply();
    }
    synchronized boolean getSoundsShouldBe(){
        return sPref.getBoolean(Constant.SOUNDS_SHOULD_BE_KEY,false);
    }

    /**
     * Implements `SharedPreferences` listener interface
     *
     * IMPORTANT: `SharedPreferences` object holds a weak ref to the registered listener,
     * so we implement this listener interface in `StateStorage` class and pass the reference
     * into the `registerOnSharedPreferenceChangeListener` method.
     * https://developer.android.com/reference/android/content/SharedPreferences.html#registerOnSharedPreferenceChangeListener(android.content.SharedPreferences.OnSharedPreferenceChangeListener)
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        for (ArrayMap.Entry entry : listenerMap.entrySet()) {
            if (entry.getKey().equals(key)) {
                ((OnDBChangeListener) entry.getValue()).onDBChange();
            }
        }
    }
}
