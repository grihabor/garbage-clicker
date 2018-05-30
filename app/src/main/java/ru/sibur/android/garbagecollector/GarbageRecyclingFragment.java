package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static java.lang.Math.pow;

/**
 * Фрагмент ручной переработки мусора
 */
 
public class GarbageRecyclingFragment extends Fragment {
    Storage storage;

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        storage = ((MainActivity) activity).storage;
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

        baffer.setOnClickListener(v -> {
            storage.addMoney((int) (
                    100
                    *pow(1.15,
                    this.storage.getShopItemCount(Constant.upgradeCountKey(0)))
                    )
            );
        });
    }
}
