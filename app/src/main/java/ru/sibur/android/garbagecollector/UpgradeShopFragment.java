package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class UpgradeShopFragment extends ShopFragment {
    OnMoneyUpdateListener listener = null;
    public void onAttach (Activity activity)
    {
        super.onAttach(activity);
        listener = (OnMoneyUpdateListener) activity;
    }

    public void onStart (){
        super.onStart();
        Context context = getActivity();
        initListView(context);
    }


    private void initListView(Context context) {
        ArrayList <ShopItem> upgradeArray = getUpgradeList();
        ListView listView = getView().findViewById(R.id.upgradeShopListView);
        SimpleAdapter adapter = getListViewAdapter(context, upgradeArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Upgrade current = upgradeArray.get(position);
                current.tryToBuy(context, listener);
            }
        });
    }




    private ArrayList<ShopItem> getUpgradeList(){
        ArrayList <ShopItem> upgradeArray = new ArrayList<>();

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
