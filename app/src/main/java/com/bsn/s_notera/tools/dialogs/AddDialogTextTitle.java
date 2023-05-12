package com.bsn.s_notera.tools.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bsn.s_notera.R;

/** DIALOGOS AGREGAR TEXTO TITULO CENTRAL */
public class AddDialogTextTitle {
    Activity actividad;
    LinearLayout sup_container;

    public AddDialogTextTitle(Activity actividad, String title,LinearLayout container) {
        this.actividad = actividad;
        this.sup_container = container;

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView title_text = inflater.inflate(R.layout.component_text_title, null).findViewById(R.id.text_title);
        title_text.setText(title);

        sup_container.addView(title_text);
    }

}
