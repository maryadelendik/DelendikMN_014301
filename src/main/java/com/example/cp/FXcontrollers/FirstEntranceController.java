package com.example.cp.FXcontrollers;

import com.example.cp.RestClient;
import com.example.cp.WindowChanger;
import com.example.cp.authorization.Authorization;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class FirstEntranceController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button check_button;

    @FXML
    private PasswordField password_textfield;

    @FXML
    private Label warning_label;

    String role ="";
    Boolean check_reg;
    private String main_login;
    @FXML
    void initialize(Boolean check, String login) {
        main_login=login;
        check_reg = check;
    }
    private static final String BASE_URL = "http://localhost:8080/user";
    public void CheckButtonOnAction (ActionEvent actionEvent) throws IOException, InterruptedException {
        HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BASE_URL+"/new_password");
        Authorization auth = new Authorization();
        auth.setLogin(main_login);
        auth.setPassword(password_textfield.getText());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(auth);
        StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
        entity.setContentType("application/json; charset=UTF-8");
        request.setEntity(entity);
        HttpResponse response = client.execute(request);

        Authorization auth_menu = new Authorization();
        auth_menu.setLogin(main_login);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(RestClient.class.getResource("admin_menu.fxml"));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        if(response.getStatusLine().getStatusCode()==404){
            warning_label.setText("Данный пароль уже используется");
        } else if (check_reg) {
            check_button.getScene().getWindow().hide();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ОК");
            alert.setHeaderText(null);
            alert.setContentText("Пароль изменён.");
            alert.showAndWait();
        }
        else if (response.getStatusLine().getStatusCode()==200){
            stage.setTitle("Меню ответственного за материальные потоки");
            auth_menu.setRole(true);
            AdminMenuController controller = fxmlLoader.getController();
            controller.initialize(auth_menu);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            check_button.getScene().getWindow().hide();
        } else if (response.getStatusLine().getStatusCode()==401){
            stage.setTitle("Меню работника склада");
            auth_menu.setRole(false);
            AdminMenuController controller = fxmlLoader.getController();
            controller.initialize(auth_menu);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            check_button.getScene().getWindow().hide();
        }
    }
}
