package com.example.proyectodeprogramacion;

import javafx.scene.shape.Line;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Point2D;

public class Cables {
    private Button buttonStart;
    private Button buttonEnd;
    private AnchorPane pane;

    public Cables(AnchorPane pane) {
        this.pane = pane;
    }

    // Método para establecer el primer botón
    public void setButtonStart(Button button) {
        this.buttonStart = button;
        buttonStart.setStyle("-fx-background-color: yellow;");  // Marcar botón seleccionado
    }

    // Método para establecer el segundo botón y dibujar el cable
    public void setButtonEndAndDrawCable(Button button) {
        this.buttonEnd = button;

        // Dibujar el cable solo si ambos botones están configurados
        if (buttonStart != null && buttonEnd != null) {
            drawCable();

            // Limpiar las referencias de los botones y restablecer estilos
            buttonStart.setStyle(null);
            buttonEnd.setStyle(null);
            buttonStart = null;
            buttonEnd = null;
        }
    }

    public Button getButtonStart() {
        return buttonStart;
    }

    public Button getButtonEnd() {
        return buttonEnd;
    }

    // Método para dibujar el cable
    private void drawCable() {
        // Obtener las posiciones globales de los botones
        Point2D startPoint = buttonStart.localToScene(buttonStart.getWidth() / 2, buttonStart.getHeight() / 2);
        Point2D endPoint = buttonEnd.localToScene(buttonEnd.getWidth() / 2, buttonEnd.getHeight() / 2);

        // Convertir las coordenadas de escena a coordenadas de pane
        Point2D startPaneCoords = pane.sceneToLocal(startPoint);
        Point2D endPaneCoords = pane.sceneToLocal(endPoint);

        // Crear la línea que representa el cable
        Line cable = new Line(startPaneCoords.getX(), startPaneCoords.getY(), endPaneCoords.getX(), endPaneCoords.getY());
        cable.setStrokeWidth(2);
        cable.setStyle("-fx-stroke: black;");

        // Agregar el cable al pane
        pane.getChildren().add(cable);
    }
}
