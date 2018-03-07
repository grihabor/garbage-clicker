package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        AutomataCostReduce automataCostReduce = new AutomataCostReduce();
        UpgradeCostReduce upgradeCostReduce = new UpgradeCostReduce();
        UpgradePerformanceOfAutomata upgradePerformanceOfAutomat = new UpgradePerformanceOfAutomata();



        //создание и инициализация listView
        ListView listView = getView().findViewById(R.id.upgradeShopListView);


        //заполнение listView строками

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        HashMap<String, String> map;

        map = new HashMap<>();



        addToHashMap(getString(R.string.Upgrade_1), upgradeClick.price, map, arrayList);
        addToHashMap(getString(R.string.Upgrade_2), automataCostReduce.price, map, arrayList);
        addToHashMap(getString(R.string.Upgrade_3), upgradeCostReduce.price, map, arrayList);
        addToHashMap(getString(R.string.Upgrade_4), upgradePerformanceOfAutomat.price, map, arrayList);
        addToHashMap(getString(R.string.Upgrade_5), upgradeTimeOfAutomat.price, map, arrayList);


        SimpleAdapter adapter = new SimpleAdapter(context, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Price"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);



    }


    public void addToHashMap(String value, float price, HashMap<String, String> map, ArrayList<HashMap<String, String>> arrayList){
        map = new HashMap<>();
        map.put("Name", value);
        map.put("Price", "Стоимость : "+String.valueOf(price));
        arrayList.add(map);
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.activity_upgrade_shop_fragment, null);
    }

}
