package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java9.util.stream.Collectors;
import java9.util.stream.IntStream;
import java9.util.stream.StreamSupport;

public class Shop <ShopItemType extends ShopItem> {
    private static String TAG = "Shop";
    Storage storage;
    ShopItemFactory<ShopItemType> factory;

    ArrayList<ShopItemType> shopItemArray;

    int itemLayoutId;
    String[] shopItemAttributes;
    int[] shopItemAttrIds;

    Context context;


    Shop (int resourceId, Context context, ShopItemFactory<ShopItemType> factory) {
        this.storage = new StateStorage(context);
        this.factory = factory;
        this.context = context;
        shopItemArray = getShopItemList(resourceId);
    }

    private ArrayList<ShopItemType> getShopItemList (int resourceId) {
        JSONLoader loader = new JSONLoader(context);
        JSONObject jsonObject = loader.parceJSONResource(resourceId);

        ArrayList<ShopItemType> shopItemAttrArray = null;

        if(jsonObject != null) {
            shopItemAttrArray = IntStream
                    .range(0, jsonObject.names().length())
                    .mapToObj(i -> createInstance(jsonObject, i))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            Log.e(TAG, "Unable to load data from json: id=" + resourceId);
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

    public ShopItemType createInstance(JSONObject object, int iterationIndex) {
        return factory.factory(object, storage, iterationIndex);
    }
}

