package ru.sibur.android.garbagecollector;

import android.content.Context;

import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;

import java9.util.stream.StreamSupport;

public class AutomationShop extends Shop {
    @Override
    String getTag() {
        return "AUTOMATION_SHOP";
    }

    AutomationShop(int resourceId, Context context, Storage storage) {
        super(resourceId, context, storage);
    }

    @Override
    public ShopItem createInstance(JSONObject object, int iterationIndex) {
        return new Automata(object, storage, iterationIndex);
    }

    public BigInteger getTotalPerformance() {
        ArrayList<Automata> automatas = (ArrayList<Automata>) shopItemArray;
        BigInteger sum = StreamSupport
                .stream(automatas)
                .map(Automata::getTotalPerformance)
                .reduce((s1, s2) -> s1.add(s2))
                .orElse(BigInteger.ZERO);

        return sum;
    }
}
