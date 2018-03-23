package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private ArrayList<ShopItem> getUpgradeList() {
        ArrayList<ShopItem> upgradeArray = new ArrayList<>();

        //Использование убитых детей класса Automata

        /*
        upgradeArray.add(new AutomataPerfomanceUpgrade(getString(R.string.Up_AutomataPerfomanceUpgrade), 100));
        upgradeArray.add(new UpgradeCostReduce(getString(R.string.Up_UpgradeCostReduce), 200));
        upgradeArray.add(new UpgradeManualClick(getString(R.string.Up_UpgradeManualClick), 300));
        upgradeArray.add(new AutomataDelayReduce(getString(R.string.Up_AutomataDelayReduce), 400));
        upgradeArray.add(new AutomataCostReduce(getString(R.string.Up_AutomataCostReduce), 500));
         */
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
