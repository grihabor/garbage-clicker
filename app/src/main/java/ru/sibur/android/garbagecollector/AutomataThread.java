package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.os.AsyncTask;

import java.math.BigInteger;

import java9.util.stream.StreamSupport;


/**
 * Поток для работы автоматов
 */

public class AutomataThread extends AsyncTask<Void, Void, Void> implements AutomataMoneyCalculator {
    private final String TAG = "AUTOMATA_THREAD";
    Storage storage;
    Context context;


    AutomataThread(Context context) {
        this.storage = new StateStorage(context);
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

        Shop<Automata> automationShop = new Shop<>(R.raw.automatas, context, new Automata.Factory());
        BigInteger sum = StreamSupport
                .stream(automationShop.shopItemArray)
                .map(Automata::getTotalPerformance)
                .reduce((s1, s2) -> s1.add(s2))
                .orElse(BigInteger.ZERO);

        return sum;
    }
}
