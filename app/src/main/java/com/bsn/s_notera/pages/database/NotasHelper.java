package com.bsn.s_notera.pages.database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.bsn.s_notera.constanst.ConstNoterRoutes;
import com.bsn.s_notera.pages.ActivityNotesCreate;
import com.bsn.s_notera.pages.ActivityNotesList;
import com.bsn.s_notera.tools.lists.ListFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotasHelper {

        Activity activity;
        ArrayList<String> titlArray;
        ArrayList<Objeto> listArray;
        public NotasHelper(Activity activity){
                this.activity=activity;
                titlArray = new ArrayList<>();
                listArray = new ArrayList<>();
        }

        /** GUARDAR BORRAR */
        public void GuardarNota(Objeto objetoa, ActivityNotesCreate activity){
                @SuppressLint("StaticFieldLeak")
                class saveObje extends AsyncTask<Void, Void, Void> {
                        @Override
                        protected Void doInBackground(Void... voids) {
                                DataBaseNoter.getDatabase(activity).objetosDao().insertObjeto(objetoa);
                                return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                                super.onPostExecute(unused);
                                Log.e("Save note task", "Done");
                        }
                }
                new saveObje().execute();
        }
        public void BorrarNotas(ArrayList<Objeto> objetoa){
                class BorrarObjes extends AsyncTask<Void, Void, Void> {
                        @Override
                        protected Void doInBackground(Void... voids) {
                                for(Objeto objeto:objetoa){
                                        DataBaseNoter.getDatabase(activity).objetosDao().deleteNote(objeto);
                                }
                                return null;
                        }
                        @Override
                        protected void onPostExecute(Void unused) {
                                super.onPostExecute(unused);
                                Toast.makeText(activity, "Notas Eliminadas", Toast.LENGTH_SHORT).show();
                        }
                }
                new BorrarObjes().execute();
        }

        /** CARGAR LISTAS */
        public ArrayList<Objeto> getNotesArray(Activity activity,String route){
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());

                if (listArray != null)if(listArray.size()!=0) listArray.clear();
                executor.execute(() -> {
                        final List<Objeto> listaFull = DataBaseNoter.getDatabase(activity).objetosDao().fetchAllRuta(route);
                        listArray.addAll(listaFull);
                        handler.post(() -> {
                                Log.e("List Notes:", listArray.toString());
                        });
                });


                return listArray;
        }
        public ArrayList<Objeto> getNotesArraybyTitle(Activity activity,String route,String title){
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());

                if (listArray != null)if(listArray.size()!=0) listArray.clear();
                executor.execute(() -> {
                        final List<Objeto> listaFull = DataBaseNoter.getDatabase(activity).objetosDao().fetchPorTitulo(route,title);
                        listArray.addAll(listaFull);
                        handler.post(() -> {
                                Log.e("List Notes:", listArray.toString());
                        });
                });


                return listArray;
        }
        public ArrayList<String> getTitullis(Activity activity){
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());

                if (titlArray != null)if(titlArray.size()!=0) titlArray.clear();
                executor.execute(() -> {
                        final List<Objeto> listaFull = DataBaseNoter.getDatabase(activity).objetosDao().fetchAllRuta(ConstNoterRoutes.RouteMain);

                        for (Objeto objeto: listaFull) {
                                if(!titlArray.toString().contains(objeto.getTitulo())) {
                                        titlArray.add(objeto.getTitulo());
                                }
                        }
                        handler.post(() -> {
                                Log.e("lista Titulos:", titlArray.toString());
                        });
                });


                return titlArray;
        }


}
