package com.example.edgy.designlayout;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by EDGY on 4/25/2017.
 */

public class AdapterDesign extends BaseAdapter{
    private Context context;
    private List<TextList> list;
    private LayoutInflater layoutInflater;
    private TextView textView;


    public AdapterDesign(Context context, List<TextList> list) {
        this.context = context;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.record_list_layout, null, false);
        textView = (TextView) view.findViewById(R.id.tv_record_name);
        textView.setText(list.get(i).toString());
        return null;
    }
}
