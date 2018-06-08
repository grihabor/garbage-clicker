package ru.sibur.android.garbagecollector;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ListFragment;
import android.view.View;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;

import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
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

        SimpleAdapter adapter = new SimpleAdapter(shop.context, viewDataArray, shop.itemLayoutId,
                shop.shopItemAttributes,
                shop.shopItemAttrIds);

        IntStream.range(0, shop.shopItemArray.size()).forEach(i -> {
            ShopItem item = shop.shopItemArray.get(i);
            item.setOnCountChangeListener(() -> {
                viewDataArray.set(i, item.getViewData(getActivity()));
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
        ValueAnimator animator;
        if(current.tryToBuy(shop.context, shop.storage)) {
            animator = (ValueAnimator) AnimatorInflater.loadAnimator(this.getActivity(), R.animator.buying_success);
        } else {
            animator = (ValueAnimator) AnimatorInflater.loadAnimator(this.getActivity(), R.animator.buying_fail);
        }

        animator.addUpdateListener((animation) -> {
            int color = (int) animation.getAnimatedValue();
            v.setBackgroundColor(color);
        });

        animator.start();
    }

}
