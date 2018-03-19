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
  
    String LAST_UPDATE_NAME = "update";


    AutomataThread(Context context, OnMoneyUpdateListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... turningOn) {

        SharedPreferences sPref = context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE);


        Date Now = new Date();
        int TimeDifference = (int) (Now.getTime() - (sPref.getLong(LAST_UPDATE_NAME, Now.getTime())));
        int DeltaMoney = getMoneyPerTimeUnit()*TimeDifference/Constant.TIME_UNIT;
        int Money = sPref.getInt(Constant.MONEY_KEY, 0);

        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(Constant.MONEY_KEY, Money + DeltaMoney);
        editor.putLong(LAST_UPDATE_NAME, Now.getTime());

        editor.apply();

        while (!(isCancelled())) {
            try {
                Thread.sleep(Constant.TIME_UNIT);

                Money = sPref.getInt(Constant.MONEY_KEY, 0);
                editor.putInt(Constant.MONEY_KEY, Money + getMoneyPerTimeUnit());
                editor.putLong(LAST_UPDATE_NAME, (new Date()).getTime());
                editor.apply();

                publishProgress();

            } catch (InterruptedException e) {
                break;
            }
            
            if (isCancelled()) {
                break;
            }
            
            money = sPref.getInt(MainActivity.MONEY_KEY, 0);
            editor.putInt(MainActivity.MONEY_KEY, money + getMoneyPerTimeUnit());
            editor.putLong(LAST_UPDATE_NAME, (new Date()).getTime());
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
