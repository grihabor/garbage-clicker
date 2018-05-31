package ru.sibur.android.garbagecollector;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static java.lang.Math.pow;

/**
 * Класс автомата
 *
 * Этот класс позволяет создавать новые виды автоматов
 */

public class Automata extends ShopItem {
    private int index;
    private int basePerformance;

    private final String TAG = "AUTOMATA";

    Automata(JSONObject attrs, Storage storage, int automataIndex) {
        super(attrs, storage);
        this.index = automataIndex;

        try {
            basePerformance = attrs.getInt("base_performance");
        } catch (JSONException e) {
            Log.e(TAG, "JSONException: " + e.getMessage());
        }
    }

    @Override
    int getPrice () {
        int count = getCount();

        int price = (int) (
                basePrice
                *pow(Constant.AUTOMATA_COST_INCREASE_MULTIPLIER,count)
                *pow(Constant.AUTOMATA_COST_DECREASE_MULTIPLIER, this.storage.getShopItemCount(Constant.upgradeCountKey(1)))
        );

        return price;
    }

    @Override
    String getCountKey() {
        return Constant.automataCountKey(index);
    }

    @Override
    public HashMap<String, Object> getViewData() {
        HashMap<String, Object> ret = super.getViewData();
        ret.put(Constant.SHOP_ITEM_PERFORMANCE_KEY, basePerformance);
        return ret;
    }
}
