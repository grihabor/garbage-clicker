package com.example.user.garbagecollector;

import android.content.SharedPreferences;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static TextView SiburikiImage;
    final static String MY_PREF = "my_pref";
    public final static String SIBURIKI = "siburiki";
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

        sharedPreferences = getSharedPreferences(MY_PREF, MODE_PRIVATE);

        SiburikiImage = new TextView(this);
        SiburikiImage = findViewById(R.id.siburiksNum);
        setSiburiki();
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

    protected static void setSiburiki () {
        SiburikiImage.setText("Сибурики: " + sharedPreferences.getInt(SIBURIKI, 0));
    }
}
