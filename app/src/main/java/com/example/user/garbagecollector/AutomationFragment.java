package com.example.user.garbagecollector;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Олег on 05.03.2018.
 */

public class AutomationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
        return inflater.inflate(R.layout.automation_shop_fragment, group);
    }
}
