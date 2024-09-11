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
    private Button mostrarLed;
    @FXML
    private Button mostrarSwitch;
    @FXML
    private Button mostrarCable;
    @FXML
    private Pane mainPane;
    @FXML
    private AnchorPane mostrarCables;

    private Cables cableManager;

    private Button[][] botonesBusSuperior;
    private Button[][] botonesPistaSuperior;
    private Button[][] botonesBusInferior;
    private Button[][] botonesPistaInferior;

    private int posicionBusSuperior = -1;
    private int posicionBusInferior = -1;
    private int posicionPistaSuperior = -1;
    private int posicionPistaInferior = -1;

    //variable para controlar si aparecio la bateria
    private boolean aparecioBateria = false;

    private Button botonPresionado;
    ControladorBateria controladorBateria;
    public Button getBotonPresionado() {
        return botonPresionado;
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
    public void setControladrBateria(ControladorBateria bateria) {
        this.controladorBateria = bateria;
    }



    @FXML
    public void initialize() {
        // Inicializar las matrices de botones
        botonesBusSuperior = new Button[2][25];
        botonesBusInferior = new Button[2][25];

        botonesPistaSuperior = new Button[5][30];
        botonesPistaInferior = new Button[5][30];

        // Agregar botones a busSuperior
        agregarBotonesGridPane(busSuperior, "busSuperior");

        // Agregar botones a pistaSuperior
        agregarBotonesGridPane(pistaSuperior, "pistaSuperior");

        // Agregar botones a busInferior
        agregarBotonesGridPane(busInferior, "busInferior");

        // Agregar botones a pistaInferior
        agregarBotonesGridPane(pistaInferior, "pistaInferior");

        cableManager = new Cables(mostrarCables, mostrarCable, busSuperior, pistaSuperior, busInferior, pistaInferior);

    }
    public void setControladorBateria(ControladorBateria bateria) {
        this.controladorBateria = bateria;
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
                    if (aparecioBateria) {
                        controladorBateria.recibirDato(button, mostrarCable, mostrarCables, busSuperior, pistaSuperior, busInferior, pistaInferior);
                    }

                    //si no hay ningun aparato conectado, se dibuja con otro boton de la protoboard
                    onButtonClicked(button, tipo);
                });

                gridPane.add(button, col, row);
            }
        }
    }

    // Método para manejar la direccion de corriente de los buses
    public void manejoBotones(String tipo, int fila) {
        for (int i = 0; i < botonesBusSuperior.length; i++) {
            for (int j = 0; j < botonesBusSuperior[i].length; j++) {
                switch (tipo) {
                    case "busSuperior":
                        if (i == posicionBusSuperior) {
                            botonesBusSuperior[i][j].setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                        }
                        break;
                    case "busInferior":
                        if (i == posicionBusInferior) {
                            botonesBusInferior[i][j].setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                        }
                        break;
                    default:
                        break;
                }

            }
        }
    }

    // Método para manejar ka direccion de corriente de las pistas
    public void manejoPista(String tipo, int fila) {
        for (int i = 0; i < botonesPistaSuperior.length; i++) {
            for (int j = 0; j < botonesPistaSuperior[i].length; j++) {
                switch (tipo){
                    case "pistaSuperior":
                        if (j == posicionPistaSuperior) {
                            botonesPistaSuperior[i][j].setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                        }
                        break;
                    case "pistaInferior":
                        if (j == posicionPistaInferior) {
                            botonesPistaInferior[i][j].setStyle("-fx-background-color: green; -fx-background-radius: 30;");
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }



    // Método que carga la interfaces de los elemtentos de los botones
    private void cargarInterfacezElementos(String nombre){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombre));
            // Carga el archivo FXML
            Parent elemento = loader.load();
            // Cargamos el archivo nombre.fxml
            //Parent elemento = FXMLLoader.load(getClass().getResource(nombre));
            if (nombre.equals("switch.fxml")) {
                ControladorSwitch switchController = loader.getController();
                switchController.setProtoboard(this); // Pasar la instancia de Protoboard
            }
            elemento.setLayoutX(47);
            elemento.setLayoutY(53);
            mostrarCables.getChildren().add(elemento);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para manejar cuando se hace clic en un botón de la protoboard
    public void onButtonClicked(Button button, String tipo) {
        //cableManager.setButtonStart(botonCargaNegativa);
        // Si no hay un botón de inicio configurado, configúralo
        if (cableManager.getButtonStart() == null) {
            cableManager.setButtonStart(button);
        }else {
            // Si ya hay un botón de inicio, configúralo como final y dibuja el cable
            cableManager.setButtonEndAndDrawCable(button);
        }

    }

    @FXML
    private void botonAgregaBateria(ActionEvent event) {
        try {
            //Cargamos el fxml manual, para que no se pierdan los datos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bateria.fxml"));
            Parent root = loader.load();
            root.setLayoutX(47);
            root.setLayoutY(53);

            // Obtén el controlador de la batería
            ControladorBateria controladorBateria = loader.getController();

            // Establece la referencia en ControladorProtoboard
            this.setControladorBateria(controladorBateria);
            mostrarCables.getChildren().add(root);

            aparecioBateria = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void botonAgregaSwitch(ActionEvent event) {
        cargarInterfacezElementos("switch.fxml");
    }
    @FXML
    private void botonAgregaLed(ActionEvent event) {
        cargarInterfacezElementos("led.fxml");
    }

}





