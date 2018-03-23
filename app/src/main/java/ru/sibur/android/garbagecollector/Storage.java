package ru.sibur.android.garbagecollector;

/**
 * Created by Олег on 23.03.2018.
 */

public abstract class Storage {
    abstract void addMoney(int amount);
    abstract int getMoney();

    abstract void addShopItemPurchase (String itemName);
    abstract int getPurchases (String itemName);

    abstract long updateAutomataThreadActionTime();
}