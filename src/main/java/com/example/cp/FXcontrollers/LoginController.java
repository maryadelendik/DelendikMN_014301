package com.example.cp.FXcontrollers;


import com.example.cp.Connect;
import com.example.cp.RestClient;
import com.example.cp.WindowChanger;
import com.example.cp.authorization.Authorization;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label loginMessageLabel;

    private static final String BASE_URL = "http://localhost:8080/user";
    @FXML
    void initialize() {
    }
    public void loginButtonOnAction (ActionEvent actionEvent) throws IOException, InterruptedException {
             if (loginTextField.getText().isEmpty()||passwordTextField.getText().isEmpty()) {
            loginMessageLabel.setText("Ошибка входа. Введите логин и пароль!");
        } else {
                 HttpClient client = HttpClients.createDefault();
                 HttpPost request = new HttpPost(BASE_URL+"/check_first_password");
                 Authorization auth = new Authorization();
                 auth.setLogin(loginTextField.getText());
                 auth.setFirst_password(passwordTextField.getText());
                 ObjectMapper objectMapper = new ObjectMapper();
                 String jsonRequestBody = objectMapper.writeValueAsString(auth);
                 StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                 entity.setContentType("application/json; charset=UTF-8");
                 request.setEntity(entity);
                 HttpResponse response = client.execute(request);

              if (response.getStatusLine().getStatusCode() == 200){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(RestClient.class.getResource("first_entrance.fxml"));
                fxmlLoader.load();
                Parent root = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Новый пароль");
                stage.setScene(new Scene(root));
                FirstEntranceController controller = fxmlLoader.getController();
                controller.initialize(false, loginTextField.getText());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                loginButton.getScene().getWindow().hide();
            }
            else if (response.getStatusLine().getStatusCode() == 401){
                  HttpPost request_sign_in = new HttpPost(BASE_URL+"/authorization");
                  Authorization auth_sign_in = new Authorization();
                  auth_sign_in.setLogin(loginTextField.getText());
                  auth_sign_in.setPassword(passwordTextField.getText());
                  ObjectMapper objectMapper_sign_in = new ObjectMapper();
                  String jsonRequestBody_sign_in = objectMapper_sign_in.writeValueAsString(auth_sign_in);
                  StringEntity entity_sign_in = new StringEntity(jsonRequestBody_sign_in, StandardCharsets.UTF_8);
                  entity_sign_in.setContentType("application/json; charset=UTF-8");
                  request_sign_in.setEntity(entity_sign_in);
                  HttpResponse response_sign_in = client.execute(request_sign_in);

                if (response_sign_in.getStatusLine().getStatusCode() == 404){
                    loginMessageLabel.setText("Ошибка входа. Неверный пароль!");
                }else  {
                    ObjectMapper objectMapper_role = new ObjectMapper();
                    Authorization auth_role = objectMapper_role.readValue(response_sign_in.getEntity().getContent(), Authorization.class);
                    Authorization auth_menu = new Authorization();
                    auth_menu.setRole(auth_role.getRole());
                    auth_menu.setLogin(loginTextField.getText());
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(RestClient.class.getResource("admin_menu.fxml"));
                    fxmlLoader.load();
                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    if (auth_role.getRole()) {
                        stage.setTitle("Меню ответственного за материальные потоки");
                        AdminMenuController controller = fxmlLoader.getController();
                        controller.initialize(auth_menu);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                        loginButton.getScene().getWindow().hide();
                        // WindowChanger.changeWindow(getClass(), loginButton, "admin_menu.fxml", "Меню ответственного за материальные потоки", false);
                    }
                    else  {
                        stage.setTitle("Меню работника склада");
                        AdminMenuController controller = fxmlLoader.getController();
                        controller.initialize(auth_menu);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                        loginButton.getScene().getWindow().hide();
                        //  WindowChanger.changeWindow(getClass(), loginButton, "admin_menu.fxml", "Меню работника склада", false);
                    }
                }

            }
            else {
                loginMessageLabel.setText("Ошибка входа. Такой пользователь \nотсутствует в системе!");
            }

        }
    }
}
