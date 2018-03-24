package ru.sibur.android.garbagecollector;

/**
 * Created by RedSnail on 23.03.2018.
 */

public abstract class Storage {
    abstract void addMoney(int amount);
    abstract int getMoney();

    abstract void incrementShopItemCount (String itemName);
    abstract int getShopItemCount (String itemName);

    abstract void updateAutomataThreadActionTime(AutomataMoneyCalculator calculator);

    abstract void addOnDBChangeListener (String key, OnDBChangeListener listener);

}