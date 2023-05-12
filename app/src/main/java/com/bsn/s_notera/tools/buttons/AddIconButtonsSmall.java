package com.bsn.s_notera.tools.buttons;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;

import com.bsn.s_notera.R;

public class AddIconButtonsSmall {
    Activity actividad;
    LinearLayout img_container;
    public ImageView Add(Activity actividad, LinearLayout container, @DrawableRes int icon,int color,@DrawableRes int background) {
        this.actividad = actividad;
        this.img_container = container;

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout container_ico = inflater.inflate(R.layout.component_button_image, null).findViewById(R.id.container_ic_bu);
        ImageView iconSmall = container_ico.findViewById(R.id.button_small);
        iconSmall.setImageResource(icon);
        iconSmall.setColorFilter(actividad.getColor(color));
        iconSmall.setBackgroundResource(background);

        img_container.addView(container_ico);
        return iconSmall;
    }
}
