package com.example.user.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class GarbageRecyclingFragment extends Fragment {

    @Override
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

    public void onStart() {
        super.onStart();

        Button baffer = getView().findViewById(R.id.button);

        baffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
                int CurrentMoney = MainActivity.sharedPreferences.getInt(MainActivity.MONEY_KEY, 0);
                editor.putInt(MainActivity.MONEY_KEY, CurrentMoney + 1);
                editor.apply();

                MainActivity.setMoneyDisplay();
            }
        });
    }
}
