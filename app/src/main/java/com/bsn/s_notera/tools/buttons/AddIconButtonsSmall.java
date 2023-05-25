package com.bsn.s_notera.tools.buttons;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );

        LayoutInflater inflater = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout container_ico = inflater.inflate(R.layout.component_button_image, null).findViewById(R.id.container_ic_bu);
        container_ico.setLayoutParams(param);

        ImageView iconSmall = container_ico.findViewById(R.id.button_small);
        iconSmall.setImageResource(icon);
        iconSmall.setColorFilter(actividad.getColor(color));
        iconSmall.setBackgroundResource(background);
        android.view.ViewGroup.LayoutParams layoutParams = iconSmall.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        iconSmall.setLayoutParams(layoutParams);

        img_container.addView(container_ico);
        return iconSmall;
    }
}
