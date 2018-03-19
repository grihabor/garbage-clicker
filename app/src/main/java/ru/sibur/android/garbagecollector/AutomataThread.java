package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.util.Date;

/**
 * Поток для работы автоматов
 */

public class AutomataThread extends AsyncTask<Void, Void, Void> {
    Context context;
    OnMoneyUpdateListener listener;
    final static String LAST_UPDATE_NAME = "update";
    final static int TIME_UNIT = 1000;

    AutomataThread(Context context, OnMoneyUpdateListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... turningOn) {

        SharedPreferences sPref = context.getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);

        Date now = new Date();
        long timeDelta = now.getTime() - sPref.getLong(LAST_UPDATE_NAME, now.getTime());
        int moneyDelta = (int)(getMoneyPerTimeUnit() * timeDelta / TIME_UNIT);
        int money = sPref.getInt(MainActivity.MONEY_KEY, 0);

        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(MainActivity.MONEY_KEY, money + moneyDelta);
        editor.putLong(LAST_UPDATE_NAME, now.getTime());
        editor.apply();

        while (!(isCancelled())) {
            try {
                Thread.sleep(TIME_UNIT);

                money = sPref.getInt(MainActivity.MONEY_KEY, 0);
                editor.putInt(MainActivity.MONEY_KEY, money + getMoneyPerTimeUnit());
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

    int getMoneyPerTimeUnit() {
        //копается в sPref, смотрит, кого сколько купили
        return 100;
    }
}