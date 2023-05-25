package com.bsn.s_notera.tools.buttons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bsn.s_notera.R;

/** BOTON TEXTO */
public class AddSimpleTextButton {
    Activity myActivity;
    LinearLayout button_container;
    LinearLayout buttons_box;

    public TextView TextButton(Activity myActivity,String title, LinearLayout container) {
        this.myActivity = myActivity;
        this.button_container = container;
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );
        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout buttons_box = inflater.inflate(R.layout.component_button, null).findViewById(R.id.button_text_linear);
        buttons_box.setLayoutParams(param);
        TextView textButton = buttons_box.findViewById(R.id.button_text);
        textButton.setText(title);
        button_container.addView(buttons_box);
        return  textButton;
    }

}
