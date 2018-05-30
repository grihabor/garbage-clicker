package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.StreamSupport;

import static android.content.ContentValues.TAG;

/**
 * Фрагмент магазина автоматов
 */

public class AutomationShopFragment extends ShopFragment {
    private final String TAG = "AUTOMATION_FRAGMENT";
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
        if(jsonarray != null) {
            for (int i = 0; i < jsonarray.length(); i++) {
                try {
                    JSONObject automataAttributes = jsonarray.getJSONObject(i);
                    String name = automataAttributes.getString("name");
                    int basePerformance = automataAttributes.getInt("base_performance");
                    int basePrice = automataAttributes.getInt("base_price");
                    int iconId = Constant.SHOP_ITEMS_ICON_IDS[automataAttributes.getInt("icon_id_num")];
                    automataArray.add(new Automata(name, basePrice, basePerformance, iconId, storage, i));
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException: " + e.getMessage());
                }
            }
            return automataArray;
        } else {
            Log.e(TAG, "Unable to load data from automatas.json");
            return null;
        }
    }
}
