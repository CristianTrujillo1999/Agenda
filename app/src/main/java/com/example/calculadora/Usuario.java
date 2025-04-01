package com.example.calculadora;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int codigo;
    private String nombre;
    private String apellido;
    private int edad;
    private String sexo;
    private String email;
    private String telefono;
    private String direccion;
    private String escolaridad;
    private String intereses;


    public Usuario(int codigo, String nombre, String apellido, int edad, String sexo, String email, String telefono, String direccion, String escolaridad, String intereses) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.escolaridad = escolaridad;
        this.intereses = intereses;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }




    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public String getIntereses() {
        return intereses;
    }
}
