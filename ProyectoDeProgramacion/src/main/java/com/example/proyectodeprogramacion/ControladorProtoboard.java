package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;

public class ControladorProtoboard {

    @FXML
    private GridPane busSuperior;

    @FXML
    private GridPane pistaSuperior;

    @FXML
    private GridPane busInferior;

    @FXML
    private GridPane pistaInferior;
    
    @FXML
    private Pane mainPane;

    @FXML
    private AnchorPane PantallaProtoboard;

    //Clases externas
    private Cables cableManager;
    private ControladorBateria controladorBateria;

    private AnchorPane pantalla;

    //variables para almacenar la posición del mouse
    private double offsetX;
    private double offsetY;

    public void setCableManager(Cables cableManager) {
        this.cableManager = cableManager;
    }

    public void setPantalla(AnchorPane pantalla) {
        this.pantalla = pantalla;
    }

    public AnchorPane getPantalla() {
        return pantalla;
    }

    public GridPane getBusSuperior() {
        return busSuperior;
    }

    public GridPane getPistaSuperior() {
        return pistaSuperior;
    }

    public GridPane getBusInferior() {
        return busInferior;
    }

    public GridPane getPistaInferior() {
        return pistaInferior;
    }

    //contructores de la clase
    public ControladorProtoboard() {}

    @FXML
    public void initialize() {

        //Obtenemos las variables globales
        pantalla = VariablesGlobales.pantallaPrincipal;
        
        // Agregar botones a busSuperior
        agregarBotonesGridPane(busSuperior, "busSuperior");

        // Agregar botones a pistaSuperior
        agregarBotonesGridPane(pistaSuperior, "pistaSuperior");

        // Agregar botones a busInferior
        agregarBotonesGridPane(busInferior, "busInferior");

        // Agregar botones a pistaInferior
        agregarBotonesGridPane(pistaInferior, "pistaInferior");

        //Manejamos los movimientos del mouse en el paneBateria
        PantallaProtoboard.setOnMousePressed(this::handleMousePressed);
        PantallaProtoboard.setOnMouseDragged(this::handleMouseDragged);

        //cableManager = new Cables(pantallaPrincipal.getPantallaPrincipal(), busSuperior, pistaSuperior, busInferior, pistaInferior);    
        cableManager = new Cables(busSuperior, pistaSuperior, busInferior, pistaInferior);
    }
    
    // Método que recorre un GridPane y añade botones en cada celda
    private void agregarBotonesGridPane(GridPane gridPane, String tipo) {
        int rows = gridPane.getRowConstraints().size();
        int columns = gridPane.getColumnConstraints().size();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Button button = new Button();
                // Tamaño del botón
                button.setMinSize(15, 15);
                button.setMaxSize(15, 15);
                button.setStyle("-fx-background-radius: 30;");

                // Asignar un ID basado en la posición
                button.setId("Button -"+ tipo+ "-"+ row + "-" + col);

                //manejamos el clic a la protoboard
                button.setOnAction(event -> {
                    onButtonClicked(button, tipo);
                });

                gridPane.add(button, col, row);
            }
        }
    }

    // Método para manejar cuando se hace clic en un botón de la protoboard
    public void onButtonClicked(Button button, String tipo) {
        // Si no hay un botón de inicio configurado, configúralo
        if(VariablesGlobales.aparecioBateria == true) {
            cableManager.setButtonStart(VariablesGlobales.botonPresionadoBateria);
            VariablesGlobales.aparecioBateria = false;
            VariablesGlobales.botonPresionadoBateria = null;
            System.out.println("Boton presionado bateria");

        }else if (cableManager.getButtonStart() == null) {
            cableManager.setButtonStart(button);
        }else {
            // Si ya hay un botón de inicio, configúralo como final y dibuja el cable
            cableManager.setButtonEndAndDrawCable(button);
        }
    }

    private void handleMousePressed(MouseEvent event) {
        offsetX = event.getSceneX() - PantallaProtoboard.getLayoutX();
        offsetY = event.getSceneY() - PantallaProtoboard.getLayoutY();
    }

    private void handleMouseDragged(MouseEvent event) {
        PantallaProtoboard.setLayoutX(event.getSceneX() - offsetX);
        PantallaProtoboard.setLayoutY(event.getSceneY() - offsetY);
    }

}