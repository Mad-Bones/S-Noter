package com.bsn.s_notera.tools.text;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bsn.s_notera.R;

public class AddTextSearchBar  {

    Activity actividad;
    LinearLayout text_container;
    public EditText Add(Activity actividad, String text, LinearLayout container) {
        this.actividad = actividad;
        this.text_container = container;

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout container_search = inflater.inflate(R.layout.component_edittext_search, null).findViewById(R.id.container_search);
        EditText texts = container_search.findViewById(R.id.search_field);
        if (!text.matches("")){
            texts.setText(text);
        }
        text_container.addView(container_search);
        return texts;
    }

}