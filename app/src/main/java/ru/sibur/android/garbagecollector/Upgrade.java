package ru.sibur.android.garbagecollector;

import org.json.JSONObject;

import java.math.BigDecimal;
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

        BigDecimal increaseMultiplier = BigDecimal.valueOf(Constant.UPGRADE_COST_INCREASE_MULTIPLIER);
        increaseMultiplier = increaseMultiplier.pow(getCount());

        BigDecimal decreaseMultiplier = BigDecimal.valueOf(Constant.UPGRADE_COST_DECREASE_MULTIPLIER);
        decreaseMultiplier = decreaseMultiplier.pow(priceUpgradeCount);

        BigDecimal multiplier = increaseMultiplier.multiply(decreaseMultiplier);
        return Constant.multiply(basePrice, multiplier);
    }
}
