module com.example.proyectodeprogramacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.proyectodeprogramacion to javafx.fxml;
    exports com.example.proyectodeprogramacion;
}