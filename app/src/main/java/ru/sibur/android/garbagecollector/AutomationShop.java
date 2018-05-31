package ru.sibur.android.garbagecollector;

import android.content.Context;

import org.json.JSONObject;

public class AutomationShop extends Shop {
    @Override
    String getTag() {
        return "AUTOMATION_SHOP";
    }

    AutomationShop(int resourceId, Context context, Storage storage) {
        super(resourceId, context, storage);
    }

    @Override
    public ShopItem createInstance(JSONObject object, int iterationIndex) {
        return new Automata(object, storage, iterationIndex);
    }
}