package com.bsn.s_notera.tools.containers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.bsn.s_notera.R;

public class AddEmptyBoxContainerSup {
    Activity myActivity;
    LinearLayout inferior_container;

    public LinearLayout BoxContainer(Activity myActivity){
        this.myActivity = myActivity;
        this.inferior_container = (myActivity).findViewById(R.id.container_superior);
        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout empty_box = inflater.inflate(R.layout.container_box, null).findViewById(R.id.box_container);
        inferior_container.addView(empty_box);
        return empty_box;
    }
}
