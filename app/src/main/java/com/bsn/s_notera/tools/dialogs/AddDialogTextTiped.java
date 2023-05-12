package com.bsn.s_notera.tools.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.bsn.s_notera.R;
import com.bsn.s_notera.anim.text.Typewriter;

/** DIALOG AGREGAR CAJA DE TEXTO ANIMADO */
public class AddDialogTextTiped {
    Activity actividad;
    Typewriter text_content;
    LinearLayout sup_container;

    public AddDialogTextTiped(Activity actividad, String content,LinearLayout container) {
        this.actividad = actividad;

        this.sup_container = container;

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout tiped_text = inflater.inflate(R.layout.component_text_typed_long, null).findViewById(R.id.text_tiped_component_long);
        this.text_content = tiped_text.findViewById(R.id.content_typed_text);
        text_content.setCharacterDelay(50);
        text_content.animateText(content);

        sup_container.addView(tiped_text);
    }

}
