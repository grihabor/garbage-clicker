package com.example.user.garbagecollector;


import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    AutomationShopFragment automationShopFragment;
    GarbageRecyclingFragment garbageRecyclingFragment;
    UpgradeShopFragment upgradeShopFragment;

    FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //инициализация игрового фрагмента
        garbageRecyclingFragment = new GarbageRecyclingFragment();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.fragmentMainLayout, garbageRecyclingFragment);
        fTrans.commit();
    }

    public void switchToUpgradeShopFragment(View view) {
        upgradeShopFragment = new UpgradeShopFragment();
        switchTo(upgradeShopFragment);
    }

    public void switchToAutomationShopFragment(View view) {
        automationShopFragment = new AutomationShopFragment();
        switchTo(automationShopFragment);
    }

    public void switchToGarbageRecyclingFragment(View view) {
        garbageRecyclingFragment = new GarbageRecyclingFragment();
        switchTo(garbageRecyclingFragment);
    }

    public void switchTo(Fragment fragment){
        fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragmentMainLayout, fragment);
        fTrans.commit();
    }
}
