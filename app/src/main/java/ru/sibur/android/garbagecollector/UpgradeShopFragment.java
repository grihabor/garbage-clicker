package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class UpgradeShopFragment extends Fragment {


    public void onAttach (Activity activity){

        super.onAttach(activity);
    }

    public void onStart (){
        super.onStart();

        Context context = getActivity();
        initListView(context);



        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
         //       android.R.layout.simple_list_item_1, upgrades);

        //listView.setAdapter(adapter);


    }

    private void initListView(Context context) {

        //инициализация апгрейдов
        UpgradeClick upgradeClick = new UpgradeClick();
        UpgradeTimeOfAutomat upgradeTimeOfAutomat = new UpgradeTimeOfAutomat();
        UpgradeCostOfAutomat upgradeCostOfAutomat = new UpgradeCostOfAutomat();
        UpgradeCostOfUpgrade upgradeCostOfUpgrade = new UpgradeCostOfUpgrade();
        UpgradePerformanceOfAutomat upgradePerformanceOfAutomat = new UpgradePerformanceOfAutomat();



        //создание и инициализация listView
        ListView listView = getView().findViewById(R.id.upgradeShopListView);


        //заполнение listView строками

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        HashMap<String, String> map;

        map = new HashMap<>();
        map.put("Name", getString(R.string.Upgrade_1));
        map.put("Price", "Стоимость : "+String.valueOf(upgradeClick.price));
        arrayList.add(map);

        map = new HashMap<>();
        map.put("Name", getString(R.string.Upgrade_2));
        map.put("Price", "Стоимость : "+String.valueOf(upgradeCostOfAutomat.price));
        arrayList.add(map);

        map = new HashMap<>();
        map.put("Name", getString(R.string.Upgrade_3));
        map.put("Price", "Стоимость : "+String.valueOf(upgradeCostOfUpgrade.price));
        arrayList.add(map);

        map = new HashMap<>();
        map.put("Name", getString(R.string.Upgrade_4));
        map.put("Price", "Стоимость : "+String.valueOf(upgradePerformanceOfAutomat.price));
        arrayList.add(map);

        map = new HashMap<>();
        map.put("Name", getString(R.string.Upgrade_5));
        map.put("Price", "Стоимость : "+String.valueOf(upgradeTimeOfAutomat.price));
        arrayList.add(map);

        SimpleAdapter adapter = new SimpleAdapter(context, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Price"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);



    }



    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.activity_upgrade_shop_fragment, null);
    }

}
