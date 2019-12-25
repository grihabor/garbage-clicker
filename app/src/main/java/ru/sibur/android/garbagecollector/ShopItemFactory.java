package ru.sibur.android.garbagecollector;

import org.json.JSONObject;

public interface ShopItemFactory <T extends ShopItem> {
    T factory(JSONObject jsonData, Storage storage, int iterationIndex);
}
