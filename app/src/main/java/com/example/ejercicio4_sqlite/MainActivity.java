package com.example.ejercicio4_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ejercicio4_sqlite.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private List<Persona> listaPersonas;
    private ActivityMainBinding binding;
    private RecyclerView recyclerViewPersona;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase(); //Escribe en la base de datos

        if (db != null){
            Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        }

        /*dbHelper.insertar("48196526W", "Santi", "Nieto", 19, "Alcalá");
        dbHelper.insertar("72397790T", "Antonio", "Pozo", 47, "Mairena");
        dbHelper.insertar("11569692W", "Jose", "Moreno", 22, "Sevilla");
        dbHelper.insertar("70093748Y", "Pedro", "Nolasco", 18, "Viso");
        dbHelper.insertar("57027770Y", "Jose Luis", "Galván", 42, "Barcelona");*/

        listaPersonas = dbHelper.obtenerPersonas();

        recyclerViewPersona = binding.recyclerView;
        recyclerViewPersona.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerViewAdapter(listaPersonas);
        recyclerViewPersona.setAdapter(adapter);
    }
}