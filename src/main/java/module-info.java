module com.example.musicappspo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens com.example.musicappspo to javafx.fxml;
    exports com.example.musicappspo;
}

