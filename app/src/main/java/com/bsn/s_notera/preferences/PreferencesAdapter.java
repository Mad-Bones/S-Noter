package com.bsn.s_notera.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.bsn.s_notera.constanst.ConstAppVisuals;
import com.bsn.s_notera.constanst.ConstNoterRoutes;

/**PREFERENCES ADAPTER*/
public class PreferencesAdapter

{
    private static final String SESSION_NAME = "com.";
    private final SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor prefsEditor;

    @SuppressLint("CommitPrefEdits")
    public PreferencesAdapter(Context context)
    {
        mSharedPreferences = context.getSharedPreferences(SESSION_NAME, Context.MODE_PRIVATE);
        prefsEditor = mSharedPreferences.edit();
    }

    public boolean fistTime() {return mSharedPreferences.getBoolean("fistTime", true);}
    public void setfistTime(boolean fistTime) {
        prefsEditor = mSharedPreferences.edit();
        prefsEditor.putBoolean("fistTime", fistTime);
        prefsEditor.apply();
    }
    public int trackNumber() {return mSharedPreferences.getInt("trackNumber", 0);}
    public void setTrackNumber(int trackNumber) {
        prefsEditor = mSharedPreferences.edit();
        prefsEditor.putInt("trackNumber", trackNumber);
        prefsEditor.apply();
    }

    public boolean isPlaying() {return mSharedPreferences.getBoolean("isPlaying", false);}
    public void setisPlaying(boolean isPlaying) {
        prefsEditor = mSharedPreferences.edit();
        prefsEditor.putBoolean("isPlaying", isPlaying);
        prefsEditor.apply();
    }
    public String inMode() {return mSharedPreferences.getString("inMode", "main");}
    public void setinMode(String inMode) {
        prefsEditor = mSharedPreferences.edit();
        prefsEditor.putString("inMode", inMode);
        prefsEditor.apply();
    }
    public int viewTypeInt() {return mSharedPreferences.getInt("viewTypeInt", ConstAppVisuals.VistaLista);}
    public void setviewTypeInt(int viewTypeInt) {
        prefsEditor = mSharedPreferences.edit();
        prefsEditor.putInt("viewTypeInt", viewTypeInt);
        prefsEditor.apply();
    }
    public String primaryRoute() {return mSharedPreferences.getString("primaryRoute", ConstNoterRoutes.RouteMain);}
    public void setprimaryRoute(String primaryRoute) {
        prefsEditor = mSharedPreferences.edit();
        prefsEditor.putString("primaryRoute", primaryRoute);
        prefsEditor.apply();
    }

    public String titleSearch() {return mSharedPreferences.getString("titleSearch", "");}
    public void setTitleSearch(String titleSearch) {
        prefsEditor = mSharedPreferences.edit();
        prefsEditor.putString("titleSearch", titleSearch);
        prefsEditor.apply();
    }
}
