package ru.sibur.android.garbagecollector;


import android.content.Context;
import android.graphics.drawable.VectorDrawable;
import android.widget.ImageView;
import android.widget.Toast;

public class Upgrade {
    float price;
    String name;
    Upgrade(String nameIN, float priceIn){
        price = priceIn;
        name = nameIN;
    }

    void Execute (Context context) {
        Toast.makeText(context, "казнить нельзя помиловать", Toast.LENGTH_SHORT).show();
    }
}
