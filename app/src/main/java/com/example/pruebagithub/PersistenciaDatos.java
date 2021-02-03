package com.example.pruebagithub;


import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PersistenciaDatos extends SQLiteOpenHelper {
    private final String SQL_CREATE_TABLE_CONTACTOS = "CREATE TABLE PERSONAS (CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " CEDULA TEXT NOT NULL ," +
            "CLAVE TEXT NOT NULL " +
            ")";

    public PersistenciaDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PersistenciaDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public PersistenciaDatos(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear estrcutura de la bdd (tablas)
        db.execSQL(SQL_CREATE_TABLE_CONTACTOS);
        //Insertar datos iniciales

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Codigo SQL para crear estrcutura de la bdd (tablas) que neceistan modificarse
        db.execSQL("DROP TABLE IF EXISTS PERSONAS");
        db.execSQL(SQL_CREATE_TABLE_CONTACTOS);
    }


}
