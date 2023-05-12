package com.bsn.s_notera.tools.text;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bsn.s_notera.R;

/** AGREGAR TEXTO TITULO CENTRAL */
public class AddTextTitle {
    Activity actividad;
    LinearLayout sup_container;

    public AddTextTitle(Activity actividad,String title) {
        this.actividad = actividad;
        this.sup_container = (actividad).findViewById(R.id.container_general);

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView title_text = inflater.inflate(R.layout.component_text_title, null).findViewById(R.id.text_title);
        title_text.setText(title);

        sup_container.addView(title_text);
    }

}
