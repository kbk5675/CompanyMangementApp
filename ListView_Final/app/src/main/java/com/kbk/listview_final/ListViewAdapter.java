package com.kbk.listview_final;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kbk.listview_final.ListViewItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> arrayList = new ArrayList<>();

    public ListViewAdapter() {

    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listviewitem,parent,false);
        }



        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView3);
        TextView text1 = (TextView) convertView.findViewById(R.id.textname);
        TextView text2 = (TextView) convertView.findViewById(R.id.textsubtitle);
        TextView text3 = (TextView) convertView.findViewById(R.id.textcomment);

        ListViewItem listViewItem = arrayList.get(position);

        imageView.setImageDrawable(listViewItem.getDrawable());
        text1.setText(listViewItem.getText1());
        text2.setText(listViewItem.getText2());
        text3.setText(listViewItem.getText3());

        return convertView;
    }
}
