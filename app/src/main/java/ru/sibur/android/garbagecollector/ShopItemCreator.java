package ru.sibur.android.garbagecollector;

import org.json.JSONObject;

public interface ShopItemCreator {
    ShopItem createInstance (JSONObject object, int iterationIndex);
}
