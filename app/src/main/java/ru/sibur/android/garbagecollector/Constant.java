package ru.sibur.android.garbagecollector;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.stream.StreamSupport;

/**
 * Содержит константы, нужные нам в разных классах программы
 */

public final class Constant {
    public static final String MONEY_KEY = "money_key";
    public static final String PREF_NAME = "my_pref";
    public static final String LAST_UPDATE_NAME = "update";
    public static final String TOTAL_MONEY_EARNED_KEY = "total_money_earned_key";
    public static final int TIME_UNIT = 1000;

    public static final double UPGRADE_COST_INCREASE_MULTIPLIER = 1.30;
    public static final double AUTOMATA_COST_INCREASE_MULTIPLIER = 1.30;
    public static final double AUTOMATA_COST_DECREASE_MULTIPLIER = 0.85;
    public static final double UPGRADE_COST_DECREASE_MULTIPLIER = 0.85;
    public static final double PERFORMANCE_INCREASE_MULTIPLIER = 1.15;
    public static final double CLICK_INCREASE_MULTIPLIER = 4;

    public static final String SHOP_ITEM_NAME_KEY = "name";
    public static final String SHOP_ITEM_PRICE_KEY = "base_price";
    public static final String SHOP_ITEM_COUNT_KEY = "key";
    public static final String SHOP_ITEM_ICON_ID_KEY = "icon_id";
    public static final String SHOP_ITEM_PERFORMANCE_KEY = "performance";
    public static final String[] SHOP_ITEM_ATTRIBUTES = {SHOP_ITEM_NAME_KEY, SHOP_ITEM_PRICE_KEY, SHOP_ITEM_COUNT_KEY, SHOP_ITEM_ICON_ID_KEY, SHOP_ITEM_PERFORMANCE_KEY};
    public static final int S_PREF_NUMERAL_SYSTEM = 10;

    public static final int[] SHOP_ITEMS_ICON_IDS = {R.drawable.shop_item_icon};

    public static final int[] SHOP_ITEM_VIEWS_ATTRS_IDS = {R.id.name, R.id.price, R.id.quantity, R.id.img, R.id.performance };

    public static final String[] MATH_ORDER_LETTERS = {"", "k", "m", "b", "t"};
    public static final int MONEY_DIVISOR = 100;
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
        BigDecimal bigDecimal = new BigDecimal(amount);
        bigDecimal = bigDecimal.divide(BigDecimal.valueOf(MONEY_DIVISOR));
        BigDecimal thousand = BigDecimal.valueOf(1000);
        int i;
        for (i = 0; i < MATH_ORDER_LETTERS.length - 1; i++) {
            boolean tooLarge = bigDecimal.compareTo(thousand) > 0;
            if (tooLarge) {
                bigDecimal = bigDecimal.divide(thousand);
            }
            else break;
        }

        DecimalFormat formatter = new DecimalFormat("000");
        if (bigDecimal.compareTo(BigDecimal.valueOf(100)) < 0) {
            formatter.applyPattern("00.0");
        }
        if (bigDecimal.compareTo(BigDecimal.valueOf(10)) < 0) {
            formatter.applyPattern("0.00");
        }

        return formatter.format(bigDecimal) + MATH_ORDER_LETTERS[i];

    }

    public static BigInteger multiply(BigInteger in, double multiplier) {
        BigInteger bigMultiplier = BigInteger.valueOf((long) (multiplier*100));
        return in.multiply(bigMultiplier).divide(BigInteger.valueOf(100));
    }

}
