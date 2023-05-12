package com.bsn.s_notera.tools.containers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.bsn.s_notera.R;

public class AddEmptyBoxContainerHorizontal {
    Activity myActivity;
    LinearLayout button_container;

    public LinearLayout BoxContainer(Activity myActivity){
        this.myActivity = myActivity;
        this.button_container = (myActivity).findViewById(R.id.container_general);
        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout empty_box = inflater.inflate(R.layout.container_box_horizontal, null).findViewById(R.id.box_container);
        button_container.addView(empty_box);
        return empty_box;
    }
}
