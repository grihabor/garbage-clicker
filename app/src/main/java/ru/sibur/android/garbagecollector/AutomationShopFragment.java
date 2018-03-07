package ru.sibur.android.garbagecollector;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.garbagecollector.R;


public class AutomationShopFragment extends Fragment {
    @Override
    public void onAttach (Activity activity){

        super.onAttach(activity);
    }
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
        return inflater.inflate(R.layout.automation_shop_fragment, null);
    }
}
