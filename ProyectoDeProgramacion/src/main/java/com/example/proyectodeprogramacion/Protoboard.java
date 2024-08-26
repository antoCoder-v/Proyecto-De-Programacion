package com.example.proyectodeprogramacion;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.scene.Parent;

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
    private Button mostrarLed;
    @FXML
    private Button mostrarSwitch;
    @FXML
    private Button mostrarCable;
    @FXML
    private Pane bateria;
    @FXML
    private Pane mainPane;
    @FXML
    private AnchorPane mostrarCables;

    private Cables cableManager;

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
        mostrarLed.setOnAction(event -> loadLedInterface());
        cableManager = new Cables(mostrarCables);
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

                // Asignar un ID basado en la posición
                button.setId("Button-" + row + "-" + col);

                gridPane.add(button, col, row);
                button.setOnAction(event -> onButtonClicked(button));
            }
        }
    }

    // Método que carga la interfaces de los elemtentos de los botones
    private void cargarInterfacezElementos(String nombre){
        try {
            // Cargamos el archivo nombre.fxml
            Parent elemento = FXMLLoader.load(getClass().getResource(nombre));
            elemento.setLayoutX(47);
            elemento.setLayoutY(53);
            mostrarCables.getChildren().add(elemento);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadLedInterface() {
        try {
            // Cargamos el archivo Led.fxml
            Parent ledUI = FXMLLoader.load(getClass().getResource("Led.fxml"));
            // Agregamos el contenido del Led.fxml al mainPane
            mainPane.getChildren().clear();  // Limpiamos el panel antes de cargar la interfaz del LED
            mainPane.getChildren().add(ledUI); // Añadimos la interfaz del LED
        } catch (IOException e) {
            e.printStackTrace();
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

    @FXML
    private void botonAgregaBateria(ActionEvent event) {
        cargarInterfacezElementos("Bateria.fxml");
    }
}





