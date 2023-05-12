package com.bsn.s_notera.pages.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Objeto.class, version = 1, exportSchema = false)
public abstract class DataBaseNoter extends RoomDatabase {

    private static DataBaseNoter DataBaseNoter;

    public static synchronized DataBaseNoter getDatabase(Context context){

        if (DataBaseNoter == null) {
            DataBaseNoter = Room.databaseBuilder(
                    context,
                    DataBaseNoter.class,
                    "notes_dbase"
            ).build();
        }
        return DataBaseNoter;
    }
    public abstract ObjetosDAO objetosDao();

}
