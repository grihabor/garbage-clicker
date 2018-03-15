package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
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
import java.util.HashMap;

public class UpgradeShopFragment extends Fragment {
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

    private void initListView(final Context context) {

        final ArrayList <Upgrade> upgradeArray = getUpgradeList();

        ListView listView = getView().findViewById(R.id.upgradeShopListView);

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for (int i = 0; i < upgradeArray.size(); i++){
            arrayList.add(upgradeArray.get(i).getViewData());
        }

        SimpleAdapter adapter = new SimpleAdapter(context, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Price"},
                new int[]{android.R.id.text1, android.R.id.text2});
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



    private ArrayList<Upgrade> getUpgradeList(){
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
