package com.bsn.s_notera.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bsn.s_notera.R;

import java.util.ArrayList;

public class AutoTextoAdapter extends ArrayAdapter<String> {
    private LayoutInflater mInflater = null;
    private Activity activity;

    public AutoTextoAdapter(Activity a, ArrayList<String> items) {
        super(a, 0, items);
        activity = a;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static class ViewHolder {

        public TextView title;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.item_autocomp,
                    parent, false);
            holder.title = (TextView) convertView
                    .findViewById(R.id.titulo_item_comp);
            convertView.setTag(holder);
            holder.title.setText(getItem(position).toString());
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
}