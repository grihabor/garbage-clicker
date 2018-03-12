package ru.sibur.android.garbagecollector;

import android.app.Fragment;
import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 12.03.2018.
 */

public class ShopFragment extends Fragment {
    public void getListViewView(Context context, ArrayList<ShopItem> itemArray, ListView listView){
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for (int i = 0; i < itemArray.size(); i++){
            arrayList.add(itemArray.get(i).getViewData());
        }

        SimpleAdapter adapter = new SimpleAdapter(context, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"Name", "Price"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }
}
