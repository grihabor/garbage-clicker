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

        ArrayList <Upgrade> upgradeArray = getUpgradeArray();

        ListView listView = getView().findViewById(R.id.upgradeShopListView);

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

    private ArrayList<Upgrade> getUpgradeArray(){
        ArrayList <Upgrade> upgradeArray = new ArrayList<Upgrade>();

        upgradeArray.add(new AutomataPerfomanceUpgrade(getString(R.string.Up_AutomataPerfomanceUpgrade), 1));
        upgradeArray.add(new UpgradeCostReduce(getString(R.string.Up_UpgradeCostReduce), 2));
        upgradeArray.add(new UpgradeManualClick(getString(R.string.Up_UpgradeManualClick), 3));
        upgradeArray.add(new AutomataDelayReduce(getString(R.string.Up_AutomataDelayReduce), 4));
        upgradeArray.add(new AutomataCostReduce(getString(R.string.Up_AutomataCostReduce), 5));

        return upgradeArray;

    }

    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.activity_upgrade_shop_fragment, null);
    }

}
