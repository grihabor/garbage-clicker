package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.app.Fragment;
import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java9.util.function.Function;
import java9.util.stream.IntStream;
import java9.util.stream.Collectors;
import java9.util.stream.StreamSupport;

/**
 * Базовый фрагмент для фрагментов магазинов
 */

abstract public class ShopFragment extends Fragment implements Function<JSONObject, ShopItem> {
    Storage storage;
    int iterationIndex;

    private final String TAG = "SHOP_FRAGMENT";

    abstract int getResourceId();
    abstract int getListViewId();

    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        storage = ((MainActivity) activity).storage;
        iterationIndex = 0;
    }

    @Override
    public void onStart() {
        super.onStart();
        initListView(getActivity(), getShopItemList(), getListViewId());
    }

    public SimpleAdapter getListViewAdapter(Context context, ArrayList<? extends ShopItem> shopItems) {
        ArrayList<HashMap<String, Object>> viewDataArray = StreamSupport
                .stream(shopItems)
                .map(ShopItem::getViewData)
                .collect(Collectors.toCollection(ArrayList::new));


        SimpleAdapter adapter = new SimpleAdapter(context, viewDataArray, R.layout.shop_item_view,
                Constant.SHOP_ITEM_ATTRIBUTES,
                Constant.SHOP_ITEM_VIEWS_ATTRS_IDS);

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
        ListView listView = getView().findViewById(listViewId);
        SimpleAdapter adapter = getListViewAdapter(context, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, itemClicked, position, id) -> {
            ShopItem current = items.get(position);
            if( current.tryToBuy(context, storage) == true) {
                ColorDrawable[] color = {
                        new ColorDrawable(Color.GREEN),
                        new ColorDrawable(Color.WHITE)
                };
                TransitionDrawable trans = new TransitionDrawable(color);
                itemClicked.setBackground(trans);
                trans.startTransition(300);
            } else {
                ColorDrawable[] color = {
                        new ColorDrawable(Color.RED),
                        new ColorDrawable(Color.WHITE)
                };
                TransitionDrawable trans = new TransitionDrawable(color);
                itemClicked.setBackground(trans);
                trans.startTransition(300);
            }
        });
    }

    private ArrayList<? extends ShopItem> getShopItemList () {
        JSONLoader loader = new JSONLoader(getActivity());
        JSONArray jsonarray = loader.parceJSONResource(getResourceId());

        ArrayList<? extends ShopItem> shopItemAttrArray = null;

        if(jsonarray != null) {
            shopItemAttrArray = IntStream.range(0, jsonarray.length()).mapToObj(i -> {
                iterationIndex = i;
                ShopItem ret = null;
                try {
                    ret = this.apply ((JSONObject) jsonarray.get(i));
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException: " + e.getMessage());
                }

                return ret;
            }).collect(Collectors.toCollection(ArrayList::new));


        } else {
            Log.e(TAG, "Unable to load data from automatas.json");
        }
        return shopItemAttrArray;
    }



}
