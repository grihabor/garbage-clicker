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
        showClickPerformance();

        Button baffer = getView().findViewById(R.id.button);

        baffer.setOnClickListener(v -> {
            storage.addMoney((int) (
                    100
                    *pow(1.15,this.storage.getShopItemCount(Constant.upgradeCountKey(0)))
                    )

            );
            showClickPerformance();
        });
    }

    public void showClickPerformance(){
        String countKey = Constant.upgradeCountKey(0);
        int count = this.storage.getShopItemCount(countKey);
        int clickPerformance =(int)(100*pow(1.15,count));
        String clickPerformancestring =Constant.formatMoney(clickPerformance);
        clickPerformanceTextView.setText(clickPerformancestring);

    }
}
