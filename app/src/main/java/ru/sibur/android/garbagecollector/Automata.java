package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
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

        BigDecimal decreaseMultiplier = BigDecimal.valueOf(Constant.AUTOMATA_COST_DECREASE_MULTIPLIER);
        decreaseMultiplier = decreaseMultiplier.pow(priceUpgradeCount);

        BigDecimal increaseMultiplier = BigDecimal.valueOf(Constant.PERFORMANCE_INCREASE_MULTIPLIER);
        increaseMultiplier = increaseMultiplier.pow(getCount());

        BigDecimal multiplier = decreaseMultiplier.multiply(increaseMultiplier);

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
        String performanceUpgradeCountKey = "automatas_performance_upgrade";
        int performanceUpgradeCount = storage.getShopItemCount(performanceUpgradeCountKey);

        BigDecimal multiplier = BigDecimal.valueOf(Constant.PERFORMANCE_INCREASE_MULTIPLIER);
        multiplier = multiplier.pow(performanceUpgradeCount);

        return Constant.multiply(basePerformance, multiplier);
    }
}
