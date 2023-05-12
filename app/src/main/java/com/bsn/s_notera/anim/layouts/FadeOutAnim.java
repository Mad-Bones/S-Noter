package com.bsn.s_notera.anim.layouts;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bsn.s_notera.R;

/**DESAPARECER ANIMATION*/
public class FadeOutAnim {
    public FadeOutAnim(Activity actividad, View views) {
        Animation animation1 = AnimationUtils.loadAnimation(actividad.getApplicationContext(), R.anim.fade_out_short);
        views.startAnimation(animation1);
        views.setVisibility(View.GONE);
    }
}
