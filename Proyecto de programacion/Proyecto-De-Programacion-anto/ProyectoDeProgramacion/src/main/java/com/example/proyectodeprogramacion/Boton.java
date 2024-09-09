package com.example.proyectodeprogramacion;

import javafx.scene.control.Button;

public class Boton {
    private String carga;
    private Button boton;  
    private double[] posicion;
    private boolean presionado;
    
    public Boton(Button boton) {
        this.boton = boton;
        this.carga = "neutra";
        this.presionado = false;
    }

    //Cuando modificamos la carga la representamos con colores
    public void setCarga(String carga) {
        if(carga.equals("positiva")){
            this.boton.setStyle("-fx-background-color: green;");
        }else if(carga.equals("negativa")){
            this.boton.setStyle("-fx-background-color: red;");
        }else if(carga.equals("neutra")){
            this.boton.setStyle("-fx-background-color: white;");
        }
            this.carga = carga;
        
    }

    public String getCarga() {
        return carga;
    }

    //obtenemos y modicamos la posicion del boton
    public void setPosicion(double[] posicion) {
        this.posicion = posicion;
    }

    public double[] getPosicion() {
        return posicion;
    }

    //obtenemos la posicion global del boton
    public double[] getPosicionGlobal(){
        return new double[]{boton.localToScene(boton.getWidth() / 2, boton.getHeight() / 2).getX(), boton.localToScene(boton.getWidth() / 2, boton.getHeight() / 2).getY()};
    }

    public boolean getPresionado() {
        return presionado;
    }

    public void setPresionado(boolean presionado) {
        this.presionado = presionado;
    }
}
