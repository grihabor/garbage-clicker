package com.example.user.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 05.03.2018.
 *
 */

public class GarbageRecyclingFragmentActivity  extends Fragment{
    public void onAttach (Activity activity){

        super.onAttach(activity);
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_garbage_recycling_fragment, null);
    }
}
