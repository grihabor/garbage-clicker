package ru.sibur.android.garbagecollector;

import android.content.Context;

import org.json.JSONObject;

public class UpgradeShop extends Shop {
    @Override
    String getTag() {
        return "UPGRADE_SHOP";
    }

    UpgradeShop(int resourceId, Context context, Storage storage) {
        super(resourceId, context, storage);
    }

    @Override
    public ShopItem createInstance(JSONObject object, int iterationIndex) {
        return new Upgrade(object, storage, iterationIndex);
    }
}
