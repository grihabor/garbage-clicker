package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.util.Date;

/**
 * Created by Олег on 16.03.2018.
 */

public class AutomataThread extends AsyncTask<Void, Void, Void> {
    Context context;
    OnMoneyUpdateListener listener;
    String LAST_UPDATE_NAME = "update";
    final static String POWER_NAME = "power";
    final static int TIME_UNIT = 1000;

    AutomataThread(Context context, OnMoneyUpdateListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... turningOn) {

        SharedPreferences sPref = context.getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);

        Date Now = new Date();
        int TimeDifference = (int) (Now.getTime() - (sPref.getLong(LAST_UPDATE_NAME, Now.getTime())));
        int DeltaMoney = sPref.getInt(POWER_NAME, 0)*TimeDifference/TIME_UNIT;
        int Money = sPref.getInt(MainActivity.MONEY_KEY, 0);

        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(MainActivity.MONEY_KEY, Money + DeltaMoney);
        editor.putLong(LAST_UPDATE_NAME, Now.getTime());
        editor.apply();

        while (!(isCancelled())) {
            try {
                Thread.sleep(TIME_UNIT);

                Money = sPref.getInt(MainActivity.MONEY_KEY, 0);
                editor.putInt(MainActivity.MONEY_KEY, Money + sPref.getInt(POWER_NAME, 0));
                editor.putLong(LAST_UPDATE_NAME, (new Date()).getTime());
                editor.apply();

                publishProgress();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    protected void onProgressUpdate(Void... voids) {
        listener.OnMoneyUpdate();
    }
}