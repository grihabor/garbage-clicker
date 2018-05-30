package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

/**
 * Фрагмент магазина автоматов
 */

public class AutomationShopFragment extends ShopFragment {
    private final String TAG = "AUTOMATION_FRAGMENT";

    @Override
    int getDataFileId() {
        return R.raw.automatas;
    }

    @Override
    int getListViewId() {
        return R.id.automationShopListView;
    }

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
        return inflater.inflate(R.layout.activity_automation_shop_fragment, null);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public ShopItem apply(JSONObject jsonObject) {
        return new Automata(jsonObject, storage, iterationIndex);
    }
}
