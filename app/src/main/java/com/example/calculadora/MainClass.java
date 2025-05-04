package com.example.calculadora;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.ArrayList;

public class MainClass extends AppCompatActivity {

    private EditText etNombre, etApellido, etEdad, etEmail, etTelefono, etDireccion;
    RadioGroup rg1;
    RadioButton rb1, rb2, rb3;
    Spinner sp1;
    String estudios[] = {"No tiene","Primaria","Secundaria","Bachiller","Técnica y Tecnológica","Universitaria","Posgrado"};
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12, cb13, cb14, cb15;
    AdminSQLiteOpenHelper admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lymainclass);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        etEmail = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);
        etDireccion = findViewById(R.id.etDireccion);
        rg1 = findViewById(R.id.rg1);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        sp1 = findViewById(R.id.sp1);
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,estudios);
        sp1.setAdapter(adaptador1);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        cb6 = findViewById(R.id.cb6);
        cb7 = findViewById(R.id.cb7);
        cb8 = findViewById(R.id.cb8);
        cb9 = findViewById(R.id.cb9);
        cb10 = findViewById(R.id.cb10);
        cb11 = findViewById(R.id.cb11);
        cb12 = findViewById(R.id.cb12);
        cb13 = findViewById(R.id.cb13);
        cb14 = findViewById(R.id.cb14);
        cb15 = findViewById(R.id.cb15);
        admin = new AdminSQLiteOpenHelper(this,"agenda",null,1);


        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("DatosUsuarios.txt")));
            String linea;
            SQLiteDatabase db = admin.getWritableDatabase();
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 9) {
                    ContentValues registro = new ContentValues();
                    registro.put("nombre", partes[0]);
                    registro.put("apellido", partes[1]);
                    registro.put("edad", Integer.parseInt(partes[2]));
                    registro.put("sexo", partes[3]);
                    registro.put("email", partes[4]);
                    registro.put("telefono", partes[5]);
                    registro.put("direccion", partes[6]);
                    registro.put("escolaridad", partes[7]);
                    registro.put("intereses", partes[8]);

                    db.insert("usuarios", null, registro);
                }
            }
            db.close();
            br.close();

        } catch (IOException e) {
            Toast.makeText(this, "Error al leer archivo", Toast.LENGTH_SHORT).show();
        }
    }


    public void calculadora(View View){

        Intent intent = new Intent( this, Calculadora.class);
        startActivity(intent);

    }

    public void siguienteActividad(View View){
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("SELECT * from usuarios", null);
        if (fila.moveToFirst()){
            Intent intent = new Intent( this, MostrarUsuarios.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"No existe el usuario",Toast.LENGTH_LONG).show();
        }
        bd.close();
    }

    public void guardarUsuario(View view) {

        if(     etNombre.getText().toString().isEmpty() || etApellido.getText().toString().isEmpty() ||
                etEdad.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() ||
                etTelefono.getText().toString().isEmpty() || etDireccion.getText().toString().isEmpty() ||
                (!rb1.isChecked() && !rb2.isChecked() && !rb3.isChecked())){
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());
        String sexo = "Otro";
        if (rb1.isChecked()){
            sexo = "Masculino";
        }
        if (rb2.isChecked()){
            sexo = "Femenino";
        }
        String email = etEmail.getText().toString();
        String telefono = etTelefono.getText().toString();
        String direccion = etDireccion.getText().toString();
        String escolaridad = "" + sp1.getSelectedItemPosition();
        String intereses = "";

        if(cb1.isChecked()){
            intereses += "Videojuegos";
        }
        if(cb2.isChecked()){
            intereses += "Musica";
        }
        if(cb3.isChecked()){
            intereses += "Deporte";
        }
        if(cb4.isChecked()){
            intereses += "Literatura";
        }
        if(cb5.isChecked()){
            intereses += "Arte";
        }
        if(cb6.isChecked()){
            intereses += "Cine";
        }
        if(cb7.isChecked()){
            intereses += "Programacion";
        }
        if(cb8.isChecked()){
            intereses += "Cocina";
        }
        if(cb9.isChecked()){
            intereses += "Escritura";
        }
        if(cb10.isChecked()){
            intereses += "Manualidades";
        }
        if(cb11.isChecked()){
            intereses += "Idiomas";
        }
        if(cb12.isChecked()){
            intereses += "Fotografia";
        }
        if(cb13.isChecked()){
            intereses += "Jardineria";
        }
        if(cb14.isChecked()){
            intereses += "Modelismo";
        }
        if(cb15.isChecked()){
            intereses += "Meditacion";
        }

        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        registro.put("edad", edad);
        registro.put("sexo", sexo);
        registro.put("email", email);
        registro.put("telefono", telefono);
        registro.put("direccion", direccion);
        registro.put("escolaridad", escolaridad);
        registro.put("intereses", intereses);
        long id = db.insert("usuarios", null, registro);
        Toast.makeText(this, "Usuario guardada con código: " + id, Toast.LENGTH_SHORT).show();

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("DatosUsuarios.txt", Context.MODE_APPEND));
            String datos = nombre + ";" + apellido + ";" + edad + ";" + sexo + ";" + email + ";" + telefono + ";" + direccion + ";" + escolaridad + ";" + intereses + "\n";
            archivo.write(datos);
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
            Toast.makeText(this, "Error al guardar en archivo", Toast.LENGTH_SHORT).show();
        }

        etNombre.setText("");
        etApellido.setText("");
        etEdad.setText("");
        rg1.clearCheck();
        etEmail.setText("");
        etTelefono.setText("");
        etDireccion.setText("");
        sp1.setSelection(0);
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
        cb4.setChecked(false);
        cb5.setChecked(false);
        cb6.setChecked(false);
        cb7.setChecked(false);
        cb8.setChecked(false);
        cb9.setChecked(false);
        cb10.setChecked(false);
        cb11.setChecked(false);
        cb12.setChecked(false);
        cb13.setChecked(false);
        cb14.setChecked(false);
        cb15.setChecked(false);


        db.close();

    }

}
