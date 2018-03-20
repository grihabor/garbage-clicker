package ru.sibur.android.garbagecollector;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Активити самой игры
 */

public class MainActivity extends AppCompatActivity implements OnMoneyUpdateListener {
    TextView moneyDisplay;
    AutomataThread automataThread = null;

    
    @Override
    public void OnMoneyUpdate() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.PREF_NAME, MODE_PRIVATE);
        float money = sharedPreferences.getInt(Constant.MONEY_KEY, 0) * Constant.MONEY_DISPLAY_COEFFICIENT;
        NumberFormat formatter = new DecimalFormat("#0.00");     
        moneyDisplay.setText(formatter.format(money));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //инициализация игрового фрагмента
        switchToGarbageRecyclingFragment(null);

        moneyDisplay = findViewById(R.id.moneyDisplay);
        OnMoneyUpdate();
    }

    protected void onResume() {
        super.onResume();
        automataThread = new AutomataThread(this, this);
        automataThread.execute();
    }

    protected void onPause() {
        super.onPause();
        if (automataThread != null) {
            automataThread.cancel(true);
        }
    }

    public void switchToUpgradeShopFragment(View view) {
        switchTo(new UpgradeShopFragment());
    }

    public void switchToAutomationShopFragment(View view) {
        switchTo(new AutomationShopFragment());
    }

    public void switchToGarbageRecyclingFragment(View view) {
        switchTo(new GarbageRecyclingFragment());
    }

    public void switchTo(Fragment fragment) {
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragmentMainLayout, fragment);
        fTrans.commit();
    }
}
