module com.example.miniproject2 {
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires jdk.compiler;

    opens com.example.miniproject2 to javafx.fxml;
    exports com.example.miniproject2;
    opens com.example.miniproject2.controllers to javafx.fxml;
    opens com.example.miniproject2.views to javafx.fxml;
    opens com.example.miniproject2.models to javafx.base;

}