package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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
    public void initialize() {
        // Agregar botones a busSuperior
        agregarBotonesGridPane(busSuperior);

        // Agregar botones a pistaSuperior
        agregarBotonesGridPane(pistaSuperior);

        // Agregar botones a busInferior
        agregarBotonesGridPane(busInferior);

        // Agregar botones a pistaInferior
        agregarBotonesGridPane(pistaInferior);
    }

    // Método que recorre un GridPane y añade botones en cada celda
    private void agregarBotonesGridPane(GridPane gridPane) {
        int rows = gridPane.getRowConstraints().size();
        int columns = gridPane.getColumnConstraints().size();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Button button = new Button();
                //button.setPrefSize(20, 2); // Tamaño del botón, puedes ajustarlo
                button.setMinSize(15, 15);
                button.setMaxSize(15, 15);

                gridPane.add(button, col, row);
            }
        }
    }
}





