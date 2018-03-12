package ru.sibur.android.garbagecollector;

import android.app.Fragment;

/**
 * Created by Олег on 12.03.2018.
 */

public class MoneyChangingFragment extends Fragment {
    OnMoneyUpdateListener listener = null;

    public void setMoneyUpdateListener (OnMoneyUpdateListener listener) {
        this.listener = listener;
    }
}
