package com.bsn.s_notera.pages;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bsn.s_notera.R;
import com.bsn.s_notera.preferences.PreferencesAdapter;

public class ActivitySplash extends AppCompatActivity {

    Handler handlerfin;
    Intent intenta;
    final private static int REQUEST_ALMACENAMIENTO = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);
        handlerfin = new Handler();

        intenta=new Intent(ActivitySplash.this, ActivityNotesList.class);
        if(!CheckStoragePermissions()){
            RequestStoragePermissions();
        }else{
            checkFirstTime();
        }

    }

    /** PEDIDO DE PERMISOS */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ALMACENAMIENTO:
                checkFirstTime();
                break;
        }
    }
    public boolean CheckStoragePermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    public void RequestStoragePermissions() {
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, REQUEST_ALMACENAMIENTO);
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


}