package ru.sibur.android.garbagecollector;

import java.lang.Math;
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
    public static final String SHOP_ITEM_PRICE_KEY = "price";
    public static final String SHOP_ITEM_COUNT_KEY = "count";
    public static final String SHOP_ITEM_ICON_ID_KEY = "icon_id";
    public static final String SHOP_ITEM_PERFORMANCE_KEY = "performance";
    public static final String[] SHOP_ITEM_ATTRIBUTES = {SHOP_ITEM_NAME_KEY, SHOP_ITEM_PRICE_KEY, SHOP_ITEM_COUNT_KEY, SHOP_ITEM_ICON_ID_KEY, SHOP_ITEM_PERFORMANCE_KEY};
    public static final int S_PREF_NUMERAL_SYSTEM = 10;

    public static final int[] SHOP_ITEMS_ICON_IDS = {R.drawable.shop_item_icon};

    public static final int[] SHOP_ITEM_VIEWS_ATTRS_IDS = {R.id.name, R.id.price, R.id.quantity, R.id.img, R.id.performance };

    public static final String[] MATH_ORDER_LETTERS = {"", "T", "M", "B", "R"};
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
        final BigDecimal floatAmount = new BigDecimal(amount);
        final BigDecimal money = floatAmount.divide(BigDecimal.valueOf(MONEY_DIVISOR));
        final BigDecimal threshold = BigDecimal.valueOf(100000);
        final BigDecimal step = BigDecimal.valueOf(1000);
        
        BigDecimal displayedMoney = money;
        int exp;
        for (exp = 0; exp < MATH_ORDER_LETTERS.length - 1; ++exp) {
            boolean fitsExpectedWidth = displayedMoney.compareTo(threshold) < 0;
            if (fitsExpectedWidth) {
                break;
            }
            displayedMoney = displayedMoney.divide(step);
        }

        DecimalFormat formatter = new DecimalFormat("0000");
        if (displayedMoney.compareTo(BigDecimal.valueOf(100)) < 0) {
            formatter.applyPattern("00.00");
        }

        return formatter.format(displayedMoney) + MATH_ORDER_LETTERS[exp];
    }

    public static BigInteger multiply(BigInteger bi, double multiplier) {
        int intMultiplier = Math.round(MONEY_DIVISOR * multiplier);
        BigInteger bigMultiplier = BigInteger.valueOf(intMultiplier);
        return bi.multiply(bigMultiplier).divide(BigInteger.valueOf(MONEY_DIVISOR));
    }

}
