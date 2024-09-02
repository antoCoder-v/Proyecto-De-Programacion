package com.example.proyectodeprogramacion;

import java.io.Serializable;

import com.example.proyectodeprogramacion.Datos;



import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Point2D;

public class Bateria implements Serializable{

    @FXML
    private Button botonCargaNegativa;

    @FXML
    private Button botonCargaPositiva;

    @FXML
    private AnchorPane anchorPaneBateria;

    @FXML
    private AnchorPane pantallaPrincipal;

    private double offsetX;
    private double offsetY;
    private double[] posicionBotonNegativo = new double[]{0, 0};
    private double[] posicionBotonPositiva = new double[]{0, 0};
    
    private ArchivosDatos archivosDatos = new ArchivosDatos();

    

    @FXML
    public void initialize() {

        // Agregar manejadores de eventos al anchorPaneBateria
        anchorPaneBateria.setOnMousePressed(this::handleMousePressed);
        anchorPaneBateria.setOnMouseDragged(this::handleMouseDragged);
        
    }

    @FXML
    public void botonCargaNegativa(ActionEvent event) {
        try {
            botonCargaNegativa.setStyle("-fx-background-color: red;");

            // Obtener las coordenadas del botonCargaNegativa en la escena
            Point2D botonNegativoCoordenadas = botonCargaNegativa.localToScene(botonCargaNegativa.getWidth() / 2, botonCargaNegativa.getHeight() / 2);
            posicionBotonNegativo = new double[]{botonNegativoCoordenadas.getX(), botonNegativoCoordenadas.getY()};
            System.out.println("Posición carga negativa: " + posicionBotonNegativo[0] + ", " + posicionBotonNegativo[1]);
            
            // Verifica que las clases Datos y ArchivosDatos estén correctamente definidas y sin errores
            Datos datos = new Datos("bateria", posicionBotonNegativo);
            archivosDatos.guardarDatos(datos, "bateria.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }   

    @FXML
    public void botonCargaPositiva(ActionEvent event) {
        try {
            botonCargaPositiva.setStyle("-fx-background-color: green;");
            
            // Obtener las coordenadas del botonCargaNegativa en la escena
            Point2D botonPositivaCoordenadas = botonCargaPositiva.localToScene(botonCargaPositiva.getWidth() / 2, botonCargaPositiva.getHeight() / 2);
            posicionBotonPositiva = new double[]{botonPositivaCoordenadas.getX(), botonPositivaCoordenadas.getY()};
            System.out.println("Posición carga negativa: " + posicionBotonNegativo[0] + ", " + posicionBotonNegativo[1]);
            
            // Verifica que las clases Datos y ArchivosDatos estén correctamente definidas y sin errores
            //Datos datos = new Datos("bateria", posicionBotonPositiva);
            //archivosDatos.guardarDatos(datos, "bateria.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }  

    private void handleMousePressed(MouseEvent event) {
        // Almacenar la posición inicial del mouse
        offsetX = event.getSceneX() - anchorPaneBateria.getLayoutX();
        offsetY = event.getSceneY() - anchorPaneBateria.getLayoutY();
    }

    private void handleMouseDragged(MouseEvent event) {
        // Mover el pane según el movimiento del mouse
        anchorPaneBateria.setLayoutX(event.getSceneX() - offsetX);
        anchorPaneBateria.setLayoutY(event.getSceneY() - offsetY);
    }
}
