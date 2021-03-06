package ru.sibur.android.garbagecollector;

import java.lang.Math;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * Содержит константы, нужные нам в разных классах программы
 */

public final class Constant {

    public static final int SOUND_ONE = 1; // наименования звуков, начинать с единицы
    public static final int SOUND_TWO = 2;
    public static final int SOUND_QUALITY = 100; // уже не используется, можно ставить 0
    public static final int MAX_STREAMS = 4; // максимальное количество потоков звука, воспр. одновременно
    public static final int PRIORITY = 1; // приоритет потока (0 - самый низкий)
    public static final float volume = 1f; // громкость звукового эффекта
    public static final float rate = 1f; // скорость воспроизведения (от 0.5 до 2.0)
    public static final int loop = 0; // количество повторов 0 - без повторов, (-1) - зациклен

    public static final String MONEY_KEY = "money_key";
    public static final String PREF_NAME = "my_pref";
    public static final String LAST_UPDATE_NAME = "update";
    public static final String TOTAL_MONEY_EARNED_KEY = "total_money_earned_key";
    public static final String MUSIC_SHOULD_BE_KEY = "music_should_be";
    public static final String SOUNDS_SHOULD_BE_KEY = "sounds_should_be";
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

    public static final int[] SHOP_ITEM_VIEWS_ATTRS_IDS = {R.id.name, R.id.price, R.id.quantity, R.id.img, R.id.performance };

    public static final String[] MATH_ORDER_LETTERS = {"", "T", "M", "B", "R"};
    public static final int MONEY_DIVISOR = 100;

    public static String getDrawableResourceFullName (String shortName, String packageName) {
        return packageName + ":drawable/" + shortName;
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

        DecimalFormat formatter = new DecimalFormat("0");
        if (displayedMoney.compareTo(BigDecimal.valueOf(100)) < 0) {
            formatter.applyPattern("0.00");
        }

        return formatter.format(displayedMoney) + MATH_ORDER_LETTERS[exp];
    }

    public static BigInteger multiply(BigInteger bi, BigDecimal multiplier) {
        BigDecimal bd = new BigDecimal(bi);
        BigDecimal result = bd.multiply(multiplier);
        return result.toBigInteger();
    }
}
