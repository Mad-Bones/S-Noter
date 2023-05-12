package com.bsn.s_notera.pages.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "objeto")
public class Objeto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "creado")
    private Long creado;
    @ColumnInfo(name = "titulo")
    private String titulo;
    @ColumnInfo(name = "texto")
    private String texto;

    @ColumnInfo(name = "ruta")
    private String ruta;
    @ColumnInfo(name = "etiqueta")
    private String etiqueta;
    @ColumnInfo(name = "imagen")
    private String imagen;
    @ColumnInfo(name = "checked")
    private boolean checked;
    @ColumnInfo(name = "tipo")
    private int tipo;
    @ColumnInfo(name = "temp")
    private long temp;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCreado() {
        return creado;
    }

    public void setCreado(Long creado) {
        this.creado = creado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public long getTemp() {
        return temp;
    }

    public void setTemp(long temp) {
        this.temp = temp;
    }

    @NonNull
    @Override
    public String toString() {
        return titulo;
    }
}
