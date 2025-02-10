module edu.fiuba.algo3 {
    requires javafx.controls;
    requires json.simple;
    requires com.fasterxml.jackson.databind;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    exports edu.fiuba.algo3.vista;
    exports edu.fiuba.algo3.controlador;
    exports edu.fiuba.algo3.modelo;
    exports edu.fiuba.algo3;
}