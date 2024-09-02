package com.example.proyectodeprogramacion;


import java.io.Serializable;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;

public class Protoboard implements Serializable{

    @FXML
    private GridPane busSuperior;

    @FXML
    private GridPane pistaSuperior;

    @FXML
    private GridPane busInferior;

    @FXML
    private GridPane pistaInferior;
    
    @FXML
    private AnchorPane PantallaProtoboard;

    private double offsetX;
    private double offsetY;

    private Cables cableManager;
    public double[] posicionBotonProtoboard = new double[]{10, 10};
    private ArchivosDatos archivosDatos = new ArchivosDatos();


    @FXML
    public void initialize() {
        // Agregar botones a busSuperior
        agregarBotonesGridPane(busSuperior);

        // Agregar botones a pistaSuperior
        agregarBotonesGridPane(pistaSuperior);

        // Agregar botones a busInferior
        agregarBotonesGridPane(busInferior);

        // Agregar botones a pistaInferior
        agregarBotonesGridPane(pistaInferior);

        cableManager = new Cables(PantallaProtoboard);

        // Agregar manejadores de eventos a la protoboard
        PantallaProtoboard.setOnMousePressed(this::handleMousePressed);
        PantallaProtoboard.setOnMouseDragged(this::handleMouseDragged);
    }

    // Método que recorre un GridPane y añade botones en cada celda
    private void agregarBotonesGridPane(GridPane gridPane) {
        try{
            int rows = gridPane.getRowConstraints().size();
            int columns = gridPane.getColumnConstraints().size();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    Button button = new Button();
                    //button.setPrefSize(20, 2); // Tamaño del botón, puedes ajustarlo
                    button.setMinSize(15, 15);
                    button.setMaxSize(15, 15);

                    // Asignar un ID basado en la posición
                    button.setId("Button-" + row + "-" + col);

                    gridPane.add(button, col, row);
                    button.setOnAction(event -> {
                        try{
                            onButtonClicked(button);
                            // Obtener las coordenadas del botonCargaNegativa en la escena
                            Point2D buttonCoordenada = button.localToScene(button.getWidth() / 2, button.getHeight() / 2);
                            posicionBotonProtoboard = new double[] { buttonCoordenada.getX(), buttonCoordenada.getY() };
                            
                            System.out.println("Posición protoboard: " + posicionBotonProtoboard[0] + ", " + posicionBotonProtoboard[1]);
                            
                            Datos datos = new Datos("protoboard", posicionBotonProtoboard);
                            archivosDatos.guardarDatos(datos, "protoboard.txt");
                            //archivosDatos.guardarDatos(datos);
                        }
                        catch (Exception e) {
                            System.out.println("Error en: " + e.getMessage());
                        }
                        
                        
                    });
                }
            }
        }catch (Exception e) {
            System.out.println("Error en: " + e.getMessage());
        }
        
    }

    // Método para manejar cuando se hace clic en un botón de la protoboard
    public void onButtonClicked(Button button) {
        // Si no hay un botón de inicio configurado, configúralo
        if (cableManager.getButtonStart() == null) {
            cableManager.setButtonStart(button);
        } else {
            // Si ya hay un botón de inicio, configúralo como final y dibuja el cable
            cableManager.setButtonEndAndDrawCable(button);
        }
    }

    private void handleMousePressed(MouseEvent event) {
        // Almacenar la posición inicial del mouse
        offsetX = event.getSceneX() - PantallaProtoboard.getLayoutX();
        offsetY = event.getSceneY() - PantallaProtoboard.getLayoutY();
    }

    private void handleMouseDragged(MouseEvent event) {
        // Mover el pane según el movimiento del mouse
        PantallaProtoboard.setLayoutX(event.getSceneX() - offsetX);
        PantallaProtoboard.setLayoutY(event.getSceneY() - offsetY);
    }

}