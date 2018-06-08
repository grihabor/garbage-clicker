package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java9.util.stream.Collectors;
import java9.util.stream.IntStream;
import java9.util.stream.StreamSupport;

public abstract class Shop {
    Storage storage;

    ArrayList<? extends ShopItem> shopItemArray;

    int itemLayoutId;
    String[] shopItemAttributes;
    int[] shopItemAttrIds;

    Context context;

    abstract String getTag();

    Shop (int resourceId, Context context, Storage storage) {
        this.storage = storage;
        this.context = context;
        shopItemArray = getShopItemList(resourceId);
    }

    private ArrayList<? extends ShopItem> getShopItemList (int resourceId) {
        JSONLoader loader = new JSONLoader(context);
        JSONObject jsonObject = loader.parceJSONResource(resourceId);

        ArrayList<? extends ShopItem> shopItemAttrArray = null;

        if(jsonObject != null) {
            shopItemAttrArray = IntStream
                    .range(0, jsonObject.names().length())
                    .mapToObj(i -> createInstance(jsonObject, i))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            Log.e(getTag(), "Unable to load data from json: id=" + resourceId);
        }

        return shopItemAttrArray;
    }

    public ShopFragment getShopFragment (int shopItemLayoutId, String[] shopItemAttributes, int[] shopItemAttrIds) {
        this.itemLayoutId = shopItemLayoutId;
        this.shopItemAttributes = shopItemAttributes;
        this.shopItemAttrIds = shopItemAttrIds;

        ShopFragment ret = new ShopFragment();
        ret.setShop(this);
        return ret;
    }

    ArrayList<HashMap<String, Object>> getViewDataArray() {
        return StreamSupport
                .stream(shopItemArray)
                .map((item) -> item.getViewData(context))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    abstract public ShopItem createInstance(JSONObject object, int iterationIndex);
}
