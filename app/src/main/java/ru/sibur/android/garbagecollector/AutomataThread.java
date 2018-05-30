package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.os.AsyncTask;

import java9.util.stream.IntStream;

import static java.lang.Math.pow;


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

    int getMoneyPerTimeUnit() {
        String[] AutomataNames = context.getResources().getStringArray(R.array.automata_array);

        int totalMoneyPerTimeUnit = IntStream
                                    .range(0, AutomataNames.length)
                                    .map(i -> storage.getShopItemCount(Constant.automataCountKey(i)) * Constant.automataPerformance(i))
                                    .sum();
        totalMoneyPerTimeUnit= (int) (
            totalMoneyPerTimeUnit
            *pow(1.15, this.storage.getShopItemCount(Constant.upgradeCountKey(3)))
            *pow(1.15, this.storage.getShopItemCount(Constant.upgradeCountKey(4))
            ));

        return totalMoneyPerTimeUnit;
    }
}
