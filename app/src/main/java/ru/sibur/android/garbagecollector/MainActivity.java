package ru.sibur.android.garbagecollector;

import android.content.SharedPreferences;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements OnMoneyUpdateListener {
    TextView moneyDisplay;
    AutomataThread automataThread = null;
    public final static String MONEY_KEY = "money_key";
    public final static String PREF_NAME = "my_pref";
    public final static float MONEY_DISPLAY_COEFFICIENT = (float) 0.01;

    public void OnMoneyUpdate() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        moneyDisplay.setText((sharedPreferences.getInt(MONEY_KEY, 0))*MONEY_DISPLAY_COEFFICIENT + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

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
        automataThread.cancel(true);
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

    public void switchTo(Fragment fragment){
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragmentMainLayout, fragment);
        fTrans.commit();
    }
}
