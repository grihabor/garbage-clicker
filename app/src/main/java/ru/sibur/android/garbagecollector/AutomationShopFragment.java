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
    OnMoneyUpdateListener listener = null;

    @Override
    public void onAttach (Activity activity)
    {
        super.onAttach(activity);
        listener = (OnMoneyUpdateListener) activity;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
  
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
        return inflater.inflate(R.layout.activity_automation_shop_fragment, null);
    }

    public void onStart(){
        super.onStart();
    }
}
