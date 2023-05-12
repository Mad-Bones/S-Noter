package com.bsn.s_notera.tools.sistem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

public class ShareText {
    public static class Share extends AsyncTask<Void, Void, Void> {
        @SuppressLint("StaticFieldLeak")
        Activity actividad;
        String text;

        public Share(Activity actividad,String text ) {
            this.actividad = actividad;
            this.text = text;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String shareBody = text;
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            actividad.startActivity(Intent.createChooser(intent, "Compartir con..."));
            return null;
        }

    }
}
