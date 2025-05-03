package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;

public class Calculadora extends AppCompatActivity {

        TextView resultado, solucion;
        Button buttonAC, buttonC, buttonParen, buttonPorcen;
        Button buttonDiv, buttonMul, buttonSum, buttonRes, buttonIgual;
        Button button9, button8, button7, button6, button5, button4, button3, button2, button1, button0, buttonDot;
        Spinner spinnerHt;
        ArrayList<String> historial = new ArrayList<>();

        int errorFlag = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.lycalculadora);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            resultado = findViewById(R.id.resultado);
            solucion = findViewById(R.id.solucion);

            buttonAC = findViewById(R.id.buttonAC);
            buttonC = findViewById(R.id.buttonC);
            buttonParen = findViewById(R.id.buttonParen);
            buttonPorcen = findViewById(R.id.buttonPorcen);
            buttonDiv = findViewById(R.id.buttonDiv);
            buttonMul = findViewById(R.id.buttonMul);
            buttonSum = findViewById(R.id.buttonSum);
            buttonRes = findViewById(R.id.buttonRes);
            buttonIgual = findViewById(R.id.buttonIgual);
            button9 = findViewById(R.id.button9);
            button8 = findViewById(R.id.button8);
            button7 = findViewById(R.id.button7);
            button6 = findViewById(R.id.button6);
            button5 = findViewById(R.id.button5);
            button4 = findViewById(R.id.button4);
            button3 = findViewById(R.id.button3);
            button2 = findViewById(R.id.button2);
            button1 = findViewById(R.id.button1);
            button0 = findViewById(R.id.button0);
            buttonDot = findViewById(R.id.buttonDot);

            spinnerHt = findViewById(R.id.spinnerHt);
            ArrayAdapter<String> adaptador1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,historial);
            historial.add("Historial");
            spinnerHt.setAdapter(adaptador1);

            spinnerHt.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Object item = parent.getItemAtPosition(position);
                            String ResHistorial = item.toString();
                            if(!ResHistorial.equals("Historial")) {
                                resultado.setText("");
                                resultado.setText(ResHistorial.substring(ResHistorial.lastIndexOf(" ") + 1));
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    }
            );

        }

        public void deleteAll(View view){
            solucion.setText("");
            resultado.setText("");
        }

        public void getText(View view){
            Button clickedButton = (Button) view;
            String buttonText = clickedButton.getText().toString();
            String input = resultado.getText().toString();
            String operacion = solucion.getText().toString();

            if(errorFlag == 1){
                solucion.setText("");
                errorFlag = 0;
            }

            if (buttonText.equals("C")){ //Borra el número puesto en el campo de resultado, de uno en uno.
                if (!input.isEmpty()){
                    resultado.setText(input.substring(0,input.length()-1));
                }
            }else{
                if(!(buttonText.equals("/") || buttonText.equals("*") || buttonText.equals("+"))) { //Evita que se pongan los operadores mostrados
                    input = input + buttonText;
                }
                resultado.setText(input);
            }

            if (buttonText.equals("/") || buttonText.equals("*") || buttonText.equals("+")){
                if (!input.isEmpty()){
                    solucion.setText(operacion+input+buttonText);
                    resultado.setText("");
                }
            }

            if(buttonText.equals("-")){
                if (!input.isEmpty() && input.length() > 1 && input.substring(0,1).equals("-")){
                    if (input.length()>2 && !input.substring(1, input.length()-1).contains("-")){
                        solucion.setText(operacion+input);
                        resultado.setText("");
                    }else{
                        resultado.setText("-");
                    }
                }else{
                    if(!input.isEmpty() && !input.substring(0,1).equals("-")){
                        solucion.setText(operacion+input);
                        resultado.setText("");
                    }
                }
            }

        }

        public void setResult(View view){
            String res = resultado.getText().toString();
            String CalcOperacion = solucion.getText().toString() + res;
            Expression exp = new Expression(CalcOperacion);
            String result = String.valueOf(exp.calculate());

            if(errorFlag == 1){
                solucion.setText("");
                resultado.setText("");
                errorFlag = 0;
            }

            if (!CalcOperacion.isEmpty()){
                solucion.setText("");
                resultado.setText(result);
                historial.add(CalcOperacion + " = " + result);
            }

            if (result.equals("NaN") && !CalcOperacion.isEmpty()){
                solucion.setText("Error");
                resultado.setText("");
                errorFlag = 1;
            }

        }

        public void decimal(View view){
            String input = resultado.getText().toString();
            if (!input.isEmpty() && !input.contains(".")){
                resultado.setText(input + ".");
            }
        }

        public void parentesis(View view){
            String full_op = solucion.getText().toString() + resultado.getText().toString();
            String input = resultado.getText().toString();
            int abierto = 0;
            int cerrado = 0;
            int largo = full_op.length();
            for (int i = 0; i < largo;i++){
                if (full_op.substring(i,i+1).equals("(")){
                    abierto += 1;
                }
                if (full_op.substring(i,i+1).equals(")")){
                    cerrado += 1;
                }
            }
            if (abierto == cerrado || full_op.substring(largo-1,largo).equals("(")){
                resultado.setText(input + "(");
            }else if(cerrado < abierto && !full_op.substring(largo-1,largo).equals("(")){
                resultado.setText(input + ")");
            }
        }
    }