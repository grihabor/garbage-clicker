package ru.sibur.android.garbagecollector;

/**
 * Класс автомата
 * 
 * Этот класс позволяет создавать новые виды автоматов
 */

public class Automata extends ShopItem {
    private int index;

    Automata(String name, int initialPrice, Storage storage, int automataIndex) {
        super(name, initialPrice, storage);
        this.index = automataIndex;
    }

    @Override
    int getPrice () {
        int count = storage.getShopItemCount(getCountKey());
        int outPrice = initialPrice;

        for (int i = 0; i < count; i++) {
            outPrice *= 1.15;
        }

        return outPrice;
    }

    @Override
    String getCountKey() {
        return Constant.automataCountKey(index);
    }
}
