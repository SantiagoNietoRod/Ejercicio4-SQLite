package com.example.ejercicio4_sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;   //Controla la versión de la base de datos que se va ha usar
    private static final String NOMBRE_BASEDATOS = "basedatos1.db";
    private static final String TABLA_PERSONA = "t_persona";

    public DBHelper(@Nullable Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Se crea la tabla
        /*sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_PERSONA + "(" +
                "DNI VARCHAR(9)," +
                "Nombre VARCHAR(20)," +
                "Apellidos VARCHAR(50)," +
                "Edad INTEGER(3)," +
                "Direccion VARCHAR(50))");*/
    }

    //Este método se ejecuta cuando cambia la versión de la bd
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLA_PERSONA);
        onCreate(sqLiteDatabase);
    }

    public boolean insertar(String dni, String nombre, String apellidos, int edad, String direccion){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("DNI", dni);
        values.put("Nombre", nombre);
        values.put("Apellidos", apellidos);
        values.put("Edad", edad);
        values.put("DirecciOn", direccion);

        long res = db.insert(TABLA_PERSONA, null, values);

        if (res == -1){
            return false;
        }else{
            return true;
        }

    }

    public List<Persona> obtenerPersonas(){

        List<Persona> listaPersonas = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_PERSONA + " ORDER BY Edad DESC", null);

        if (cursor.moveToFirst()){

            do {

                @SuppressLint("Range") String dni = cursor.getString(cursor.getColumnIndex("DNI"));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
                @SuppressLint("Range") String apellidos = cursor.getString(cursor.getColumnIndex("Apellidos"));
                @SuppressLint("Range") int edad = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Edad")));
                @SuppressLint("Range") String direccion = cursor.getString(cursor.getColumnIndex("Direccion"));

                Persona persona = new Persona();
                persona.setDni(dni);
                persona.setNombre(nombre);
                persona.setApellidos(apellidos);
                persona.setEdad(edad);
                persona.setDireccion(direccion);

                listaPersonas.add(persona);

            }while (cursor.moveToNext());

            cursor.close();
            db.close();

        }
        return listaPersonas;

    }
}
