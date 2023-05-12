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
import com.bsn.s_notera.constanst.ConstNoterRoutes;
import com.bsn.s_notera.pages.ActivityNotesList;
import com.bsn.s_notera.pages.database.NotasHelper;
import com.bsn.s_notera.pages.database.Objeto;
import com.bsn.s_notera.preferences.PreferencesAdapter;

import java.util.ArrayList;

public class AddDialogButtonsEraseList {
    Activity myActivity;
    LinearLayout containerDialog;
    AlertDialog alertDialog;
    ArrayList <Objeto> arrayList;

    /**2 BUTTON*/
    public AddDialogButtonsEraseList(Activity myActivity, AlertDialog alertDialog, LinearLayout containerDialog, ArrayList<Objeto> listArray) {
        this.myActivity = myActivity;
        this.alertDialog = alertDialog;
        this.containerDialog = containerDialog;
        this.arrayList = listArray;

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
        textButtonB.setText("Borrar");
        textButtonB.setOnClickListener(view -> {
            new NotasHelper(myActivity).BorrarNotas(arrayList);
            new PreferencesAdapter(myActivity).setprimaryRoute(ConstNoterRoutes.RouteMain);
            myActivity.startActivity(new Intent(myActivity, ActivityNotesList.class));
            myActivity.finish();
            dismissDialog();
        });
        containerDialog.addView(button_object);
    }
    private void dismissDialog(){
        if(alertDialog!=null){
            alertDialog.dismiss();
        }
    }

}
