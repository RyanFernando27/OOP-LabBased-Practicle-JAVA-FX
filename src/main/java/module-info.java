module org.ticketmanager.ticketmanagerapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens org.ticketmanager.ticketmanagerapplication.config to com.google.gson;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.google.gson;

    exports org.ticketmanager.ticketmanagerapplication;
    exports org.ticketmanager.ticketmanagerapplication.ui to javafx.graphics;


}