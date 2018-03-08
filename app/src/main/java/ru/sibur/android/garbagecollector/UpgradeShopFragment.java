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
    }

    private void initListView(Context context) {
        //инициализация апгрейдов
        UpgradeManualClick upgradeManualClick = new UpgradeManualClick();
        AutomataDelayReduce automataDelayReduce = new AutomataDelayReduce();
        AutomataCostReduce automataCostReduce = new AutomataCostReduce();
        UpgradeCostReduce upgradeCostReduce = new UpgradeCostReduce();
        AutomataPerfomanceUpgrade automataPerfomanceUpgrade = new AutomataPerfomanceUpgrade();
        //создание и инициализация listView
        ListView listView = getView().findViewById(R.id.upgradeShopListView);
        //заполнение listView строками
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        addToHashMap(getString(R.string.Up_UpgradeManualClick), upgradeManualClick.price, arrayList);
        addToHashMap(getString(R.string.Up_AutomataCostReduce), automataCostReduce.price, arrayList);
        addToHashMap(getString(R.string.Up_UpgradeCostReduce), upgradeCostReduce.price, arrayList);
        addToHashMap(getString(R.string.Up_AutomataPerfomanceUpgrade), automataPerfomanceUpgrade.price, arrayList);
        addToHashMap(getString(R.string.Up_AutomataDelayReduce), automataDelayReduce.price, arrayList);
        //добавление содержимого listView на экран
        SimpleAdapter adapter = new SimpleAdapter(context, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Price"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);

    }


    public void addToHashMap(String value, float price, ArrayList<HashMap<String, String>> arrayList){
        HashMap<String, String> map = new HashMap<>();
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
