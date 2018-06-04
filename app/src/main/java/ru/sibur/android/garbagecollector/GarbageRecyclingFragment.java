package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Math.pow;

/**
 * Фрагмент ручной переработки мусора
 */
 
public class GarbageRecyclingFragment extends Fragment {
    Storage storage;
    TextView clickPerformanceTextView;
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
        Activity activity = getActivity();
            if (activity != null) {
                clickPerformanceTextView = activity.findViewById(R.id.click_pefrormance_text_view);
            }

        Button baffer = activity.findViewById(R.id.button);

        baffer.setOnClickListener(v -> {
          Showing_money_per_click
            storage.addMoney((int) (getClickPerformance())
            );
            showClickPerformance();
        });

        showClickPerformance();

    }

    public int getClickPerformance() {
        String countKey = Constant.upgradeCountKey(0);
        int count = this.storage.getShopItemCount(countKey);
        int clickPerformance = (int) (100 * pow(1.15, count));
        return clickPerformance;
    }

    public void showClickPerformance(){
        int clickPerformance = getClickPerformance();
        String clickPerformanceString = Constant.formatMoney(clickPerformance);
        clickPerformanceTextView.setText(clickPerformanceString);

    }
}
