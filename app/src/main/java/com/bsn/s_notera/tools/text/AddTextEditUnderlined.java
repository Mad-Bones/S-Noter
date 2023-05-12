package com.bsn.s_notera.tools.text;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bsn.s_notera.R;

/** AGREGAR
 * TEXTO
 * EMPTY BOX
 * */
public class AddTextEditUnderlined {
    Activity actividad;
    LinearLayout text_container;

    public EditTextUnderlined TextEditUnderlined(Activity actividad, String text,String hint, LinearLayout container) {
        this.actividad = actividad;
        this.text_container = container;

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout texts_container = inflater.inflate(R.layout.component_edittext_long, null).findViewById(R.id.edittext_container);
        EditTextUnderlined texts = texts_container.findViewById(R.id.edittext_long);
        texts.setHint(hint);
        texts.setText(text);

        text_container.addView(texts_container);
        return texts;
    }

}
