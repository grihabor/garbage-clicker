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
        ArrayList <Automata> upgradeArray = getUpgradeArray();

        ListView listView = getView().findViewById(R.id.automationShopListView);

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for (int i = 0; i < upgradeArray.size(); i++){
            addToHashMap(upgradeArray.get(i).name, upgradeArray.get(i).price, arrayList);
        }

        SimpleAdapter adapter = new SimpleAdapter(context, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Price"},
                new int[]{android.R.id.text1, android.R.id.text2});

        listView.setAdapter(adapter);

    }

    private void addToHashMap(String value, float price, ArrayList<HashMap<String, String>> arrayList){
        HashMap<String, String> map = new HashMap<>();
        map.put("Name", value);
        map.put("Price", "Стоимость : "+String.valueOf(price));
        arrayList.add(map);
    }

    private ArrayList<Automata> getUpgradeArray(){
        ArrayList <Automata> upgradeArray = new ArrayList<Automata>();

        upgradeArray.add(new LittleAutomata(getString(R.string.Up_LittleAutomata), 1));
        upgradeArray.add(new MediumAutomata(getString(R.string.Up_MediumAutomata), 2));
        upgradeArray.add(new BigAutomata(getString(R.string.Up_BigAutomata), 3));


        return upgradeArray;

    }


}
