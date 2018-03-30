package ru.sibur.android.garbagecollector;

/**
 * Содержит константы, нужные нам в разных классах программы
 */

public final class Constant {
    public static final String MONEY_KEY = "money_key";
    public static final String PREF_NAME = "my_pref";
    public static final float MONEY_DISPLAY_COEFFICIENT = 0.01f;
    public static final String LAST_UPDATE_NAME = "update";
    public static final int TIME_UNIT = 1000;

    public static String automataCountKey (int index) {
        return "automata:" + index;
    }

    public static String upgradeCountKey (int index) {
        return "upgrade:" + index;
    }

}