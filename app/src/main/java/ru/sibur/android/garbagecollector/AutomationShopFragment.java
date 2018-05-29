package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

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

        try {
            JSONArray jsonarray = new JSONArray(readJson(R.raw.automatas));
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                int basePrice = jsonobject.getInt("base_price");
                int basePerformance = jsonobject.getInt("base_performance");
                automataArray.add(new Automata(name, 100*basePrice, 100*basePerformance, storage, i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return automataArray;
    }



}
