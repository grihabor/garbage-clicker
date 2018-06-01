package ru.sibur.android.garbagecollector;

import org.json.JSONObject;

import java.math.BigInteger;

import static java.lang.Math.pow;

/**
 * Базовый класс для улучшений
 */ 

public class Upgrade extends ShopItem {
    private int index;

    Upgrade(JSONObject attrs, Storage storage, int upgradeIndex) {
        super(attrs, storage);
        this.index = upgradeIndex;
    }

    @Override
    String getCountKey() {
        return Constant.upgradeCountKey(index);
    }
    @Override
    BigInteger getPrice () {
        double multiplier = pow(Constant.UPGRADE_COST_INCREASE_MULTIPLIER, getCount()) *
                pow(Constant.UPGRADE_COST_INCREASE_MULTIPLIER, storage.getShopItemCount(Constant.upgradeCountKey(2)));

        return Constant.multiply(basePrice, multiplier);
    }
}
