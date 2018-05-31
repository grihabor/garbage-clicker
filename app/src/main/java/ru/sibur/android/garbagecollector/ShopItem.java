package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Элемент списка в фрагменте магазина
 */ 

public abstract class ShopItem {
    long basePrice;
    String name;
    int iconId;
    Storage storage;

    private final String TAG = "SHOP_ITEM";

    ShopItem(JSONObject attributes, Storage storage) {
        this.storage = storage;

        try {
            this.name = attributes.getString("name");
            this.basePrice = attributes.getInt("base_price");
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

    void tryToBuy (Context context, Storage storage) {
        if (storage.getMoney() >= getPrice()) {
            storage.addMoney(-getPrice());

            buy(context);
        } else {
            Toast.makeText(context, "не хватает средств", Toast.LENGTH_SHORT).show();
        }
    }

    private void buy(Context context) {
        Toast.makeText(context, "спасибо за покупку", Toast.LENGTH_SHORT).show();
        storage.incrementShopItemCount(getCountKey());
    }

    long getPrice () {
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
