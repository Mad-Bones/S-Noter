package com.bsn.s_notera.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bsn.s_notera.R;
import com.bsn.s_notera.tools.dialogs.AddDialogButtonsA;
import com.bsn.s_notera.tools.dialogs.AddDialogButtonsEraseList;
import com.bsn.s_notera.tools.dialogs.AddDialogTextTiped;
import com.bsn.s_notera.tools.dialogs.AddDialogTextTitle;

import java.util.ArrayList;

/** DIALOGO SALIR */
public class DialogDeleteList {
    @SuppressLint("StaticFieldLeak")
    public static class show extends AsyncTask<Void, Void, Void> {
        Activity activity;
        AlertDialog dialogoAlert;
        public show(Activity activity, ArrayList listArray) {
            this.activity = activity;

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                final View view = LayoutInflater.from(activity).inflate(R.layout.container_dialog,(ViewGroup)activity.findViewById(R.id.container_dialog));
                builder.setView(view);
                dialogoAlert = builder.create();
                LinearLayout container = view.findViewById(R.id.container_dialog);

                new AddDialogTextTitle(activity,"Eliminar todo",container);
                new AddDialogTextTiped(activity,"¿ Desea eliminar el contenido de la papelera permanentemente ?",container);

                new AddDialogButtonsEraseList(activity,dialogoAlert,container,listArray);
                dialogoAlert.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            if(activity.isDestroyed()){
            }
            return null;
        }
    }
}
