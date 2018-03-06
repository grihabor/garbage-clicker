package com.example.user.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by User on 05.03.2018.
 *
 */

public class UpgradeShopFragment extends Fragment {


    public void onAttach (Activity activity){

        super.onAttach(activity);
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        //заполнение списка всяким барахлом
        /*ListView listView = getView().findViewById(R.id.upgradeShopListView);
        /*final String [] upgrades = new String []{
                "upgrade1", "upgrade2"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, upgrades);
        listView.setAdapter(adapter);*/

        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.activity_upgrade_shop_fragment, null);
    }

}
