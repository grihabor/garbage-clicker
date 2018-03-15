package ru.sibur.android.garbagecollector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class AutomationShopFragment extends ShopFragment {
    OnMoneyUpdateListener listener = null;
    
    @Override
    public void onAttach (Activity activity)
    {
        super.onAttach(activity);
        listener = (OnMoneyUpdateListener) activity;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
        return inflater.inflate(R.layout.activity_automation_shop_fragment, null);
    }

    public void onStart(){
        super.onStart();
        initListView(getActivity());
    }

    private void initListView(final Context context) {
        final ArrayList <Automata> automataArray = getAutomataList();
        ListView listView = getView().findViewById(R.id.automationShopListView);
        SimpleAdapter adapter = getListViewAdapter(context, automataArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Automata current = automataArray.get(position);
                current.tryToBuy(context, listener);
            }
        });
    }
    private ArrayList<Automata> getAutomataList(){
        ArrayList <Automata> automataArray = new ArrayList<>();
        String[] stringsArray = getResources().getStringArray(R.array.automata_array);
        for (int i = 0; i < stringsArray.length; i++){
            automataArray.add(new Automata(stringsArray[i], 10*(i+1) ) );
        }
        return automataArray;
    }
}
