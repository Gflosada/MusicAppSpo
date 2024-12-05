package com.example.musicappspo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MusicApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Music Search App");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

