package com.example.calculadora;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    EditText edtAdminUser, edtAdminPass, edtUserCode, edtUserMail;
    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtAdminUser = findViewById(R.id.edtAdminUser);
        edtAdminPass = findViewById(R.id.edtAdminPass);
        edtUserCode = findViewById(R.id.edtUserCode);
        edtUserMail = findViewById(R.id.edtUserMail);
        admin = new AdminSQLiteOpenHelper(this,"agenda",null,1);
    }

    public void logAdmin(View v){
        String username = edtAdminUser.getText().toString();
        String password = edtAdminPass.getText().toString();
        if(username.equals("admin") && password.equals("admin")){
            Intent intent = new Intent( this, MainClass.class);
            startActivity(intent);
        }
    }

    public void logUser(View v){
        SQLiteDatabase bd = admin.getWritableDatabase();

        int userCode = 0;
        if(!edtUserCode.getText().toString().isEmpty()){
            userCode = Integer.parseInt(edtUserCode.getText().toString());
        }
        String mail = "-";

        if(!edtUserMail.getText().toString().isEmpty()){
            mail = edtUserCode.getText().toString();
        }

        Cursor fila = bd.rawQuery("SELECT * FROM usuarios WHERE id="+userCode + " AND email='"+mail+"'",null);
        if (fila.moveToFirst()){
            Intent intent = new Intent( this, Calculadora.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Credenciales incorrectos",Toast.LENGTH_LONG).show();
        }
        bd.close();
    }
}