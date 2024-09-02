package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.io.Serializable;

import javafx.scene.shape.Line;

public class ControladorPantallaPrincipal implements Serializable{

    @FXML
    private Button mostrarLed;

    @FXML
    private Button mostrarSwitch;

    @FXML
    private Button mostrarCable;

    @FXML
    private Button botonAgregaBateria;
    
    @FXML
    private Button botonProtoboard;

    @FXML
    private AnchorPane pantallaPrincipal;

    public double[] posicionBotonProtoboard = new double[]{10, 10};
    public double[] posicionBotonNegativo = new double[]{0, 0};

    private Line lineaConexion;

    private ArchivosDatos archivosDatos = new ArchivosDatos();

    @FXML
    public void initialize() {
        
        mostrarLed.setOnAction(event -> cargarInterfacezElementos("led.fxml"));

        botonAgregaBateria.setOnAction(event -> {
            cargarInterfacezElementos("Bateria.fxml");
            System.out.println("posicion carga negativa " + posicionBotonNegativo[0] + posicionBotonNegativo[1]);  
        });
        botonProtoboard.setOnAction(event -> {
            cargarInterfacezElementos("protoboard.fxml");
        });

        mostrarCable.setOnAction(event -> {
            dibujaCable();
            System.out.println("posicion carga negativa " + posicionBotonNegativo[0] + posicionBotonNegativo[1]); 
            System.out.println("posicion protoboard " + posicionBotonProtoboard[0] + posicionBotonProtoboard[1]);
        });
    }

    // Método que carga la interfaces de los elemtentos de los botones
    private void cargarInterfacezElementos(String nombre){
        try {
            // Cargamos el archivo nombre.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombre));
            Parent elemento = loader.load();
            
            elemento.setLayoutX(100);   
            elemento.setLayoutY(100);   
            pantallaPrincipal.getChildren().add(elemento);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dibujaCable(){
        try{
            // Crear la línea
            lineaConexion = new Line();

            posicionBotonNegativo = archivosDatos.leerDatos("bateria", "bateria.txt");
            posicionBotonProtoboard = archivosDatos.leerDatos("protoboard", "protoboard.txt");


            // Establecer las coordenadas de la línea
            lineaConexion.setStartX(posicionBotonProtoboard[0]);
            lineaConexion.setStartY(posicionBotonProtoboard[1]);
            lineaConexion.setEndX(posicionBotonNegativo[0]);
            lineaConexion.setEndY(posicionBotonNegativo[1]);

            // Añadir la línea al AnchorPane principal
            pantallaPrincipal.getChildren().add(lineaConexion);
        }catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
        
    }

}