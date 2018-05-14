package ru.sibur.android.garbagecollector;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Содержит константы, нужные нам в разных классах программы
 */

public final class Constant {
    public static final String MONEY_KEY = "money_key";
    public static final String PREF_NAME = "my_pref";
    public static final String LAST_UPDATE_NAME = "update";
    public static final int TIME_UNIT = 1000;
    public static final String TOTAL_MONEY_EARNED_KEY = "total_money_earned_key";

    public static String automataCountKey (int index) {
        return "automata:" + index;
    }

    public static String upgradeCountKey (int index) {
        return "upgrade:" + index;
    }

    public static String formatMoney (int amount) {
        float val = amount * 0.01f;
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(val);
    }

}
