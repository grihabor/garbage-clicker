package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import java.util.ArrayList;
import java.util.HashMap;

import java9.util.stream.IntStream;

/**
 * Базовый фрагмент для фрагментов магазинов
 */

public class ShopFragment extends ListFragment {

    private final String TAG = "SHOP_FRAGMENT";
    private Shop shop;


    void setShop(Shop shop) {
        this.shop = shop;
    }


    @Override
    public void onStart() {
        super.onStart();
        initListView();
    }

    public SimpleAdapter getListViewAdapter() {

        ArrayList<HashMap<String, Object>> viewDataArray = shop.getViewDataArray();

        SimpleAdapter adapter = new SimpleAdapter(shop.context, viewDataArray, R.layout.shop_item_view,
                shop.shopItemAttributes,
                shop.shopItemAttrIds);

        IntStream.range(0, shop.shopItemArray.size()).forEach(i -> {
            ShopItem item = shop.shopItemArray.get(i);
            item.setOnCountChangeListener(() -> {
                viewDataArray.set(i, item.getViewData());
                adapter.notifyDataSetChanged();
            });
        });

        return adapter;
    }

    protected void initListView() {
        SimpleAdapter adapter = getListViewAdapter();
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ShopItem current = shop.shopItemArray.get(position);
        current.tryToBuy(shop.context, shop.storage);
    }

}
