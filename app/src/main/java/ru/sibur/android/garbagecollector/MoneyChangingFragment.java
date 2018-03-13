package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by Олег on 12.03.2018.
 */

public class MoneyChangingFragment extends Fragment {
    OnMoneyUpdateListener listener = null;

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);

        listener = (OnMoneyUpdateListener) activity;
    }
}
