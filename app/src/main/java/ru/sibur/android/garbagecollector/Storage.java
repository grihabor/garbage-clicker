package ru.sibur.android.garbagecollector;

/**
 * Created by RedSnail on 23.03.2018.
 */

public abstract class Storage {
    abstract void addMoney(int amount);
    abstract int getMoney();

    abstract void incrementShopItemCount (String itemCountKey);
    abstract int getShopItemCount (String itemCountKey);

    abstract void updateAutomataThreadActionTime(AutomataMoneyCalculator calculator);

    abstract void addOnDBChangeListener (String key, OnDBChangeListener listener);

}
