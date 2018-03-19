package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Фрагмент ручной переработки мусора
 */
 
public class GarbageRecyclingFragment extends Fragment {
    OnMoneyUpdateListener listener = null;

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        listener = (OnMoneyUpdateListener) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_garbage_recycling_fragment, null);
    }

    public void onStart() {
        super.onStart();

        Button baffer = getView().findViewById(R.id.button);

        baffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = 
                    getActivity()
                    .getSharedPreferences(
                            Constant.PREF_NAME,
                        Context.MODE_PRIVATE
                    );
                SharedPreferences.Editor editor = sp.edit();
                int money = sp.getInt(Constant.MONEY_KEY, 0);
                editor.putInt(Constant.MONEY_KEY, money + 100);
                editor.apply();

                if (listener != null) {
                    listener.OnMoneyUpdate();
                }
            }
        });
    }
}
