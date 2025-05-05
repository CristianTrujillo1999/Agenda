package com.example.calculadora;

import android.content.ContentValues;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MostrarUsuarios extends AppCompatActivity {

    private EditText etCodigo, etNombre, etApellido, etEdad, etEmail, etTelefono, etDireccion;
    RadioGroup rg1;
    RadioButton rb1, rb2, rb3;
    Spinner sp1;
    String estudios[] = {"No tiene","Primaria","Secundaria","Bachiller","Técnica y Tecnológica","Universitaria","Posgrado"};
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12, cb13, cb14, cb15;
    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lymostrarusuarios);

        etCodigo = findViewById(R.id.etCodigo);
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
    }

    public void mostrarUsuario(View view) {
        SQLiteDatabase bd = admin.getWritableDatabase();
        int id = Integer.parseInt(etCodigo.getText().toString());
        Cursor fila = bd.rawQuery("SELECT * from usuarios where id="+id,null);

        if ((etCodigo.getText().toString()).isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un codigo", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fila.moveToFirst()){
            etNombre.setText(fila.getString(1));
            etApellido.setText(fila.getString(2));
            etEdad.setText(String.valueOf(fila.getString(3)));
            if(fila.getString(4).equals("Otro")){
                rb3.setChecked(true);
            }
            if(fila.getString(4).equals("Masculino")){
                rb1.setChecked(true);
            }
            if(fila.getString(4).equals("Femenino")){
                rb2.setChecked(true);
            }

            etEmail.setText(fila.getString(5));
            etTelefono.setText(fila.getString(6));
            etDireccion.setText(fila.getString(7));

            sp1.setSelection(Integer.parseInt(fila.getString(8)));

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

            if (fila.getString(9).contains("Videojuegos")){
                cb1.setChecked(true);
            }
            if (fila.getString(9).contains("Musica")){
                cb2.setChecked(true);
            }
            if (fila.getString(9).contains("Deporte")){
                cb3.setChecked(true);
            }
            if (fila.getString(9).contains("Literatura")){
                cb4.setChecked(true);
            }
            if (fila.getString(9).contains("Arte")){
                cb5.setChecked(true);
            }
            if (fila.getString(9).contains("Cine")){
                cb6.setChecked(true);
            }
            if (fila.getString(9).contains("Programacion")){
                cb7.setChecked(true);
            }
            if (fila.getString(9).contains("Cocina")){
                cb8.setChecked(true);
            }
            if (fila.getString(9).contains("Escritura")){
                cb9.setChecked(true);
            }
            if (fila.getString(9).contains("Manualidades")){
                cb10.setChecked(true);
            }
            if (fila.getString(9).contains("Idiomas")){
                cb11.setChecked(true);
            }
            if (fila.getString(9).contains("Fotografia")){
                cb12.setChecked(true);
            }
            if (fila.getString(9).contains("Jardineria")){
                cb13.setChecked(true);
            }
            if (fila.getString(9).contains("Modelismo")){
                cb14.setChecked(true);
            }
            if (fila.getString(9).contains("Meditacion")){
                cb15.setChecked(true);
            }
        }else{
            Toast.makeText(this,"No existe el usuario",Toast.LENGTH_LONG).show();
        }
        bd.close();
    }

    public void modificar(View view) {
        SQLiteDatabase bd = admin.getWritableDatabase();
        int id = Integer.parseInt(etCodigo.getText().toString());
        ContentValues registro = new ContentValues();

        if ((etCodigo.getText().toString()).isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un codigo", Toast.LENGTH_SHORT).show();
            return;
        }

        registro.put("nombre",etNombre.getText().toString());
        registro.put("apellido",etApellido.getText().toString());
        registro.put("edad",Integer.parseInt(etEdad.getText().toString()));
        if (rb1.isChecked()){
            registro.put("sexo","Masculino");
        }
        if (rb2.isChecked()){
            registro.put("sexo","Femenino");
        }
        if (rb3.isChecked()){
            registro.put("sexo","Otro");
        }
        registro.put("email",etEmail.getText().toString());
        registro.put("telefono",etTelefono.getText().toString());
        registro.put("direccion",etDireccion.getText().toString());
        registro.put("escolaridad",sp1.getSelectedItemPosition()+"");

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
        registro.put("intereses",intereses);

        try {
            String archivo = "DatosUsuarios.txt";
            StringBuilder nuevoContenido = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(archivo)));
            String linea;

            String nuevaLinea = etNombre.getText().toString() + ";" +
                    etApellido.getText().toString() + ";" +
                    etEdad.getText().toString() + ";" +
                    (rb1.isChecked() ? "Masculino" : rb2.isChecked() ? "Femenino" : "Otro") + ";" +
                    etEmail.getText().toString() + ";" +
                    etTelefono.getText().toString() + ";" +
                    etDireccion.getText().toString() + ";" +
                    sp1.getSelectedItemPosition() + ";" +
                    intereses;

            String emailViejo = "";
            Cursor cursor = bd.rawQuery("SELECT email FROM usuarios WHERE id="+id, null);
            if (cursor.moveToFirst()) {
                emailViejo = cursor.getString(0);
            }

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 6 && partes[4].equals(emailViejo)) {
                    nuevoContenido.append(nuevaLinea).append("\n");
                } else {
                    nuevoContenido.append(linea).append("\n");
                }
            }

            OutputStreamWriter file = new OutputStreamWriter(openFileOutput(archivo, MODE_PRIVATE));
            file.write(nuevoContenido.toString());
            file.close();

        } catch (IOException e) {
            Toast.makeText(this, "Error actualizando archivo", Toast.LENGTH_SHORT).show();
        }

        bd.update("usuarios",registro,"id="+id,null);
        Toast.makeText(this, "Usuario modificado correctamente", Toast.LENGTH_SHORT).show();

    }

    public void eliminar(View view) {

        if (etCodigo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un código", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(etCodigo.getText().toString());
        SQLiteDatabase bd = admin.getWritableDatabase();

        String emailUsuario = "";
        Cursor cursor = bd.rawQuery("SELECT email FROM usuarios WHERE id=" + id, null);
        if (cursor.moveToFirst()) {
            emailUsuario = cursor.getString(0);
        } else {
            Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
            cursor.close();
            bd.close();
            return;
        }

        int Eliminados = bd.delete("usuarios", "id=" + id, null);
        bd.close();

        if (Eliminados > 0) {
            try {
                String archivo = "DatosUsuarios.txt";
                StringBuilder nuevoContenido = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(archivo)));
                String linea;

                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 5 && !partes[4].equals(emailUsuario)) {
                        nuevoContenido.append(linea).append("\n");
                    }
                }

                OutputStreamWriter file = new OutputStreamWriter(openFileOutput(archivo, MODE_PRIVATE));
                file.write(nuevoContenido.toString());
                file.close();

                Toast.makeText(this, "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                Toast.makeText(this, "Error al eliminar del archivo", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No se pudo eliminar el usuario", Toast.LENGTH_SHORT).show();
        }
    }
}
