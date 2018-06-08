package ru.sibur.android.garbagecollector;

import org.json.JSONObject;

import java.math.BigInteger;

import static java.lang.Math.pow;

/**
 * Базовый класс для улучшений
 */ 

public class Upgrade extends ShopItem {

    Upgrade(JSONObject jsonData, Storage storage, int iterationIndex) {
        super(jsonData, storage, iterationIndex);
    }

    @Override
    BigInteger getPrice () {
        String priceUpgradeCountKey = "cheap_upgrades";
        int priceUpgradeCount = storage.getShopItemCount(priceUpgradeCountKey);
        double increaseMultiplier = pow(Constant.UPGRADE_COST_INCREASE_MULTIPLIER, getCount());
        double decreaseMultiplier = pow(Constant.UPGRADE_COST_DECREASE_MULTIPLIER, priceUpgradeCount);
        double multiplier = increaseMultiplier*decreaseMultiplier;
        return Constant.multiply(basePrice, multiplier);
    }
}
