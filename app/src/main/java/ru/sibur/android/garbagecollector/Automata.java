package ru.sibur.android.garbagecollector;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.HashMap;

import static java.lang.Math.pow;

/**
 * Класс автомата
 *
 * Этот класс позволяет создавать новые виды автоматов
 */

public class Automata extends ShopItem {
    private int index;
    private BigInteger basePerformance;

    private final String TAG = "AUTOMATA";

    Automata(JSONObject attrs, Storage storage, int automataIndex) {
        super(attrs, storage);
        this.index = automataIndex;

        try {
            basePerformance = new BigInteger (attrs.getString("base_performance"));
        } catch (JSONException e) {
            Log.e(TAG, "JSONException: " + e.getMessage());
        }
    }

    @Override
    BigInteger getPrice () {
        String priceUpgradeCountKey = Constant.upgradeCountKey(1);
        int priceUpgradeCount = storage.getShopItemCount(priceUpgradeCountKey);
        double decreaseMultiplier = pow(Constant.AUTOMATA_COST_DECREASE_MULTIPLIER, priceUpgradeCount);
        double increaseMultiplier = pow(Constant.AUTOMATA_COST_INCREASE_MULTIPLIER, getCount());
        double multiplier = decreaseMultiplier * increaseMultiplier;

        return Constant.multiply(basePrice, multiplier);
    }

    @Override
    String getCountKey() {
        return Constant.automataCountKey(index);
    }

    @Override
    public HashMap<String, Object> getViewData() {
        HashMap<String, Object> ret = super.getViewData();
        ret.put(Constant.SHOP_ITEM_PERFORMANCE_KEY, Constant.formatMoney(getUnaryPerformance()));
        return ret;
    }

    BigInteger getTotalPerformance() {
        BigInteger count = BigInteger.valueOf(getCount());
        return getUnaryPerformance().multiply(count);
    }

    BigInteger getUnaryPerformance() {
        String performanceUpgradeCountKey = Constant.upgradeCountKey(5);
        int performanceUpgradeCount = storage.getShopItemCount(performanceUpgradeCountKey);
        double multiplier = pow (Constant.PERFORMANCE_INCREASE_MULTIPLIER, performanceUpgradeCount);
        return Constant.multiply(basePerformance, multiplier);
    }
}
