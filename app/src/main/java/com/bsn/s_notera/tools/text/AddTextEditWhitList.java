package com.bsn.s_notera.tools.text;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bsn.s_notera.R;

import java.util.ArrayList;

/** AGREGAR
 * TEXTO
 * EMPTY BOX
 * */
public class AddTextEditWhitList {
    Activity actividad;
    LinearLayout text_container;

    public AutoCompleteTextView TextEditSimple(Activity actividad, String hint, LinearLayout container, ArrayList<String> tagsList) {
        this.actividad = actividad;
        this.text_container = container;

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout texts_cont = inflater.inflate(R.layout.component_edittext_autocomplete, null).findViewById(R.id.cont_auto_simple);
        AutoCompleteTextView texts = texts_cont.findViewById(R.id.edittext_simple);
        texts.setHint(hint);

        text_container.addView(texts_cont);
        return texts;
    }

}
