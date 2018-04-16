package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        String[] stringsArray = getResources().getStringArray(R.array.automata_array);
        for (int i = 0; i < stringsArray.length; i++) {
            int price = 1000 * (i + 1);
            automataArray.add(new Automata(stringsArray[i], price, R.drawable.ferum, storage, i));
        }
        return automataArray;
    }
}
