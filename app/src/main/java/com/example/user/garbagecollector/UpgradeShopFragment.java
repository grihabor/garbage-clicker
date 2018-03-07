package com.example.user.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class UpgradeShopFragment extends Fragment {


    public void onAttach (Activity activity){

        super.onAttach(activity);
    }
    public void onStart (){
        super.onStart();

        Context context = getActivity();

        UpgradeClick upgradeClick = new UpgradeClick();
        UpgradeTimeOfAutomat upgradeTimeOfAutomat = new UpgradeTimeOfAutomat();
        UpgradeCostOfAutomat upgradeCostOfAutomat = new UpgradeCostOfAutomat();
        UpgradeCostOfUpgrade upgradeCostOfUpgrade = new UpgradeCostOfUpgrade();
        UpgradePerformanceOfAutomat upgradePerformanceOfAutomat = new UpgradePerformanceOfAutomat();

        String[] upgrades = new String[] {
          getString(R.string.Upgrade_1), getString(R.string.Upgrade_2), getString(R.string.Upgrade_3), getString(R.string.Upgrade_4), getString(R.string.Upgrade_5)
        };

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
         //       android.R.layout.simple_list_item_1, upgrades);

        //listView.setAdapter(adapter);


    }

    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.activity_upgrade_shop_fragment, null);
    }

}
