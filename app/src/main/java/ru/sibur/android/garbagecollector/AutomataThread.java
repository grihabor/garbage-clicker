package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.os.AsyncTask;

import java.math.BigInteger;
import java.util.ArrayList;

import java9.util.stream.StreamSupport;

import static java.lang.Math.pow;


/**
 * Поток для работы автоматов
 */

public class AutomataThread extends AsyncTask<Void, Void, Void> implements AutomataMoneyCalculator {
    private final String TAG = "AUTOMATA_THREAD";
    Storage storage;
    Context context;


    AutomataThread(StateStorage storage, Context context) {
        this.storage = storage;
        this.context = context;
    }

    @Override
    public BigInteger calculateMoney(BigInteger timeDifference) {
        return getMoneyPerTimeUnit()
                .multiply(timeDifference)
                .divide(BigInteger.valueOf(Constant.TIME_UNIT));
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

    BigInteger getMoneyPerTimeUnit() {

        AutomationShop automationShop = new AutomationShop(R.raw.automatas, context, storage);
        BigInteger sum = automationShop.getTotalPerformance();

        return sum;
    }
}
