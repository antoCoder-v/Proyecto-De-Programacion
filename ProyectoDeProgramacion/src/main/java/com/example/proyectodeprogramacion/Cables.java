package com.example.proyectodeprogramacion;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Point2D;

public class Cables {
    private Button buttonStart, buttonEnd;
    private AnchorPane pane;
    private GridPane busSuperior, busInferior, pistaSuperior, pistaInferior;
    private String estilo;   // Para manejar la carga de los botones
    private Line cableSeleccionado = null; // Cable actualmente seleccionado

    public Cables() {
        // Constructor vacío
    }

    public Cables(GridPane busSuperior, GridPane pistaSuperior, GridPane busInferior, GridPane pistaInferior) {
        pane = VariablesGlobales.pantallaPrincipal;

        // Asignar los GridPane
        if (busSuperior == null || pistaSuperior == null || busInferior == null || pistaInferior == null) {
            System.out.println("Alguno de los GridPane recibidos es nulo.");
        }
        this.busSuperior = busSuperior;
        this.busInferior = busInferior;
        this.pistaSuperior = pistaSuperior;
        this.pistaInferior = pistaInferior;
    }

    // Método para establecer el primer botón
    public void setButtonStart(Button button) {
        this.buttonStart = button;
    }

    // Método para establecer el segundo botón y dibujar el cable
    public void setButtonEndAndDrawCable(Button button) {
        this.buttonEnd = button;

        if (buttonStart != null && buttonEnd != null) {
            if(buttonStart == buttonEnd){
                mostrarVentanaMensaje("No puedes conectar un botón consigo mismo.");
            } else {
                drawCable();

                // Reconocer la carga de los botones
                reconoceCarga(buttonStart, buttonEnd);

                // Propagar la corriente
                manejoCorriente(buttonStart);
                manejoCorriente(buttonEnd);

                // Reiniciar los botones seleccionados
                buttonStart = null;
                buttonEnd = null;
                estilo = "";
            }
        }
    }

    public Button getButtonStart() {
        return buttonStart;
    }

    public Button getButtonEnd() {
        return buttonEnd;
    }

    // Método para dibujar el cable entre los dos botones
    private void drawCable() {
        // Obtener las posiciones de inicio y final
        Point2D startPoint = buttonStart.localToScene(buttonStart.getWidth() / 2, buttonStart.getHeight() / 2);
        Point2D endPoint = buttonEnd.localToScene(buttonEnd.getWidth() / 2, buttonEnd.getHeight() / 2);

        // Convertir las coordenadas de escena a coordenadas locales del pane
        Point2D startPaneCoords = pane.sceneToLocal(startPoint);
        Point2D endPaneCoords = pane.sceneToLocal(endPoint);

        // Crear el cable como una línea
        Line cable = new Line(startPaneCoords.getX(), startPaneCoords.getY(), endPaneCoords.getX(), endPaneCoords.getY());
        cable.setStrokeWidth(3);

        // Habilitar la edición del cable
        enableCableEdit(cable);

        // Agregar el cable al pane
        pane.getChildren().add(cable);
    }

    // Método para habilitar la edición del cable
    private void enableCableEdit(Line cable) {
        // Evento para seleccionar el cable al hacer clic izquierdo
        cable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                // Resaltar el cable cuando esté seleccionado
                cable.setStroke(Color.BLUE);
                cableSeleccionado = cable;
            }
            // Evento para eliminar el cable al hacer clic derecho
            if (event.getButton() == MouseButton.SECONDARY) {
                pane.getChildren().remove(cable);
            }
        });

        // Evento para arrastrar el cable y moverlo
        cable.setOnMouseDragged(event -> {
            if (cableSeleccionado != null) {
                // Obtener las nuevas coordenadas y actualizar la posición del cable
                double newX = event.getX();
                double newY = event.getY();

                // Actualizar solo el punto final para simular el arrastre
                cableSeleccionado.setEndX(newX);
                cableSeleccionado.setEndY(newY);
            }
        });
    }

    // Método para mostrar una ventana de alerta
    private void mostrarVentanaMensaje(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método para manejar la propagación de corriente
    private void manejoCorriente(Button boton) {
        String buttonId = boton.getId();
        String tipo = "";
        int row = -1;
        int col = -1;

        // Obtener la información del botón
        if (buttonId != null && buttonId.startsWith("Button -")) {
            String[] parts = buttonId.split("-");
            if (parts.length == 4) {
                tipo = parts[1].trim();
                row = Integer.parseInt(parts[2].trim());
                col = Integer.parseInt(parts[3].trim());
            } else {
                System.out.println("ID del botón no tiene el formato esperado.");
            }
        }

        // Propagar el color solo en la fila o columna
        if (tipo.contains("busSuperior") || tipo.contains("busInferior")) {
            GridPane gridPane = tipo.contains("busSuperior") ? busSuperior : busInferior;
            for (Node node : gridPane.getChildren()) {
                Integer nodeRow = GridPane.getRowIndex(node);
                if (nodeRow != null && nodeRow.equals(row)) {
                    node.setStyle(estilo);
                }
            }
        } else if (tipo.contains("pistaSuperior") || tipo.contains("pistaInferior")) {
            GridPane gridPane = tipo.contains("pistaSuperior") ? pistaSuperior : pistaInferior;
            for (Node node : gridPane.getChildren()) {
                Integer nodeCol = GridPane.getColumnIndex(node);
                if (nodeCol != null && nodeCol.equals(col)) {
                    node.setStyle(estilo);
                }
            }
        }
    }

    // Método para reconocer la carga de los botones y definir el estilo del cable
    private void reconoceCarga(Button boton1, Button boton2){
        String style1 = boton1.getStyle();
        String style2 = boton2.getStyle();
        if(style1.contains("green") && style2.contains("red") || style1.contains("red") && style2.contains("green")){
            mostrarVentanaMensaje("No se puede conectar distintas cargas de energías.");
        } else if(style1.contains("green") || style2.contains("green")){
            estilo = "-fx-background-color: green; -fx-background-radius: 30;";
        } else if(style1.contains("red") || style2.contains("red")){
            estilo = "-fx-background-color: red; -fx-background-radius: 30;";
        } else {
            estilo = "-fx-background-radius: 30;";
        }
    }
}
