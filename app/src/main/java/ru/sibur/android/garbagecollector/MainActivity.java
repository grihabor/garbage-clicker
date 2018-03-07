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

import com.example.user.garbagecollector.R;

public class MainActivity extends AppCompatActivity {
    static TextView moneyDisplay;
    public final static String MONEY_KEY = "money_key";
    public final static String PREF_NAME = "my_pref";
    protected static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //инициализация игрового фрагмента
        GarbageRecyclingFragment garbageRecyclingFragment = new GarbageRecyclingFragment();
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.fragmentMainLayout, garbageRecyclingFragment);
        fTrans.commit();

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        moneyDisplay = findViewById(R.id.moneyDisplay);
        setMoneyDisplay();
    }

    public void switchToUpgradeShopFragment(View view) {
        switchTo(new UpgradeShopFragment());
    }

    public void switchToAutomationShopFragment(View view) {
        switchTo(new AutomationShopFragment());
    }

    public void switchToGarbageRecyclingFragment(View view) {
        switchTo( new GarbageRecyclingFragment());
    }

    public void switchTo(Fragment fragment){
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragmentMainLayout, fragment);
        fTrans.commit();
    }

    protected static void setMoneyDisplay () {
        moneyDisplay.setText(sharedPreferences.getInt(MONEY_KEY, 0) + "");
    }
}
