package ru.sibur.android.garbagecollector;

import android.content.Context;

import org.json.JSONObject;

public class UpgradeShop extends Shop {

    UpgradeShop(int resourceId, Context context, Storage storage) {
        super(resourceId, context, storage);
    }

    @Override
    public ShopItem apply(JSONObject jsonObject) {
        return new Upgrade(jsonObject, storage, iterationIndex);
    }
}
