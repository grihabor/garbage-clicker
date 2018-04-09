package ru.sibur.android.garbagecollector;

import android.widget.ImageView;

/**
 * Created by GlAI17 on 26.03.2018.
 */
public class Item {
    /**
     * Заголовок
     */
    String header;

    /**
     * Подзаголовок
     */
    String subHeader;

    /**
     * Картинка
     */

    ImageView img;


    /**
     * Конструктор создает новый элемент в соответствии с передаваемыми
     * параметрами:
     * @param header - заголовок элемента
     * @param subheader - подзаголовок
     * @param Iview - картинка
     */
    Item(String header, String subheader, ImageView Iview){
        this.header=header;
        this.subHeader=subheader;
        this.img = Iview;
    }

    //Всякие гетеры и сеттеры
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    public String getSubHeader() {
        return subHeader;
    }
    public void setSubHeader(String subHeader) {
        this.subHeader = subHeader;
    }

}