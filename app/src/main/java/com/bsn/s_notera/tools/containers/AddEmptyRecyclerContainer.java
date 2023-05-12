package com.bsn.s_notera.tools.containers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bsn.s_notera.R;

/** RECICLER_VIEW */
public class AddEmptyRecyclerContainer {
    Activity myActivity;
    LinearLayout container;

    public RecyclerView Recicler(Activity myActivity, LinearLayout container) {
        this.myActivity = myActivity;
        this.container = container;
        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout basiccontainer = inflater.inflate(R.layout.component_recycler_view, null).findViewById(R.id.container_recicler_a);
        RecyclerView recyclerView = basiccontainer.findViewById(R.id.recycler_general);

        container.addView(basiccontainer);
        return  recyclerView;
    }

}
