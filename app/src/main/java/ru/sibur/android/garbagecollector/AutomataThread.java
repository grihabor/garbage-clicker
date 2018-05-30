package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java9.util.stream.IntStream;


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
        JSONLoader loader = new JSONLoader(context);
        JSONArray automataArray = loader.parceJSONResource(R.raw.automatas);
        if(automataArray == null){
            Log.e(TAG, "Unable to load data from automatas.json");
            return 0;
        }

        int totalMoneyPerTimeUnit = IntStream
                                    .range(0, automataArray.length())
                                    .map(i -> storage.getShopItemCount(Constant.automataCountKey(i)) * Constant.automataPerformance(i))
                                    .sum();
        return totalMoneyPerTimeUnit;
    }
}
