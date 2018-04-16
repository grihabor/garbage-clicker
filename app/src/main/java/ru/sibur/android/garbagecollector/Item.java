package ru.sibur.android.garbagecollector;

import android.widget.ImageView;

/**
 * Created by GlAI17 on 26.03.2018.
 */
public class Item {

    String header;

    String subHeader;

    ImageView img;


    /**
     * @param header - заголовок элемента
     * @param subheader - подзаголовок
     * @param Iview - картинка
     */
    Item(String header, String subheader, ImageView Iview){
        this.header=header;
        this.subHeader=subheader;
        this.img = Iview;
    }

}