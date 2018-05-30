package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.StreamSupport;

/**
 * Фрагмент магазина автоматов
 */

public class AutomationShopFragment extends ShopFragment {
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
        initListView(getActivity(), getAutomataList(), R.id.automationShopListView);
    }

    private ArrayList<Automata> getAutomataList() {
        ArrayList<Automata> automataArray = new ArrayList<>();
        JSONLoader loader = new JSONLoader(getActivity());
        JSONArray jsonarray = loader.parceJSONResource(R.raw.automatas);
        for (int i = 0; i < jsonarray.length(); i++){
            try {
                JSONObject obj = jsonarray.getJSONObject(i);
                String name = obj.getString("name");
                int basePerformance = obj.getInt("base_performance");
                int basePrice = obj.getInt("base_price");
                automataArray.add(new Automata(name, basePrice, basePerformance, storage, i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return automataArray;
    }
}
