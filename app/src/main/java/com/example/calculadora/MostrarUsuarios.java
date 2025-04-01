package com.example.calculadora;

import android.content.Intent;
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

import java.util.ArrayList;
public class MostrarUsuarios extends AppCompatActivity {

    private EditText etCodigo, etNombre, etApellido, etEdad, etEmail, etTelefono, etDireccion;
    private ArrayList<Usuario> listaUsuarios;
    RadioGroup rg1;
    RadioButton rb1, rb2, rb3;
    Spinner sp1;
    String estudios[] = {"No tiene","Primaria","Secundaria","Bachiller","Técnica y Tecnológica","Universitaria","Posgrado"};
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12, cb13, cb14, cb15;

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

        listaUsuarios = (ArrayList<Usuario>) getIntent().getSerializableExtra("listaUsuarios");
    }

    public void mostrarUsuario(View view) {
        String codigoStr = etCodigo.getText().toString();

        if (codigoStr.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un codigo", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoStr);
            Usuario usuario = buscarUsuario(codigo);

            if (usuario != null) {
                etNombre.setText(usuario.getNombre());
                etApellido.setText(usuario.getApellido());
                etEdad.setText(String.valueOf(usuario.getEdad()));
                if(usuario.getSexo().equals("Otro")){
                    rb3.setChecked(true);
                }
                if(usuario.getSexo().equals("Masculino")){
                    rb1.setChecked(true);
                }
                if(usuario.getSexo().equals("Femenino")){
                    rb2.setChecked(true);
                }
                etEmail.setText(usuario.getEmail());
                etTelefono.setText(usuario.getTelefono());
                etDireccion.setText(usuario.getDireccion());

                sp1.setSelection(Integer.parseInt(usuario.getEscolaridad()));

                if (usuario.getIntereses().contains("Videojuegos")){
                    cb1.setChecked(true);
                }
                if (usuario.getIntereses().contains("Musica")){
                    cb2.setChecked(true);
                }
                if (usuario.getIntereses().contains("Deporte")){
                    cb3.setChecked(true);
                }
                if (usuario.getIntereses().contains("Literatura")){
                    cb4.setChecked(true);
                }
                if (usuario.getIntereses().contains("Arte")){
                    cb5.setChecked(true);
                }
                if (usuario.getIntereses().contains("Cine")){
                    cb6.setChecked(true);
                }
                if (usuario.getIntereses().contains("Programacion")){
                    cb7.setChecked(true);
                }
                if (usuario.getIntereses().contains("Cocina")){
                    cb8.setChecked(true);
                }
                if (usuario.getIntereses().contains("Escritura")){
                    cb9.setChecked(true);
                }
                if (usuario.getIntereses().contains("Manualidades")){
                    cb10.setChecked(true);
                }
                if (usuario.getIntereses().contains("Idiomas")){
                    cb11.setChecked(true);
                }
                if (usuario.getIntereses().contains("Fotografia")){
                    cb12.setChecked(true);
                }
                if (usuario.getIntereses().contains("Jardineria")){
                    cb13.setChecked(true);
                }
                if (usuario.getIntereses().contains("Modelismo")){
                    cb14.setChecked(true);
                }
                if (usuario.getIntereses().contains("Meditacion")){
                    cb15.setChecked(true);
                }


            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Codigo erroneo. Ingrese un numero", Toast.LENGTH_SHORT).show();
        }
    }

    private Usuario buscarUsuario(int codigo) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCodigo() == codigo) {
                return usuario;
            }
        }
        return null;
    }

    public void modificar(View view) {
        String codigoStr = etCodigo.getText().toString();

        if (codigoStr.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un codigo", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoStr);
            Usuario usuario = buscarUsuario(codigo);

            if (usuario != null) {
                usuario.setNombre(etNombre.getText().toString());
                usuario.setApellido(etApellido.getText().toString());
                usuario.setEdad(Integer.parseInt(etEdad.getText().toString()));
                if (rb1.isChecked()){
                    usuario.setSexo("Masculino");
                }
                if (rb2.isChecked()){
                    usuario.setSexo("Femenino");
                }
                if (rb3.isChecked()){
                    usuario.setSexo("Otro");
                }
                usuario.setEmail(etEmail.getText().toString());
                usuario.setTelefono(etTelefono.getText().toString());
                usuario.setDireccion(etDireccion.getText().toString());
                usuario.setEscolaridad(sp1.getSelectedItemPosition()+"");
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
                usuario.setIntereses(intereses);

                Toast.makeText(this, "Usuario modificado correctamente", Toast.LENGTH_SHORT).show();
                actualizarLista();
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error en los datos ingresados", Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizarLista() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("listaUsuarios", listaUsuarios);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        actualizarLista();
        super.onBackPressed();
    }
}
