package com.example.proyectodeprogramacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Bateria {
    @FXML
    Rectangle rectanguloParaMover;

    @FXML
    Rectangle rec1;

    @FXML
    Rectangle rec2;

    @FXML
    Rectangle rec3;

    @FXML
    Rectangle rec4;

    @FXML
    Rectangle rec5;

    @FXML
    Text sim1;

    @FXML
    Text sim2;
    
    @FXML
    Text text;

    @FXML
    Button botonCargaNegativa;

    @FXML
    Button botonCargaPositiva;

    boolean movimiento = false;

    @FXML
    public void initialize() {
        inicializarControladoresEventos();
    }

    private void inicializarControladoresEventos() {
        // Variables para almacenar la posición inicial del mouse
        final double[] offsetX = new double[1];
        final double[] offsetY = new double[1];

        // Detectar clic para mover la bateria
        rectanguloParaMover.setOnMousePressed(e -> {
            movimiento = true;
            offsetX[0] = e.getX() - rectanguloParaMover.getX();
            offsetY[0] = e.getY() - rectanguloParaMover.getY();
        });

        // Mover el extremo inicial o final según el arrastre del ratón
        rectanguloParaMover.setOnMouseDragged(e -> {
            if (movimiento) {
                rectanguloParaMover.setX(e.getX() - offsetX[0]);
                rectanguloParaMover.setY(e.getY() - offsetY[0]);
                rec1.setX(e.getX() - offsetX[0]);
                rec1.setY(e.getY() - offsetY[0]);
                rec2.setX(e.getX() - offsetX[0]);
                rec2.setY(e.getY() - offsetY[0]);
                rec3.setX(e.getX() - offsetX[0]);
                rec3.setY(e.getY() - offsetY[0]);
                rec4.setX(e.getX() - offsetX[0]);
                rec4.setY(e.getY() - offsetY[0]);
                rec5.setX(e.getX() - offsetX[0]);
                rec5.setY(e.getY() - offsetY[0]);
                sim1.setX(e.getX() - offsetX[0]);
                sim1.setY(e.getY() - offsetY[0]);
                sim2.setX(e.getX() - offsetX[0]);
                sim2.setY(e.getY() - offsetY[0]);
                text.setX(e.getX() - offsetX[0]);
                text.setY(e.getY() - offsetY[0]);
                botonCargaNegativa.setLayoutX(e.getX() - offsetX[0] + 45);
                botonCargaNegativa.setLayoutY(e.getY() - offsetY[0] + 105);
                botonCargaPositiva.setLayoutX(e.getX() - offsetX[0] + 155);
                botonCargaPositiva.setLayoutY(e.getY() - offsetY[0] + 105);


                //botonCargaPositiva.setLayoutY(e.getY() - offsetY[0] + 105);
            }
        });

        // Reiniciar las banderas cuando se suelte el ratón
        rectanguloParaMover.setOnMouseReleased(e -> {
            movimiento = false;
        });
        
    }


    @FXML
    public void botonCargaNegativa (ActionEvent event){
        System.out.println("Carga Negativa");

    }

    @FXML
    public void botonCargaPositiva (ActionEvent event){
        System.out.println("Carga Positiva");
    }
    
 


}
