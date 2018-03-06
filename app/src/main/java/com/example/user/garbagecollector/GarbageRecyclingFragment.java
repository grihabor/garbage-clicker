package com.example.user.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class GarbageRecyclingFragment extends Fragment {
    Button baffer;
    SharedPreferences sharedPreferences;

    @Override
    public void onAttach (Activity activity){
        super.onAttach(activity);
    }

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_garbage_recycling_fragment, null);
    }

    public void onStart() {
        super.onStart();

        baffer = new Button(this.getActivity());
        baffer = getView().findViewById(R.id.button);

        sharedPreferences = MainActivity.sharedPreferences;


        baffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int CurrentSiburiki = sharedPreferences.getInt(MainActivity.SIBURIKI, 0);
                editor.putInt(MainActivity.SIBURIKI, CurrentSiburiki + 1);
                editor.apply();

                MainActivity.setSiburiki();
            }
        });
    }
}
