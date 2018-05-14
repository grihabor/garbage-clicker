package ru.sibur.android.garbagecollector;

/**
 * Класс автомата
 * 
 * Этот класс позволяет создавать новые виды автоматов
 */

public class Automata extends ShopItem {

    int index;
    int icon;

    private int index;

    Automata(String name, int basePrice, Storage storage, int automataIndex) {
        super(name, basePrice, storage);
        this.index = automataIndex;
        icon = this.img;
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
