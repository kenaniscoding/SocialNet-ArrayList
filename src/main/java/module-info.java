module com.example.socialnet {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.socialnet to javafx.fxml;
    exports com.example.socialnet;
}