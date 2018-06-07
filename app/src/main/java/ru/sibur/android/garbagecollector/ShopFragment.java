package ru.sibur.android.garbagecollector;

import android.app.ListFragment;
import android.view.View;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;

import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import java9.util.stream.IntStream;

/**
 * Базовый фрагмент для фрагментов магазинов
 */

public class ShopFragment extends ListFragment {

    private Shop shop;


    void setShop(Shop shop) {
        this.shop = shop;
    }

    /*
    If you will need this fragment to be something beyond ListFragment,
     then extend Fragment and Override Fragment method onCreateView(LayoutInflater inflater)
     and make your own custom layout.
     */

    @Override
    public void onStart() {
        super.onStart();
        initListView();
    }

    public SimpleAdapter getListViewAdapter() {

        ArrayList<HashMap<String, Object>> viewDataArray = shop.getViewDataArray();

        SimpleAdapter adapter = new SimpleAdapter(shop.context, viewDataArray, R.layout.automata_item_view,
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
        if(current.tryToBuy(shop.context, shop.storage)) {
            ColorDrawable[] color = {
                    new ColorDrawable(Color.GREEN),
                    new ColorDrawable(Color.WHITE)
            };
            TransitionDrawable trans = new TransitionDrawable(color);
            v.setBackground(trans);
            trans.startTransition(300);
        } else {
            ColorDrawable[] color = {
                    new ColorDrawable(Color.RED),
                    new ColorDrawable(Color.WHITE)
            };
            TransitionDrawable trans = new TransitionDrawable(color);
            v.setBackground(trans);
            trans.startTransition(300);
        }
    }

}
