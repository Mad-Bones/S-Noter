package com.bsn.s_notera.tools.text;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bsn.s_notera.R;

/** AGREGAR
 * TEXTO
 * EMPTY BOX
 * */
public class AddTextSimple {
    Activity actividad;
    LinearLayout text_container;

    public AddTextSimple(Activity actividad, String text, LinearLayout container) {
        this.actividad = actividad;
        this.text_container = container;

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView texts = inflater.inflate(R.layout.component_text_simple, null).findViewById(R.id.text_simple);
        texts.setText(text);

        text_container.addView(texts);
    }

}
