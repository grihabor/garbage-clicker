package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Базовый фрагмент для фрагментов магазинов
 */

public class ShopFragment extends Fragment {
    OnMoneyUpdateListener listener;

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        listener = (OnMoneyUpdateListener) activity;
    }

    public SimpleAdapter getListViewAdapter(Context context, ArrayList<? extends ShopItem> shopItems) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for (int i = 0; i < shopItems.size(); i++) {
            arrayList.add(shopItems.get(i).getViewData());
        }

        SimpleAdapter adapter = new SimpleAdapter(context, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Price"},
                new int[]{android.R.id.text1, android.R.id.text2});
        return adapter;
    }

    protected void initListView(final Context context, final ArrayList<? extends ShopItem> items, int listViewId) {
        ListView listView = getView().findViewById(listViewId);
        SimpleAdapter adapter = getListViewAdapter(context, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                ShopItem current = items.get(position);
                current.tryToBuy(context, listener);
            }
        });
    }
}
