package com.example.proyectodeprogramacion;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Protoboard {

    @FXML
    private GridPane busSuperior;

    @FXML
    private GridPane pistaSuperior;

    @FXML
    private GridPane busInferior;

    @FXML
    private GridPane pistaInferior;

    @FXML
    private Pane bateria;
    @FXML
    private Pane mainPane;

    @FXML
    public void initialize() {
        // Agregar botones a busSuperior
        agregarBotonesGridPane(busSuperior, "busSuperior");

        // Agregar botones a pistaSuperior
        agregarBotonesGridPane(pistaSuperior, "pistaSuperior");

        // Agregar botones a busInferior
        agregarBotonesGridPane(busInferior, "busInferior");

        // Agregar botones a pistaInferior
        agregarBotonesGridPane(pistaInferior, "pistaInferior");

        
    }

    // Método que recorre un GridPane y añade botones en cada celda
    private void agregarBotonesGridPane(GridPane gridPane, String tipo) {
        int rows = gridPane.getRowConstraints().size();
        int columns = gridPane.getColumnConstraints().size();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Button button = new Button();
                //button.setPrefSize(20, 2); // Tamaño del botón, puedes ajustarlo
                button.setMinSize(15, 15);
                button.setMaxSize(15, 15);
                button.setOpacity(0.5);

                gridPane.add(button, col, row);

                //accion cuando presionan un boton
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        button.setStyle("-fx-background-color: #ff0000");
                    }
                });
            }
        }
    }

    public void cargaParaElBoton(String carga, String elementos){
        if (elementos.equals("Bateria")){
            //cargaBateria = carga;
        }
    }

}

