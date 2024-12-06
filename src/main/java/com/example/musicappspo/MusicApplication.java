package com.example.musicappspo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MusicApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Music App");

        // Set the application icon
        Image appIcon = new Image(MusicApplication.class.getResourceAsStream("iconex.png"));
        primaryStage.getIcons().add(appIcon);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
