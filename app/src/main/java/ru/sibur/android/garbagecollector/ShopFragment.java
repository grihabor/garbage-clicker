package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import java9.util.stream.Collectors;
import java9.util.stream.IntStream;
import java9.util.stream.StreamSupport;

/**
 * Базовый фрагмент для фрагментов магазинов
 */

public class ShopFragment extends Fragment {
    Storage storage;
    ArrayList<HashMap<String, Object>> viewDataArray;

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        storage = ((MainActivity) activity).storage;
    }

    public SimpleAdapter getListViewAdapter(Context context, ArrayList<? extends ShopItem> shopItems) {
        viewDataArray = StreamSupport.stream(shopItems)
                                     .map(ShopItem::getViewData)
                                     .collect(Collectors.toCollection(ArrayList::new));


        SimpleAdapter adapter = new SimpleAdapter(context, viewDataArray, R.layout.fragment_list_view,
                new String[]{"Name", "Price", "Qty",
                        "Img"},
                new int[]{R.id.name, R.id.price, R.id.qty, R.id.img });

        IntStream.range(0, shopItems.size()).forEach(i -> {
            ShopItem item = shopItems.get(i);
            item.setOnCountChangeListener(() -> {
                viewDataArray.set(i, item.getViewData());
                adapter.notifyDataSetChanged();
            });
        });

        return adapter;
    }

    protected void initListView(final Context context, final ArrayList<? extends ShopItem> items, int listViewId) {
        viewDataArray = StreamSupport.stream(items)
                .map(ShopItem::getViewData)
                .collect(Collectors.toCollection(ArrayList::new));

        ListView listView = getView().findViewById(listViewId);
        SimpleAdapter adapter = getListViewAdapter(context,items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, itemClicked, position, id) -> {
            ShopItem current = items.get(position);
            current.tryToBuy(context, storage);
        });
    }
}
