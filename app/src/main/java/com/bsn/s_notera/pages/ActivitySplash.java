package com.bsn.s_notera.pages;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.bsn.s_notera.R;
import com.bsn.s_notera.preferences.PermisosHandler;
import com.bsn.s_notera.preferences.PreferencesAdapter;

public class ActivitySplash extends AppCompatActivity {

    Handler handlerfin;
    Intent intenta;
    PermisosHandler permisosHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);
        handlerfin = new Handler();
        permisosHandler = new PermisosHandler(this);

        intenta=new Intent(ActivitySplash.this, ActivityNotesList.class);
        askStoragePermission();
    }
    private void checkFirstTime(){
        if(new PreferencesAdapter(this).fistTime()){
            handlerfin.postDelayed(() ->
            {
                new PreferencesAdapter(this).setfistTime(false);
                startActivity(intenta);
                finish();
            },700);
        }else {
            startActivity(intenta);
            finish();
        }
    }

    private void askStoragePermission(){
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            permisosHandler.requestAlmacenamientoPermisos();
            askStoragePermission();
            return;
        }else{
            checkFirstTime();
        }
    }
    public PermisosHandler getPermissionHandlerPer() {
        return permisosHandler;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("TAG", "onRequestPermissionsResult: requestCode " + requestCode);
        permisosHandler.onRequestPermissionsResult(requestCode, grantResults);
    }

}