package ru.sibur.android.garbagecollector;


import android.content.Context;
import android.graphics.drawable.VectorDrawable;
import android.widget.ImageView;
import android.widget.Toast;

public class Upgrade extends ShopItem {
    Upgrade(String nameIN, float priceIn){
       super(nameIN, priceIn);
    }

    void Apply (Context context) {
        Toast.makeText(context, "казнить нельзя помиловать", Toast.LENGTH_SHORT).show();
    }
}
