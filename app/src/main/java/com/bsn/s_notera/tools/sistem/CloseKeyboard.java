package com.bsn.s_notera.tools.sistem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.inputmethod.InputMethodManager;

public class CloseKeyboard {
    public static class Close extends AsyncTask<Void, Void, Void> {
        @SuppressLint("StaticFieldLeak")
        Activity actividad;

        public Close(Activity actividad) {
            this.actividad = actividad;
        }

        @Override
        protected Void doInBackground(Void... params) {
            InputMethodManager inputManager = (InputMethodManager)actividad.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(actividad.getCurrentFocus() != null) {
                inputManager.hideSoftInputFromWindow(actividad.getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
            return null;
        }

    }
}
