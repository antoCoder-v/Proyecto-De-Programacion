package com.example.proyectodeprogramacion;

import javafx.fxml.FXML;
import javafx.scene.shape.Line;

public class Cables{//falta mejorar, se mueve lento el cable
    private String carga; //lo usaremos para identificar el tipo de carga que lleva el cable
    private boolean moviendoInicio = false; // Para saber si se está moviendo el extremo inicial
    private boolean moviendoFin = false; // Para saber si se está moviendo el extremo final

    @FXML
    private Line cablecito = new Line();

    /*  lo dejare aqui para dibujar mas cables (aun no funciona)
    public Cables (double inicioX, double inicioY, double finX, double funY, AnchorPane panel) {
        cablecito.setStartX(inicioX);
        cablecito.setStartY(inicioY);
        cablecito.setEndX(finX);
        cablecito.setEndY(finY);
        cablecito.setStrokeWidth(10);
        panel.getChildren().addAll(cablecito);
        inicializarControladoresEventos();
    }*/

    public Cables(Line cable){
        cablecito = cable;
        inicializarControladoresEventos();
    }

    private void inicializarControladoresEventos() {
        // Detectar clic en el extremo inicial o final
        cablecito.setOnMousePressed(e -> {
            if (estaCerca(cablecito.getStartX(), cablecito.getStartY(), e.getX(), e.getY())) {
                moviendoInicio = true;
                moviendoFin = false;
            } else if (estaCerca(cablecito.getEndX(), cablecito.getEndY(), e.getX(), e.getY())) {
                moviendoInicio = false;
                moviendoFin = true;
            }
        });

        // Mover el extremo inicial o final según el arrastre del ratón
        cablecito.setOnMouseDragged(e -> {
            if (moviendoInicio) {
                cablecito.setStartX(e.getX());
                cablecito.setStartY(e.getY());
            } else if (moviendoFin) {
                cablecito.setEndX(e.getX());
                cablecito.setEndY(e.getY());
            }
        });

        // Reiniciar las banderas cuando se suelte el ratón
        cablecito.setOnMouseReleased(e -> {
            moviendoInicio = false;
            moviendoFin = false;
        });
    }

    // Método auxiliar para detectar si el clic está cerca de un punto
    private boolean estaCerca(double x1, double y1, double x2, double y2) {
        return Math.hypot(x1 - x2, y1 - y2) < 10;
    }

}
