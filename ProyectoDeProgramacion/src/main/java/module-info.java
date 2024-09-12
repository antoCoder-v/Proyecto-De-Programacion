module com.example.proyectodeprogramacion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectodeprogramacion to javafx.fxml;
    exports com.example.proyectodeprogramacion;
}