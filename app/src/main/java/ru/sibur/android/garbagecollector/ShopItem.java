package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Элемент списка в фрагменте магазина
 */ 

public abstract class ShopItem {
    BigInteger basePrice;
    String name;
    String iconName;
    Storage storage;
    String countKey;

    private final String TAG = "SHOP_ITEM";

    ShopItem(JSONObject jsonData, Storage storage, int iterationIndex) {
        this.storage = storage;

        try {
            String name = jsonData.names().get(iterationIndex).toString();
            iconName = name;
            countKey = name;
            JSONObject attributes = jsonData.getJSONObject(name);
            setJsonAttrs(attributes);
        } catch (JSONException e) {
            Log.e(TAG, "JSONException: " + e.getMessage());
        }
    }
    
    public HashMap<String, Object> getViewData(Context context) {
        String packageName = context.getPackageName();
        String fullIconName = Constant.resFullName(iconName, packageName);

        Resources res = context.getResources();
        int iconId = res.getIdentifier(fullIconName, "drawable", fullIconName);

        HashMap<String,Object> map = new HashMap<>();
        map.put(Constant.SHOP_ITEM_NAME_KEY, name);
        map.put(Constant.SHOP_ITEM_PRICE_KEY, Constant.formatMoney(getPrice()));
        map.put(Constant.SHOP_ITEM_ICON_ID_KEY, iconId);
        map.put(Constant.SHOP_ITEM_COUNT_KEY, getCount());
        map.put(Constant.SHOP_ITEM_PERFORMANCE_KEY, "");
        return (map);
    }

    boolean tryToBuy (Context context, Storage storage) {
        boolean key = false;
        if (storage.enoughMoney(getPrice())) {
            storage.addMoney(getPrice().negate());
            buy(context);
            key = true;
        }
        return key;
    }

    private void buy(Context context) {
        storage.incrementShopItemCount(getCountKey());
    }

    BigInteger getPrice () {
        return basePrice;
    }

    String getCountKey () {
        return countKey;
    }

    int getCount () {
        return storage.getShopItemCount(getCountKey());
    }

    void setOnCountChangeListener (OnDBChangeListener listener) {
        storage.addOnDBChangeListener(getCountKey(), listener);
    }

    void setJsonAttrs(JSONObject jsonAttrs) throws JSONException {
        this.name = jsonAttrs.getString("name");
        this.basePrice = new BigInteger (jsonAttrs.getString("base_price"));
    }
}
