package ru.sibur.android.garbagecollector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * Created by GlAI17 on 26.03.2018.
 */

public class ListViewAdapter extends BaseAdapter {

    ArrayList<? extends ShopItem> data = new ArrayList<ShopItem>();
    Context context;

    public ListViewAdapter(Context context, ArrayList<? extends ShopItem> arr) {
        if (arr != null) {
            data = arr;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int num) {
        // TODO Auto-generated method stub
        return data.get(num);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int i, View someView, ViewGroup arg2) {
        //Получение объекта inflater из контекста
        LayoutInflater inflater = LayoutInflater.from(context);
        //Если someView (View из ListView) вдруг оказался равен
        //null тогда мы загружаем его с помошью inflater
        if (someView == null) {
            someView = inflater.inflate(R.layout.custom_list_view, arg2, false);
        }
        //Обявляем наши вьюшки и связываем их с разметкой
        TextView header = (TextView) someView.findViewById(R.id.Header);
        TextView subHeader = (TextView) someView.findViewById(R.id.SubHeader);
        ImageView img = (ImageView) someView.findViewById(R.id.img);
        //Устанавливаем в каждую вьюшку соответствующий объект
        // сначала заголовок
        header.setText(data.get(i).name);
        // потом подзаголовок
        String t = valueOf(data.get(i).price/100);
        subHeader.setText(t);
        //и наконец картинку
        img.setImageResource((data.get(i).img));

        return someView;
    }

}