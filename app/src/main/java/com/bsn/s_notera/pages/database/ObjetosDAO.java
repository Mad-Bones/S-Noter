package com.bsn.s_notera.pages.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ObjetosDAO {

    @Query("SELECT * FROM objeto ORDER BY id DESC")
    List<Objeto> fetchAllObjeto();

    @Query("SELECT * FROM objeto WHERE ruta = :ruta ORDER BY id DESC")
    List<Objeto> fetchAllRuta(String ruta);

    @Query("SELECT * FROM objeto WHERE titulo = :titulo ORDER BY id DESC")
    List<Objeto> fetchAllTitulos(String titulo);

    @Query("SELECT * FROM objeto WHERE titulo = :titulo AND ruta = :ruta ORDER BY id DESC")
    List<Objeto> fetchAllBorrados(String titulo,String ruta);
    @Query("SELECT * FROM objeto WHERE titulo = :titulo AND ruta = :ruta ORDER BY id DESC")
    List<Objeto> fetchPorTitulo(String ruta,String titulo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertObjeto(Objeto objeto);
    @Delete
    void deleteNote(Objeto objeto);
}