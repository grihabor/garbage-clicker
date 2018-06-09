package ru.sibur.android.garbagecollector;

import android.content.Context;
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
    private BigInteger basePerformance;

    private final String TAG = "AUTOMATA";

    Automata(JSONObject jsonData, Storage storage, int automataIndex) {
        super(jsonData, storage, automataIndex);
    }

    @Override
    BigInteger getPrice () {
        String priceUpgradeCountKey = "cheap_automatas";
        int priceUpgradeCount = storage.getShopItemCount(priceUpgradeCountKey);
        double decreaseMultiplier = pow(Constant.AUTOMATA_COST_DECREASE_MULTIPLIER, priceUpgradeCount);
        double increaseMultiplier = pow(Constant.AUTOMATA_COST_INCREASE_MULTIPLIER, getCount());
        double multiplier = decreaseMultiplier * increaseMultiplier;

        return Constant.multiply(basePrice, multiplier);
    }

    @Override
    void setJsonAttrs(JSONObject jsonAttrs) throws JSONException {
        super.setJsonAttrs(jsonAttrs);
        basePerformance = new BigInteger (jsonAttrs.getString("base_performance"));
    }

    @Override
    public HashMap<String, Object> getViewData(Context context) {
        HashMap<String, Object> ret = super.getViewData(context);
        ret.put(Constant.SHOP_ITEM_PERFORMANCE_KEY, Constant.formatMoney(getUnaryPerformance()));
        return ret;
    }

    BigInteger getTotalPerformance() {
        BigInteger count = BigInteger.valueOf(getCount());
        return getUnaryPerformance().multiply(count);
    }

    BigInteger getUnaryPerformance() {
        String performanceUpgradeCountKey = "automata_performance_upgrade";
        int performanceUpgradeCount = storage.getShopItemCount(performanceUpgradeCountKey);
        double multiplier = pow (Constant.PERFORMANCE_INCREASE_MULTIPLIER, performanceUpgradeCount);
        return Constant.multiply(basePerformance, multiplier);
    }
}
