package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

/**
 * Фрагмент магазина улучшений
 */ 

public class UpgradeShopFragment extends ShopFragment {
    @Override
    int getDataFileId() {
        return R.raw.upgrades;
    }

    @Override
    int getListViewId() {
        return R.id.upgradeShopListView;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onStart() {
        super.onStart();
    }
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_upgrade_shop_fragment, null);
    }

    @Override
    public ShopItem apply(JSONObject jsonObject) {
        return new Upgrade(jsonObject, storage, iterationIndex);
    }
}
