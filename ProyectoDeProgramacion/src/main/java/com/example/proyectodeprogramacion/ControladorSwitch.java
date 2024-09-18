package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.geometry.Bounds;

public class ControladorSwitch {

    @FXML
    private AnchorPane switchPane; // Contiene todo el contenido del switch, incluyendo el Rectangle y los Circles

    @FXML
    private Pane mainPane; // Asegúrate de que el fx:id de tu Pane en el archivo FXML coincida

    @FXML
    private Rectangle rectangle; // Rectángulo dentro del switchPane

    @FXML
    private Circle circleCenter; // Círculo dentro del switchPane

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;

    @FXML
    private Circle circle4;

    @FXML
    private Button encender;

    private double offsetX;
    private double offsetY;
    private ControladorProtoboard protoboard;
    

    public ControladorSwitch() {
    }

    @FXML
    public void initialize() {
        protoboard = VariablesGlobales.controladorProtoboard;

        // Configura los eventos de arrastre para el switchPane
        switchPane.setOnMousePressed(this::handleMousePressed);
        switchPane.setOnMouseDragged(this::handleMouseDragged);

        // Configura los eventos de arrastre para cada círculo (opcional si se necesita mover individualmente)
        circle1.setOnMousePressed(this::handleMousePressed);
        circle1.setOnMouseDragged(this::handleMouseDragged);

        circle2.setOnMousePressed(this::handleMousePressed);
        circle2.setOnMouseDragged(this::handleMouseDragged);

        circle3.setOnMousePressed(this::handleMousePressed);
        circle3.setOnMouseDragged(this::handleMouseDragged);

        circle4.setOnMousePressed(this::handleMousePressed);
        circle4.setOnMouseDragged(this::handleMouseDragged);

        circleCenter.setOnMousePressed(this::handleMousePressed);
        circleCenter.setOnMouseDragged(this::handleMouseDragged);

        // Configura el evento al presionar el botón "Encender"
        encender.setOnAction(event -> verificarPosicion(protoboard));
    }

    public void setProtoboard(ControladorProtoboard protoboard) {
        this.protoboard = protoboard;
    }

    private void handleMousePressed(MouseEvent event) {
        offsetX = event.getSceneX() - switchPane.getLayoutX();
        offsetY = event.getSceneY() - switchPane.getLayoutY();
    }

    private void handleMouseDragged(MouseEvent event) {
        switchPane.setLayoutX(event.getSceneX() - offsetX);
        switchPane.setLayoutY(event.getSceneY() - offsetY);

        // Llama a verificarPosicion después de mover el switch para verificar su posición
        //verificarPosicion(protoboard);
    }

    // Método para verificar sobre qué botones ha quedado el switch
    public void verificarPosicion(ControladorProtoboard protoboard) {
        if (protoboard == null) {
            System.out.println("Protoboard no está asignado.");
            return;
        }

        double switchX = switchPane.getLayoutX();
        double switchY = switchPane.getLayoutY();

        verificarEnGridPane(protoboard.getBusSuperior(), switchX, switchY);
        verificarEnGridPane(protoboard.getPistaSuperior(), switchX, switchY);
        verificarEnGridPane(protoboard.getBusInferior(), switchX, switchY);
        verificarEnGridPane(protoboard.getPistaInferior(), switchX, switchY);
    }

    // Método auxiliar para iterar sobre los botones de un GridPane
    private void verificarEnGridPane(GridPane gridPane, double switchX, double switchY) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;

                // Obtener los límites en escena de ambos elementos para una comparación precisa
                Bounds buttonBounds = button.localToScene(button.getBoundsInLocal());
                Bounds switchBounds = switchPane.localToScene(switchPane.getBoundsInLocal());

                // Verifica si los límites del switch intersectan con los límites del botón
                if (switchBounds.intersects(buttonBounds)) {
                    System.out.println("Switch sobre: " + button.getId());
                    System.out.println("----------------------------------");
                }
            }
        }
    }
}









