package ru.sibur.android.garbagecollector;

/**
 * Created by Олег on 23.03.2018.
 */

public abstract class Storage {
    //деньги могут изменяться из обоих потоков, поэтому это надо делать синхронно
    synchronized void addMoney(int amount) {
        addMoneyImpl(amount);
    }

    //abstract и synchronized недопутимое сочетание
    abstract void addMoneyImpl(int amount);
    abstract int getMoney();

    abstract void addShopItemPurchase (String itemName);
    abstract int getPurchases (String itemName);

    abstract long updateAutomataThreadActionTime();
}