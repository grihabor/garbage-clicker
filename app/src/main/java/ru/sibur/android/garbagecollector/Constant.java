package ru.sibur.android.garbagecollector;

/**
 * класс содержит константы, нужные нам в разных классах программы
 */

public final class Constant {
    public static final String MONEY_KEY = "money_key";
    public static final String PREF_NAME = "my_pref";
    public static final float MONEY_DISPLAY_COEFFICIENT = 0.01f;
    public static final String LAST_UPDATE_NAME = "update";
    public static final int TIME_UNIT = 1000;

    private static String[] generateCountNames(int namesNum, String namePrefix) {
        String[] ret = new String[namesNum];

        for (int i = 0; i < namesNum; i++) {
            ret [i] = namePrefix + i;
        }

        return ret;
    }

    public static final int AUTOMATA_NUM = 3;
    public static final String[] AUTOMATA_COUNT_NAMES = generateCountNames(AUTOMATA_NUM, "Automata");

    public static final int UPGRADE_NUM = 5;
    public static final String[] UPGRADE_COUNT_NAMES = generateCountNames(UPGRADE_NUM, "Upgrade");

}
