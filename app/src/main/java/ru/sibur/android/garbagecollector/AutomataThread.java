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


    AutomataThread(Context context, OnMoneyUpdateListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... turningOn) {

        SharedPreferences sPref = context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);

        Date now = new Date();
        long timeDelta = now.getTime() - sPref.getLong(Constant.LAST_UPDATE_NAME, now.getTime());
        int moneyDelta = (int) (getMoneyPerTimeUnit() * timeDelta / Constant.TIME_UNIT);
        int money = sPref.getInt(Constant.MONEY_KEY, 0);

        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(Constant.MONEY_KEY, money + moneyDelta);
        editor.putLong(Constant.LAST_UPDATE_NAME, now.getTime());
        editor.apply();

        while (!(isCancelled())) {
            try {
                Thread.sleep(Constant.TIME_UNIT);
            } catch (InterruptedException e) {
                break;
            }
            
            if (isCancelled()) {
                break;
            }
            
            money = sPref.getInt(Constant.MONEY_KEY, 0);
            editor.putInt(Constant.MONEY_KEY, money + getMoneyPerTimeUnit());
            editor.putLong(Constant.LAST_UPDATE_NAME, (new Date()).getTime());
            editor.apply();

            publishProgress();
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
