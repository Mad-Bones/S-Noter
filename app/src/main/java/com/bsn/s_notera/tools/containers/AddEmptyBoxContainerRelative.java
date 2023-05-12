package com.bsn.s_notera.tools.containers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bsn.s_notera.R;

public class AddEmptyBoxContainerRelative {
    Activity myActivity;
    RelativeLayout mid_container;

    public LinearLayout BoxContainer(Activity myActivity){
        this.myActivity = myActivity;
        this.mid_container = (myActivity).findViewById(R.id.container_relative);
        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout empty_box = inflater.inflate(R.layout.container_box_middle, null).findViewById(R.id.box_container);
        mid_container.addView(empty_box);
        return empty_box;
    }
}
