package ru.sibur.android.garbagecollector;

import java.util.HashMap;

/**
 * Класс автомата
 *
 * Этот класс позволяет создавать новые виды автоматов
 */

public class Automata extends ShopItem {
    private int index;

    Automata(String name, int basePrice, int iconId, Storage storage, int automataIndex) {
        super(name, basePrice, iconId, storage);
        this.index = automataIndex;
    }

    @Override
    public HashMap<String, Object> getViewData() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("Name", name);
        map.put("Price",getPrice());
        map.put("Img", R.drawable.item_icon);
        map.put("Qty", getCount());
        return (map);
    }

    @Override
    int getPrice () {
        int count = getCount();
        int price = basePrice;

        for (int i = 0; i < count; i++) {
            price *= 1.15;
        }

        return price;
    }

    @Override
    String getCountKey() {
        return Constant.automataCountKey(index);
    }
}
