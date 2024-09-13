package com.example.proyectodeprogramacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ControladorBateria {

    @FXML
    private Button botonCargaNegativa;

    @FXML
    private Button botonCargaPositiva;

    @FXML
    private AnchorPane paneBateria;

    //variables para almacenar la posición del mouse
    private double offsetX;
    private double offsetY;

    //variables de otras clases
    private Cables cableManager;

    //variables para almacenar variables externas de la clase
    private Button botonPresionado;
    private AnchorPane pantalla;
    private GridPane busSuperior;
    private GridPane pistaSuperior;
    private GridPane busInferior;
    private GridPane pistaInferior;

    private ControladorPantallaPrincipal pantallaPrincipal;

    //constructores de la clase
    public ControladorBateria() {}

    public ControladorBateria(ControladorPantallaPrincipal controlPantallaPrincipal) {
        this.pantallaPrincipal = controlPantallaPrincipal;
    }


    //metodo que recibe las variables externas de la clase
    public void recibirDato(Button boton, AnchorPane pantalla, GridPane busSuperior, GridPane pistaSuperior, GridPane busInferior, GridPane pistaInferior) {
        this.botonPresionado = boton;
        this.pantalla = pantalla;
        this.busSuperior = busSuperior;
        this.pistaSuperior = pistaSuperior;
        this.busInferior = busInferior;
        this.pistaInferior = pistaInferior;
    }

    public void setPantallaPrincipal(AnchorPane pantalla) {
        this.pantalla = pantalla;
    }

    @FXML
    public void initialize() {
        pantalla = VairablesGlobales.pantallaPrincipal;
        //protoboard = new ControladorProtoboard();

        //Manejamos los movimientos del mouse en el paneBateria
        paneBateria.setOnMousePressed(this::handleMousePressed);
        paneBateria.setOnMouseDragged(this::handleMouseDragged);

    }

    @FXML
    public void botonCargaNegativa(ActionEvent event) {
        botonCargaNegativa.setStyle("-fx-background-color: red;");
        mostrarVentanaMensaje("Ha seleccionado carga negativa, para colocar cable aprete el boton.");
        //botonPositivo.setCarga("negativa");
        //cableManager = new Cables(pantalla, busSuperior, pistaSuperior, busInferior, pistaInferior);
        //cableManager.setTipoCarga("negativa");
        cableManager.setButtonStart(botonCargaNegativa);
        cableManager.setButtonEndAndDrawCable(botonPresionado);
    }

    @FXML
    public void botonCargaPositiva(ActionEvent event) {
        //cambiamos el boton a color verde
        botonCargaPositiva.setStyle("-fx-background-color: green;");
        mostrarVentanaMensaje("Ha seleccionado carga positiva, para colocar cable aprete el boton.");
        //cableManager = new Cables(pantalla,busSuperior, pistaSuperior, busInferior, pistaInferior);
        cableManager.setButtonStart(botonCargaPositiva);
        cableManager.setButtonEndAndDrawCable(botonPresionado);
    }

    private void handleMousePressed(MouseEvent event) {
        offsetX = event.getSceneX() - paneBateria.getLayoutX();
        offsetY = event.getSceneY() - paneBateria.getLayoutY();
    }

    private void handleMouseDragged(MouseEvent event) {
        paneBateria.setLayoutX(event.getSceneX() - offsetX);
        paneBateria.setLayoutY(event.getSceneY() - offsetY);
    }

    // Método para mostrar una ventana de mensaje
    private void mostrarVentanaMensaje(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}