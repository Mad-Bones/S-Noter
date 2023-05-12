package com.bsn.s_notera.anim.layouts;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bsn.s_notera.R;

/**APARECER ANIMATION*/
public class FadeInAnim {
    public FadeInAnim(Activity actividad, View views) {
        Animation animation1 = AnimationUtils.loadAnimation(actividad.getApplicationContext(), R.anim.fade_in_short);
        views.setVisibility(View.VISIBLE);
        views.startAnimation(animation1);
    }
}
