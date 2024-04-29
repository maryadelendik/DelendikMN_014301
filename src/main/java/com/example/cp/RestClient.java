package com.example.cp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.http.HttpResponse;

import static javafx.application.Application.launch;


public class RestClient extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestClient.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load()/*, 1000, 700*/);
        stage.setTitle("Вход в систему");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
