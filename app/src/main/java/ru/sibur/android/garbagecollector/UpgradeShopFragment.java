package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Фрагмент магазина улучшений
 */ 

public class UpgradeShopFragment extends ShopFragment {
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onStart() {
        super.onStart();
        Context context = getActivity();
        initListView(context, getUpgradeList(), R.id.upgradeShopListView);
    }

    private ArrayList<Upgrade> getUpgradeList() {
        ArrayList<Upgrade> upgradeArray = new ArrayList<>();
        try {
            JSONArray jsonarray = new JSONArray(readJson(R.raw.automatas));
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                int basePrice = jsonobject.getInt("base_price");
                int basePerformance = jsonobject.getInt("base_performance");
                upgradeArray.add(new Upgrade(name, basePrice, basePerformance, storage, i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return upgradeArray;

    }
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_upgrade_shop_fragment, null);
    }

}
