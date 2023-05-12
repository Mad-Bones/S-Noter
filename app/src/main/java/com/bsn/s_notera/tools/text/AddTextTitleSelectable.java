package com.bsn.s_notera.tools.text;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bsn.s_notera.R;

/** AGREGAR TEXTO TITULO CENTRAL */
public class AddTextTitleSelectable {
    Activity actividad;
    LinearLayout container;

    public AddTextTitleSelectable(Activity actividad, String title,int textColor,int textBackgr,LinearLayout container) {
        this.actividad = actividad;
        this.container = container;

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView title_text = inflater.inflate(R.layout.component_text_title, null).findViewById(R.id.text_title);
        title_text.setTextColor(actividad.getColor(textColor));
        title_text.setBackgroundColor(actividad.getColor(textBackgr));
        title_text.setText(title);


        container.addView(title_text);
    }

}
