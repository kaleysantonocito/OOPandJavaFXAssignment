package com.kaley.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MainView view = new MainView();
        Scene scene = new Scene(view.getRoot(), 900, 560);

        scene.getStylesheets().add(getClass().getResource("/Styling.css").toExternalForm());

        stage.setTitle("FSC CSC325 - Full Stack Project");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
