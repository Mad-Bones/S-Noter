package com.bsn.s_notera.tools.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bsn.s_notera.R;
import com.bsn.s_notera.constanst.ConstAppMode;
import com.bsn.s_notera.preferences.PreferencesAdapter;

public class AddDialogButtonsA {
    Activity myActivity;
    LinearLayout containerDialog;
    AlertDialog alertDialog;

    /**2 BUTTON*/
    public AddDialogButtonsA(Activity myActivity, AlertDialog alertDialog,LinearLayout containerDialog) {
        this.myActivity = myActivity;
        this.alertDialog = alertDialog;
        this.containerDialog = containerDialog;

        createButtonsEXITAPP();
    }
    private void createButtonsEXITAPP() {
        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout button_object = inflater.inflate(R.layout.component_double_button, null).findViewById(R.id.button_double_text);

        TextView textButtonA = button_object.findViewById(R.id.button_texta);
        textButtonA.setText("Cancelar");
        textButtonA.setOnClickListener(view -> {
            dismissDialog();
        });
        TextView textButtonB = button_object.findViewById(R.id.button_textb);
        textButtonB.setText("Salir");
        textButtonB.setOnClickListener(view -> {
            String mode = new PreferencesAdapter(myActivity).inMode();
            dismissDialog();
            myActivity.finish();
        });
        containerDialog.addView(button_object);
    }
    private void dismissDialog(){
        if(alertDialog!=null){
            alertDialog.dismiss();
        }
    }

}
