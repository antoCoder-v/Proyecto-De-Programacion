package com.example.proyectodeprogramacion;

import javafx.scene.Node; // Add this import statement
import javafx.scene.shape.Line;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;


public class Cables {
    private Button buttonStart;
    private Button buttonEnd;
    private AnchorPane pane;
    private GridPane busSuperior;
    private GridPane busInferior;
    private GridPane pistaSuperior;
    private GridPane pistaInferior;

    private String estilo;   //Manejamos la carga que tienen alguno de los botones
    private Button dibujaCable; // Manejamos el boton principal de dibujarCable

    public Cables(AnchorPane pane, Button dibujaCable, GridPane busSuperior, GridPane pistaSuperior, GridPane busInferior, GridPane pistaInferior) {
        this.pane = pane;
        //this.tipoCarga = "neutra";
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

    // Método para establecer el primer botón
    public void setButtonStart(Button button) {
        this.buttonStart = button;
        //buttonStart.setStyle("-fx-background-color: yellow; -fx-background-radius: 30;");  // Marcar botón seleccionado
    }

    // VERIFICAR---------Método para obtener el color actual de un botón
    private Color getButtonColor(Button button) {
        String style = button.getStyle();
        if (style.contains("green")) {
            return Color.GREEN;
        } else if (style.contains("red")) {
            return Color.RED;
        } else if (style.contains("white")) {
            return Color.WHITE;
        }
        return Color.TRANSPARENT; // Default color if not set
    }

    //HASTA AQUI---------------

    // Método para establecer el segundo botón y dibujar el cable
    /*public void setButtonEndAndDrawCable(Button button) {

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

    }*/
    public void setButtonEndAndDrawCable(Button button) {
        this.buttonEnd = button;
        //buttonEnd.setStyle("-fx-background-color: yellow; -fx-background-radius: 30;");  // Marcar botón seleccionado
        // Dibujar el cable solo si se aprieta el botón
        dibujaCable.setOnAction(e -> {
            if (buttonStart != null && buttonEnd != null) {
                drawCable();

                // Reconocer la carga de los botones de inicio y fin
                reconoceCarga(buttonStart, buttonEnd);

                // Configurar el color del botón de inicio y fin en función del tipo de carga
                //if (tipoCarga.equals("positiva")) {
                    //buttonStart.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                    //buttonEnd.setStyle("-fx-background-color: green; -fx-background-radius: 30;");

                //} else if (tipoCarga.equals("negativa")) {
                    //buttonStart.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
                    //buttonEnd.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
                 //else {
                    //buttonEnd.setStyle("-fx-background-color: white; -fx-background-radius: 30;");
                    //buttonStart.setStyle("-fx-background-color: white; -fx-background-radius: 30;");
                //}
                //System.out.println("Button Start ID: " + buttonStart.getId() + ", Color: " + tipoCarga);
                //System.out.println("Button End ID: " + buttonEnd.getId() + ", Color: " + tipoCarga);

                // Llamar al método manejoCorriente para propagar el color a otros botones en la fila/columna
                manejoCorriente(buttonStart);
                manejoCorriente(buttonEnd);

                // Reiniciar los botones para otro uso
                buttonStart = null;
                buttonEnd = null;
            } else {
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
        //if(tipoCarga.equals("positiva"))//NOSE SI AQUI PONER--------------------------------------------
            //cable.setStyle("-fx-stroke: green;");
        //else if(tipoCarga.equals("negativa"))
            //cable.setStyle("-fx-stroke: red;");
        //else{
            //cable.setStyle("-fx-stroke: black;");
        //}

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

    /*private void manejoCorriente(Button boton, String tipoCarga){
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
    }*/
    private void manejoCorriente(Button boton) {
        String buttonId = boton.getId();
        String tipo = "";
        int row = -1;
        int col = -1;

        //identificamos si el boton tiene algun color(carga)
        /* 
        String style = boton.getStyle();
        String estilo = "";
        if (style.contains("-fx-background-color: green;")) {
            estilo = "-fx-background-color: green; -fx-background-radius: 30;";
            System.out.println("El botón es de color verde.");
        } else if (style.contains("-fx-background-color: red;")) {
            estilo = "fx-background-color: red; -fx-background-radius: 30;";
            System.out.println("El botón es de color rojo.");
        } else {
            System.out.println("El color del botón no está identificado.");
        }*/

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

        // Propagar el color solo en la fila o columna correspondiente
        if (tipo.contains("busSuperior") || tipo.contains("busInferior")) {
            GridPane gridPane = tipo.contains("busSuperior") ? busSuperior : busInferior;
            for (Node node : gridPane.getChildren()) {
                Integer nodeRow = GridPane.getRowIndex(node);
                if (nodeRow != null && nodeRow.equals(row)) {
                    //if(tipoCarga.equals("positiva")){
                        //node.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                    //}else if(tipoCarga.equals("negativa")){
                        //node.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
                    //}else{
                        node.setStyle(estilo);
                    //}
                }
            }
        } else if (tipo.contains("pistaSuperior") || tipo.contains("pistaInferior")) {
            GridPane gridPane = tipo.contains("pistaSuperior") ? pistaSuperior : pistaInferior;
            for (Node node : gridPane.getChildren()) {
                Integer nodeCol = GridPane.getColumnIndex(node);
                if (nodeCol != null && nodeCol.equals(col)) {
                    node.setStyle(estilo);
                    //node.setStyle("-fx-background-color: "+ color + "; -fx-background-radius: 30;");
                    //if(tipoCarga.equals("positiva")){
                        //node.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                    //}else if(tipoCarga.equals("negativa")){
                        //node.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
                    //}
                    //node.setStyle("-fx-background-color: " + (tipoCarga.equals("positiva") ? "green" : "red") + "; -fx-background-radius: 30;");
                }
            }
        }
    }

    private void reconoceCarga(Button boton1, Button boton2){
        String style1 = boton1.getStyle();
        String style2 = boton2.getStyle();
        if(style1.contains("green") || style2.contains("green")){
            estilo = "-fx-background-color: green; -fx-background-radius: 30;";
        }else if(style1.contains("red") || style2.contains("red")){
            estilo = "-fx-background-color: red; -fx-background-radius: 30;";
        }
    }




    // Método auxiliar para aplicar el color a todos los botones en la fila o columna
    private void applyColorToRowOrColumn(String tipo, int row, int col, String tipoCarga) {
        GridPane gridPane = null;

        // Determinar el GridPane correspondiente basado en el tipo
        if (tipo.contains("busSuperior")) {
            gridPane = busSuperior;
        } else if (tipo.contains("busInferior")) {
            gridPane = busInferior;
        } else if (tipo.contains("pistaSuperior")) {
            gridPane = pistaSuperior;
        } else if (tipo.contains("pistaInferior")) {
            gridPane = pistaInferior;
        }

        if (gridPane != null) {
            for (Node node : gridPane.getChildren()) {
                Integer nodeRow = GridPane.getRowIndex(node);
                Integer nodeCol = GridPane.getColumnIndex(node);

                // Aplicar color a toda la fila o columna correspondiente
                if (nodeRow != null && nodeRow == row) {
                    applyColor(node, tipoCarga);
                }
                if (nodeCol != null && nodeCol == col) {
                    applyColor(node, tipoCarga);
                }
            }
        }
    }

    // Método para aplicar el color al botón
    private void applyColor(Node node, String tipoCarga) {
        if (node instanceof Button) {
            Button button = (Button) node;
            if (tipoCarga.equals("positiva")) {
                button.setStyle("-fx-background-color: green; -fx-background-radius: 30;");
            } else if (tipoCarga.equals("negativa")) {
                button.setStyle("-fx-background-color: red; -fx-background-radius: 30;");
            }
        }
    }
    
}

