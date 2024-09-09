package com.example.proyectodeprogramacion;

import javafx.scene.Node; // Add this import statement
import javafx.scene.shape.Line;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Point2D;

public class Cables {
    private Button buttonStart;
    private Button buttonEnd;
    private AnchorPane pane;
    private GridPane busSuperior;
    private GridPane busInferior;
    private GridPane pistaSuperior;
    private GridPane pistaInferior;

    private String tipoCarga;   //Manejamos la carga que recibe el cable
    private Button dibujaCable; // Manejamos el boton principal de dibujarCable

    public Cables(AnchorPane pane, Button dibujaCable, GridPane busSuperior, GridPane pistaSuperior, GridPane busInferior, GridPane pistaInferior) {
        this.pane = pane;
        this.tipoCarga = "neutra";
        this.dibujaCable = dibujaCable;

        //recibimos los grindpane para el manejo correcto de las corrientes
        if (busSuperior == null || pistaSuperior == null || busInferior == null || pistaInferior == null) {
            System.out.println("Alguno de los GridPane recibidos es nulo.");
        }
        this.busSuperior = busSuperior;
        this.busInferior = busInferior;
        this.pistaSuperior = pistaSuperior;
        this.pistaInferior = pistaInferior;
    }

    //funciones para modificar y recibir el tipoCarga
    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    // Método para establecer el primer botón
    public void setButtonStart(Button button) {
        this.buttonStart = button;
        buttonStart.setStyle("-fx-background-color: yellow; -fx-background-radius: 30;");  // Marcar botón seleccionado
    }

    // Método para establecer el segundo botón y dibujar el cable
    public void setButtonEndAndDrawCable(Button button) {
        
        this.buttonEnd = button;
        buttonEnd.setStyle("-fx-background-color: yellow; -fx-background-radius: 30;");  // Marcar botón seleccionado

        //dibujar el cable solo si se aprieta el boton 
        dibujaCable.setOnAction(e -> {
            if (buttonStart != null && buttonEnd != null) {
                drawCable();
                //si recibimos alguna carga, los botones quedaran con dicha carga, lo identicamos con un color
                if(tipoCarga.equals("positiva")){
                    buttonStart.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                    buttonEnd.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                    manejoCorriente(button, "positiva");
                }else if(tipoCarga.equals("negativa")){
                    buttonStart.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
                    buttonEnd.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
                    manejoCorriente(button, "negativa");
                }else{
                    buttonEnd.setStyle("-fx-background-color: white; -fx-background-radius: 30;");
                    buttonStart.setStyle("-fx-background-color: white; -fx-background-radius: 30;");
                }

                // Reiniciar los botones para otro uso
                buttonStart = null;
                buttonEnd = null;
            }else{
                mostrarVentanaMensaje("Para dibujar un cable, primero selecciona un botón de inicio y un botón final.");
            }
        });
        
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
        if(tipoCarga.equals("positiva"))
            cable.setStyle("-fx-stroke: green;");
        else if(tipoCarga.equals("negativa"))
            cable.setStyle("-fx-stroke: red;");
        else{
            cable.setStyle("-fx-stroke: black;");
        }

        // Agregar el cable al pane
        pane.getChildren().add(cable);
    }

    // Método para mostrar una ventana de mensaje
    private void mostrarVentanaMensaje(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void manejoCorriente(Button boton, String tipoCarga){
        String buttonId = boton.getId();
        String tipo = "";
        int row = -1;
        int col = -1;

        if (buttonId != null && buttonId.startsWith("Button -")) {
            String[] parts = buttonId.split("-");
            if (parts.length == 4) {
                tipo = parts[1].trim();
                row = Integer.parseInt(parts[2].trim());
                col = Integer.parseInt(parts[3].trim());

                System.out.println("Tipo: " + tipo);
                System.out.println("Row: " + row);
                System.out.println("Col: " + col);
            } else {
                System.out.println("ID del botón no tiene el formato esperado.");
            }
        }

        if(tipo.contains("busSuperior")){
            for (Node node : busSuperior.getChildren()) {
                Integer nodeRow = GridPane.getRowIndex(node);
                // Verificar si el nodo está en la posición deseada
                if(tipo.contains("busSuperior")){
                    if (nodeRow == row) {
                        if(tipoCarga.equals("positiva")){
                            node.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                        }else if(tipoCarga.equals("negativa")){
                            node.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
                        }
                    }
                }
            }
            
        }else if(tipo.contains("busInferior")){
            for (Node node : busInferior.getChildren()) {
                Integer nodeRow = GridPane.getRowIndex(node);
                // Verificar si el nodo está en la posición deseada
                if(tipo.contains("busInferior")){
                    if (nodeRow == row) {
                        if(tipoCarga.equals("positiva")){
                            node.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                        }else if(tipoCarga.equals("negativa")){
                            node.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
                        }
                    }
                }
            }
        }else if(tipo.contains("pistaSuperior")){
            for (Node node : pistaSuperior.getChildren()) {
                Integer nodeCol = GridPane.getColumnIndex(node);
                // Verificar si el nodo está en la posición deseada
                if(tipo.contains("pistaSuperior")){
                    if (nodeCol == col) {
                        if(tipoCarga.equals("positiva")){
                            node.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                        }else if(tipoCarga.equals("negativa")){
                            node.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
                        }
                    }
                }
            }
        }else if(tipo.contains("pistaInferior")){
            for (Node node : pistaInferior.getChildren()) {
                Integer nodeCol = GridPane.getColumnIndex(node);
                // Verificar si el nodo está en la posición deseada
                if(tipo.contains("pistaInferior")){
                    if (nodeCol == col) {
                        if(tipoCarga.equals("positiva")){
                            node.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                        }else if(tipoCarga.equals("negativa")){
                            node.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
                        }
                    }
                }
            }
        }
    }
}
