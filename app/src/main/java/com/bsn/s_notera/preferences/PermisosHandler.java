package com.bsn.s_notera.preferences;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.bsn.s_notera.R;

public class PermisosHandler {
    Activity activity;
    private static final String TAG = "PermisosHandler";
    final private static int REQUEST_NOTIFICACINES = 0;
    final private static int REQUEST_ALMACENAMIENTO = 1;

    private boolean almacenamiento_denied;
    private long almacenamiento_denied_time_ms;
    private boolean notificaciones_denied;
    private long notificaciones_denied_time_ms;

    final private static long deny_delay_ms = 1000;

    public PermisosHandler(Activity activity) {
        this.activity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void showRequestPermissionRationale(final int permission_code) {

        boolean ok = true;
        String [] permissions = null;
        int message_id = 0;
        switch (permission_code) {
            case REQUEST_ALMACENAMIENTO:
                Log.d(TAG, "PERMISOS ALMACENAMIENTO START");
                permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                message_id = R.string.estritura_string;//simple string como int
                break;
            case REQUEST_NOTIFICACINES:
                Log.d(TAG, "PERMISOS NOTIFICACIONES START");
                permissions = new String[]{Manifest.permission.POST_NOTIFICATIONS};
                message_id = R.string.notifica_string;//simple string como int
                break;
            default:
                Log.e(TAG, "PERMISOS DEFAULT" + permission_code);
                ok = false;
                break;
        }

        if(ok) {
            final String [] permissions_f = permissions;
            new AlertDialog.Builder(activity)
                    .setTitle("Esta app requiere este permiso")
                    .setMessage(message_id)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.ok, null)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        public void onDismiss(DialogInterface dialog) {
                            Log.d(TAG, "PIDIENDO PERMISO");
                            ActivityCompat.requestPermissions(activity, permissions_f, permission_code);
                        }
                    }).show();
        }
    }

    public void requestAlmacenamientoPermisos() {
        Log.d(TAG, "PERMISOS INICIAR PEDIDO ALMACENAMIENTO");

        if( almacenamiento_denied && System.currentTimeMillis() < almacenamiento_denied_time_ms + deny_delay_ms ) {
            Log.d(TAG, "APARENTEMENTE NO ES TIEMPO DE VOLVER A PEDIR EL PERMISO, REINSTALA LA APP");
            return;
        }

        if( ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) ) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                showRequestPermissionRationale(REQUEST_ALMACENAMIENTO);
            }
        }
        else {
            Log.d(TAG, "PERMISOS INICIANDO PEDIDO DE PERMISOS");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_ALMACENAMIENTO);
        }
    }

    public void requestNotificacionesPermisos() {
        Log.d(TAG, "PERMISOS INICIAR PEDIDO NOTIFICACIONES");
        if( notificaciones_denied && System.currentTimeMillis() < notificaciones_denied_time_ms + deny_delay_ms ) {
            Log.d(TAG, "APARENTEMENTE NO ES TIEMPO DE VOLVER A PEDIR EL PERMISO, REINSTALA LA APP");
            return;
        }

        if( ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.POST_NOTIFICATIONS) ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                showRequestPermissionRationale(REQUEST_NOTIFICACINES);
            }
        }
        else {
            Log.d(TAG, "PERMISOS INICIANDO PEDIDO DE PERMISOS");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_NOTIFICACINES);
        }
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: requestCode " + requestCode);
        switch( requestCode ) {
            case REQUEST_ALMACENAMIENTO:
            {
                if( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    Log.d(TAG, "PERMISO ALMACENAMIENTO CONDEDIDO");
                }
                else {
                    Log.d(TAG, "PERMISO ALMACENAMIENTO DENEGADO");
                    almacenamiento_denied = true;
                    almacenamiento_denied_time_ms = System.currentTimeMillis();
                }
                return;
            }
            case REQUEST_NOTIFICACINES:
            {
                if( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    Log.d(TAG, "PERMISO NOTIFICACION CONDEDIDO");
                }
                else {
                    Log.d(TAG, "PERMISO NOTIFICACION DENEGADO");
                    notificaciones_denied = true;
                    notificaciones_denied_time_ms = System.currentTimeMillis();
                }
                return;
            }
            default:
            {
                 Log.e(TAG, "PERMISO DFAULT REQUEST" + requestCode);
            }
        }
    }
}
