package com.bsn.s_notera.tools.bars;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.bsn.s_notera.R;

public class AddEmptyBarContainer {
    Activity myActivity;
    LinearLayout sup_container;

    public LinearLayout BoxContainer(Activity myActivity,LinearLayout container){
        this.myActivity = myActivity;
        this.sup_container = container;
        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout empty_box = inflater.inflate(R.layout.component_color_bar, null).findViewById(R.id.container_empty_bar);
        LinearLayout returned = empty_box.findViewById(R.id.linear_bar);
        sup_container.addView(empty_box);
        return returned;
    }
}

