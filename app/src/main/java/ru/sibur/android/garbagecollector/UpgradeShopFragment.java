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

    private ArrayList<Upgrade> getUpgradeList() {
        ArrayList<Upgrade> upgradeArray = new ArrayList<>();
        String[] stringsArray = getResources().getStringArray(R.array.upgrade_array);

        for (int index = 0; index < stringsArray.length; index++) {
            int price = 1000 + index * 1000;
            upgradeArray.add(new Upgrade(stringsArray[index], price, storage, index));
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
