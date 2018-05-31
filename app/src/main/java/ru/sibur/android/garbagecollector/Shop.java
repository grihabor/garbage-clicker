package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java9.util.function.Function;
import java9.util.stream.Collectors;
import java9.util.stream.IntStream;
import java9.util.stream.StreamSupport;

public abstract class Shop implements Function<JSONObject, ShopItem> {
    Storage storage;

    int iterationIndex;
    ArrayList<? extends ShopItem> shopItemArray;

    int listViewId;

    int itemLayoutId;
    String[] shopItemAttributes;
    int[] shopItemAttrIds;
    boolean readyToAttach;

    Context context;

    final String TAG = "SHOP";

    Shop (int resourceId, Context context, Storage storage) {
        this.storage = storage;
        iterationIndex = 0;
        this.context = context;
        shopItemArray = getShopItemList(resourceId);
        listViewId = 0;
        readyToAttach = false;
    }

    private ArrayList<? extends ShopItem> getShopItemList (int resourceId) {
        JSONLoader loader = new JSONLoader(context);
        JSONArray jsonarray = loader.parceJSONResource(resourceId);

        ArrayList<? extends ShopItem> shopItemAttrArray = null;

        if(jsonarray != null) {
            shopItemAttrArray = IntStream.range(0, jsonarray.length()).mapToObj(i -> {
                iterationIndex = i;
                ShopItem ret = null;
                try {
                    ret = this.apply ((JSONObject) jsonarray.get(i));
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException: " + e.getMessage());
                }

                return ret;
            }).collect(Collectors.toCollection(ArrayList::new));


        } else {
            Log.e(TAG, "Unable to load data from automatas.json");
        }

        return shopItemAttrArray;
    }

    public void setDisplayData (int listViewId, int shopItemLayoutId, String[] shopItemAttributes, int[] shopItemAttrIds) {
        this.listViewId = listViewId;
        this.itemLayoutId = shopItemLayoutId;
        this.shopItemAttributes = shopItemAttributes;
        this.shopItemAttrIds = shopItemAttrIds;
        this.readyToAttach = true;
    }

    ArrayList<HashMap<String, Object>> getViewDataArray() {
        return StreamSupport
                .stream(shopItemArray)
                .map(ShopItem::getViewData)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
