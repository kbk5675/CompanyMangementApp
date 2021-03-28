package com.kbk.listview_final;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable drawable;
    private String text1;
    private String text2;
    private String text3;

    public Drawable getDrawable() {
        return drawable;
    }
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getText1() {
        return text1;
    }
    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }
    public void setText2(String text2) {
        this.text2 = text2;
    }
    public String getText3() {
        return text3;
    }
    public void setText3(String text3) {
        this.text3 = text3;
    }
}
