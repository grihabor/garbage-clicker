package ru.sibur.android.garbagecollector;

import android.content.Context;
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
    int iconId;
    Storage storage;

    private final String TAG = "SHOP_ITEM";

    ShopItem(JSONObject attributes, Storage storage) {
        this.storage = storage;

        try {
            this.name = attributes.getString("name");
            this.basePrice = new BigInteger (attributes.getString("base_price"));
            this.iconId = Constant.SHOP_ITEMS_ICON_IDS[attributes.getInt("icon_id")];
        } catch (JSONException e) {
            Log.e(TAG, "JSONException: " + e.getMessage());
        }
    }
    
    public HashMap<String, Object> getViewData() {
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

    abstract String getCountKey ();

    int getCount () {
        return storage.getShopItemCount(getCountKey());
    }

    void setOnCountChangeListener (OnDBChangeListener listener) {
        storage.addOnDBChangeListener(getCountKey(), listener);
    }
}
