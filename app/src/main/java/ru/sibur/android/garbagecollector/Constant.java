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

    /**
     * @param automataIndex
     * Automata index in the list of automationShop (from 0)
     * @return
     * Key by which we can change automata count (how many times was the automata bought) via StateStorage object
     */
    public static String automataCountKey (int automataIndex) {
        return "automata:" + automataIndex;
    }

    /**
     * @param upgradeIndex
     * Upgrade index in the list of upgradeShop (from 0)
     * @return
     * Key by which we can change upgrade count (how many times was the upgrade bought) via StateStorage object
     */
    public static String upgradeCountKey (int upgradeIndex) {
        return "upgrade:" + upgradeIndex;
    }

    /**
     * @param automataIndex
     * Automata index in the list of automationShop (from 0)
     * @return
     * Money that the automata gives per time unit
     */
    public static int automataPerformance (int automataIndex) {
        return (automataIndex + 1)*10;
    }

    public static String formatMoney (int amount) {
        float val = amount * 0.01f;
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(val);
    }

}
