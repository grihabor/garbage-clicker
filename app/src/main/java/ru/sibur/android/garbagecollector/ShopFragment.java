package ru.sibur.android.garbagecollector;

import android.app.ListFragment;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.SparseIntArray;
import android.view.View;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;

import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import java9.util.stream.IntStream;

import static ru.sibur.android.garbagecollector.Constant.SOUND_QUALITY;

/**
 * Базовый фрагмент для фрагментов магазинов
 */

public class ShopFragment extends ListFragment {

    private Shop shop;
    private SoundPool soundPool;
    SparseIntArray soundMap;

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
        initializeSoundEffects(shop.context);
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
            if ( (soundPool != null) && shop.storage.getSoundsShouldBe()) soundPool.play(Constant.SOUND_ONE, Constant.volume, Constant.volume, Constant.PRIORITY, Constant.loop, Constant.rate);

            trans.startTransition(300);
        } else {
            ColorDrawable[] color = {
                    new ColorDrawable(Color.RED),
                    new ColorDrawable(Color.WHITE)
            };
            TransitionDrawable trans = new TransitionDrawable(color);
            if ( (soundPool != null) && shop.storage.getSoundsShouldBe()) soundPool.play(Constant.SOUND_TWO, Constant.volume, Constant.volume, Constant.PRIORITY, Constant.loop, Constant.rate);
            v.setBackground(trans);
            trans.startTransition(300);
        }
    }
    public void initializeSoundEffects(Context context) {
        soundPool = new SoundPool(Constant.MAX_STREAMS, AudioManager.STREAM_MUSIC, Constant.SOUND_QUALITY);
        if((android.os.Build.VERSION.SDK_INT) == 21){
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).
                    setMaxStreams(Constant.MAX_STREAMS).build();
        }
        else {
            soundPool = new SoundPool(Constant.MAX_STREAMS, AudioManager.STREAM_MUSIC, SOUND_QUALITY);
        }

        soundMap = new SparseIntArray(); // быстрый вариант HashMap для андроид
        soundMap.put(Constant.SOUND_ONE, soundPool.load(context, R.raw.coin, Constant.PRIORITY));
        soundMap.put(Constant.SOUND_TWO, soundPool.load(context, R.raw.error, Constant.PRIORITY));
    }

}
