package com.example.proyectodeprogramacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Datos implements Serializable{
    private String tipoDato;
    private double[] valores;
    private ArrayList[] listaDatos;


    public Datos(String tipoDato, double[] valores) {
        this.tipoDato = tipoDato;
        this.valores = valores;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public double[] getValores() {
        return valores;
    }
}
