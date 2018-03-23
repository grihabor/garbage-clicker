package ru.sibur.android.garbagecollector;

import android.os.AsyncTask;

/**
 * Поток для работы автоматов
 */

public class AutomataThread extends AsyncTask<Void, Void, Void> {
    Storage storage;
    static final String LAST_UPDATE_NAME = "update";
    static final int TIME_UNIT = 1000;

    AutomataThread(MainActivity activity) {
        this.storage = activity.storage;
    }

    @Override
    protected Void doInBackground(Void... turningOn) {
        long timeDelta = storage.updateAutomataThreadActionTime();
        int moneyDelta = (int) (getMoneyPerTimeUnit() * timeDelta / TIME_UNIT);
        storage.addMoney(moneyDelta);

        while (!(isCancelled())) {
            try {
                Thread.sleep(TIME_UNIT);
            } catch (InterruptedException e) {
                break;
            }
            
            if (isCancelled()) {
                break;
            }
            
            storage.addMoney(getMoneyPerTimeUnit());
            storage.updateAutomataThreadActionTime();

            publishProgress();
        }

        return null;
    }

    protected void onProgressUpdate(Void... voids) {

    }

    int getMoneyPerTimeUnit() {
        //копается в sPref, смотрит, кого сколько купили
        return 100;
    }
}
