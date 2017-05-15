package com.example.edgy.designmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by EDGY on 4/25/2017.
 */

public class AdapterMenu extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> list;
    private TextView textView;

    public AdapterMenu(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.new_training, null, false);
        textView = (TextView) view.findViewById(R.id.tv_text);
        textView.setText(list.get(i));
        return view;
    }
}
