package com.bsn.s_notera.tools.text;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bsn.s_notera.R;

/** AGREGAR
 * TITULO
 * SUBTITULO
 * EMPTY BOX
 * */
public class AddTextTitleSubtitle {
    Activity actividad;
    LinearLayout text_container;

    public AddTextTitleSubtitle(Activity actividad, String title,String sub,LinearLayout container) {
        this.actividad = actividad;
        this.text_container = container;

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout texts = inflater.inflate(R.layout.component_text_title_sub, null).findViewById(R.id.text_title_sub);
        TextView title_text = texts.findViewById(R.id.text_title);
        TextView sub_text = texts.findViewById(R.id.text_subtitle);
        title_text.setText(title);
        sub_text.setText(sub);

        text_container.addView(texts);
    }

}
