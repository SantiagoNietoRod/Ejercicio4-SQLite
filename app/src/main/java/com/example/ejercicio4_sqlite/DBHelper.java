package com.example.ejercicio4_sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;   //Controla la versión de la base de datos que se va ha usar
    private static final String NOMBRE_BASEDATOS = "basedatos1.db";
    private static final String TABLA_PERSONA = "t_persona";

    public DBHelper(@Nullable Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Se crea la tabla
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_PERSONA + "(" +
                "DNI VARCHAR(9) PRIMARY KEY AUTOINCREMENT," +
                "Nombre VARCHAR(20), NOT NULL," +
                "Apellidos VARCHAR(50), NOT NULL," +
                "Edad INTEGER(3), NOT NULL," +
                "Direccion VARCHAR(50), NOT NULL,)");
    }

    //Este método
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
