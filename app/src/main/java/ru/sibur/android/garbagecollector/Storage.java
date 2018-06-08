package ru.sibur.android.garbagecollector;

import java.math.BigInteger;

/**
 * Created by RedSnail on 23.03.2018.
 */

public abstract class Storage {
    abstract void addMoney(BigInteger amount);
    abstract boolean enoughMoney (BigInteger price);
    abstract BigInteger getMoney();
    abstract BigInteger getTotalMoney();
    abstract void incrementShopItemCount (String itemCountKey);
    abstract int getShopItemCount (String itemCountKey);
    abstract void setMusicShouldBe(boolean shouldBe);
    abstract boolean getMusicShouldBe();
    abstract void setSoundsShouldBe(boolean shouldBe);
    abstract boolean getSoundsShouldBe();

    abstract void updateAutomataThreadActionTime(AutomataMoneyCalculator calculator);

    abstract void addOnDBChangeListener (String key, OnDBChangeListener listener);
    abstract int getIdByName(String name);

}
