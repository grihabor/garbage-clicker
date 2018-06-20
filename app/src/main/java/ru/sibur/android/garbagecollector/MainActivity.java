package ru.sibur.android.garbagecollector;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


/**
 * Активити самой игры
 */

public class MainActivity extends AppCompatActivity {
    TextView moneyDisplay;
    AutomataThread automataThread = null;
    StateStorage storage;


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
        storage = new StateStorage(this);
        storage.addOnDBChangeListener(Constant.MONEY_KEY, this::updateMoney);
    }
    protected void onStart(){
        super.onStart();
        if(storage.getMusicShouldBe()) startService(new Intent(this, MyService.class));
    }

    protected void onResume() {
        super.onResume();
        automataThread = new AutomataThread(this);
        automataThread.execute();
        updateMoney();
    }

    protected void onPause() {
        super.onPause();
        if (automataThread != null) {
            automataThread.cancel(true);
        }
        stopService(new Intent(this, MyService.class));
    }

    public void switchToUpgradeShopFragment(View view) {
        Shop<Upgrade> upgradeShop = new Shop<>(R.raw.upgrades, this, new Upgrade.Factory());
        switchTo(upgradeShop.getShopFragment(
                R.layout.upgrade_item_view,
                Constant.SHOP_ITEM_ATTRIBUTES,
                Constant.SHOP_ITEM_VIEWS_ATTRS_IDS
        ));
    }

    public void switchToAutomationShopFragment(View view) {
        Shop<Automata> automationShop = new Shop<>(R.raw.automatas, this, new Automata.Factory());
        switchTo(automationShop.getShopFragment(
                R.layout.automata_item_view,
                Constant.SHOP_ITEM_ATTRIBUTES,
                Constant.SHOP_ITEM_VIEWS_ATTRS_IDS
        ));
    }

    public void switchToGarbageRecyclingFragment(View view) {
        switchTo(new GarbageRecyclingFragment());
    }

    public void switchTo(Fragment fragment) {
        FragmentTransaction fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragmentMainLayout, fragment);
        fTrans.commit();
    }

    void updateMoney() {
        moneyDisplay.setText(Constant.formatMoney(storage.getMoney()));
    }

}
