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


public class AutomationShopFragment extends Fragment {
    @Override
    public void onAttach (Activity activity){

        super.onAttach(activity);
    }

    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
        return inflater.inflate(R.layout.activity_automation_shop_fragment, null);
    }

    public void onStart(){
        super.onStart();

        Context context = getActivity();
        initListView(context);
    }

    private void initListView(Context context) {
        //инициализация автоматов
        BigAutomata bigAutomata = new BigAutomata();
        MediumAutomata mediumAutomata = new MediumAutomata();
        LittleAutomata littleAutomata = new LittleAutomata();

        //создание и инициализация listView
        ListView listView = getView().findViewById(R.id.automationShopListView);

        //заполнение listView строками

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        addToHashMap(getString(R.string.Up_BigAutomata), bigAutomata.price, arrayList);
        addToHashMap(getString(R.string.Up_MediumAutomata), mediumAutomata.price,  arrayList);
        addToHashMap(getString(R.string.Up_LittleAutomata), littleAutomata.price, arrayList);

        SimpleAdapter adapter = new SimpleAdapter(context, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Price"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);


    }

    private void addToHashMap(String value, float price,  ArrayList<HashMap<String, String>> arrayList){
        HashMap<String, String> map = new HashMap<>();
        map.put("Name", value);
        map.put("Price", "Стоимость : "+String.valueOf(price));
        arrayList.add(map);
    }

}
