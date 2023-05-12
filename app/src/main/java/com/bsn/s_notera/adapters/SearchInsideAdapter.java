package com.bsn.s_notera.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.EditText;

import com.bsn.s_notera.tools.sistem.CloseKeyboard;

public class SearchInsideAdapter {

    public static class SearchinLists extends AsyncTask<Void, Void, Void> {
        @SuppressLint("StaticFieldLeak")
        Activity actividad;
        @SuppressLint("StaticFieldLeak")
        EditText searchField;
        RecyclerMultipleAdapterList adapter;
        public SearchinLists(Activity actividad, RecyclerMultipleAdapterList adapter, EditText searchField) {
            this.actividad = actividad;
            this.adapter = adapter;
            this.searchField = searchField;
            this.adapter.getFilter().filter(this.searchField.getText().toString());

            new Handler().postDelayed(() -> {
                new CloseKeyboard.Close(actividad).execute();
            },200);
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

    }
    public static class SearchinTitles extends AsyncTask<Void, Void, Void> {
        @SuppressLint("StaticFieldLeak")
        Activity actividad;
        @SuppressLint("StaticFieldLeak")
        EditText searchField;
        RecyclerMultipleAdapterCompact adapter;
        public SearchinTitles(Activity actividad, RecyclerMultipleAdapterCompact adapter, EditText searchField) {
            this.actividad = actividad;
            this.adapter = adapter;
            this.searchField = searchField;
            this.adapter.getFilter().filter(this.searchField.getText().toString());

            new Handler().postDelayed(() -> {
                new CloseKeyboard.Close(actividad).execute();
            },200);
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

    }
}
