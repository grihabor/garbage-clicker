package ru.sibur.android.garbagecollector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import java.util.stream.IntStream;


/**
 * Поток для работы автоматов
 */

public class AutomataThread extends AsyncTask<Void, Void, Void> implements AutomataMoneyCalculator {
    Storage storage;
    Context context;


    AutomataThread(StateStorage storage, Context context) {
        this.storage = storage;
        this.context = context;

    }

    @Override
    public int calculateMoney(long timeDifference) {
        return (int) (getMoneyPerTimeUnit() * timeDifference / Constant.TIME_UNIT);
    }

    @Override
    protected Void doInBackground(Void... turningOn) {
        storage.updateAutomataThreadActionTime(this);

        while (!(isCancelled())) {
            try {
                Thread.sleep(Constant.TIME_UNIT);
            } catch (InterruptedException e) {
                break;
            }
            
            if (isCancelled()) {
                break;
            }

            storage.updateAutomataThreadActionTime(this);

            publishProgress();
        }

        return null;
    }

    protected void onProgressUpdate(Void... voids) {

    }

    @SuppressLint("NewApi")
    int getMoneyPerTimeUnit() {
        String[] AutomataNames = context.getResources().getStringArray(R.array.automata_array);

        int totalMoneyPerTimeUnit = IntStream
                                    .range(0, AutomataNames.length)
                                    .map(i -> storage.getShopItemCount(Constant.automataCountKey(i)) * Constant.automataPerformance(i))
                                    .sum();
        return totalMoneyPerTimeUnit;
    }
}
