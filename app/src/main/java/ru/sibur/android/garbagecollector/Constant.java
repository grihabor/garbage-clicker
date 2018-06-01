package ru.sibur.android.garbagecollector;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.stream.StreamSupport;

/**
 * Содержит константы, нужные нам в разных классах программы
 */

public final class Constant {
    public static final String MONEY_KEY = "money_key";
    public static final String PREF_NAME = "my_pref";
    public static final String LAST_UPDATE_NAME = "update";
    public static final String TOTAL_MONEY_EARNED_KEY = "total_money_earned_key";
    public static final BigInteger TIME_UNIT = BigInteger.valueOf(1000);

    public static final double UPGRADE_COST_INCREASE_MULTIPLIER = 1.30;
    public static final double AUTOMATA_COST_INCREASE_MULTIPLIER = 1.30;
    public static final double AUTOMATA_COST_DECREASE_MULTIPLIER = 0.85;
    public static final double UPGRADE_COST_DECREASE_MULTIPLIER = 0.85;

    public static final String SHOP_ITEM_NAME_KEY = "name";
    public static final String SHOP_ITEM_PRICE_KEY = "price";
    public static final String SHOP_ITEM_COUNT_KEY = "count";
    public static final String SHOP_ITEM_ICON_ID_KEY = "icon_id";
    public static final String SHOP_ITEM_PERFORMANCE_KEY = "performance";
    public static final String[] SHOP_ITEM_ATTRIBUTES = {SHOP_ITEM_NAME_KEY, SHOP_ITEM_PRICE_KEY, SHOP_ITEM_COUNT_KEY, SHOP_ITEM_ICON_ID_KEY, SHOP_ITEM_PERFORMANCE_KEY};

    public static final int[] SHOP_ITEMS_ICON_IDS = {R.drawable.shop_item_icon};

    public static final int[] SHOP_ITEM_VIEWS_ATTRS_IDS = {R.id.name, R.id.price, R.id.quantity, R.id.img, R.id.performance };
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


    public static String formatMoney (BigInteger amount) {
        /*String decimal = amount.toString(10);
        for (int i = 0; i < 3; i ++) {
            if (decimal.length() <= i) {
                decimal = "0" + decimal;
            }
        }

        StringBuilder builder = new StringBuilder(decimal);
        int signsThrown = Math.min(decimal.length() - 3, 11);
        if (decimal.length() <= 11) {
            if ((builder.length() % 3) == 0) builder.insert(1, ",");
            if ((builder.length() % 3) == 1) builder.insert(2, ",");
        }
        String format = builder.substring(0, decimal.length() - 1 - signsThrown);
        if (signsThrown >= 9) {
            format = format + "b";
        } else if (signsThrown >= 6) {
            format = format + "m";
        } else if (signsThrown >= 3) {
            format = format + "k";
        }

        return format;*/
        return amount.toString(10);

    }

    public static BigInteger multiply(BigInteger in, double multiplier) {
        BigInteger bigMultiplier = BigInteger.valueOf((long) (multiplier*100));
        return in.multiply(bigMultiplier).divide(BigInteger.valueOf(100));
    }

}
