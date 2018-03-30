package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Базовый фрагмент для фрагментов магазинов
 */

public class ShopFragment extends Fragment {
    Storage storage;

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        storage = ((MainActivity) activity).storage;
    }

    public SimpleAdapter getListViewAdapter(Context context, ArrayList<? extends ShopItem> shopItems) {
        ArrayList<HashMap<String, String>> viewDataArray = new ArrayList<>();

        SimpleAdapter adapter = new SimpleAdapter(context, viewDataArray, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Price"},
                new int[]{android.R.id.text1, android.R.id.text2});

        for (int i = 0; i < shopItems.size(); i++) {
            ShopItem item = shopItems.get(i);
            viewDataArray.add(item.getViewData());

            int iCopy = i;
            item.setOnCountChangeListener(() -> {
                viewDataArray.set(iCopy, item.getViewData());
                adapter.notifyDataSetChanged();
            });
        }

        adapter.notifyDataSetChanged();

        return adapter;
    }

    protected void initListView(final Context context, final ArrayList<? extends ShopItem> items, int listViewId) {
        ListView listView = getView().findViewById(listViewId);
        SimpleAdapter adapter = getListViewAdapter(context, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, itemClicked, position, id) -> {
            ShopItem current = items.get(position);
            current.tryToBuy(context, storage);
        });
    }
}
