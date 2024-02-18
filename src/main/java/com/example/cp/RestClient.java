package com.example.cp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.http.HttpResponse;

import static javafx.application.Application.launch;


public class RestClient extends Application {

 //   private static final String BASE_URL = "http://localhost:8080/supplier/delete/18";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestClient.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Вход в систему");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }






   /* private static void createNewItem() {
        try {
            HttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(BASE_URL);
            //HttpPost request = new HttpPost("http://localhost:8080/supplier/delete/18");
            HttpResponse response = client.execute(request);
            System.out.println(request);
            System.out.println(response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("Item created successfully");
            } else {
                System.out.println("Failed to create item");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
