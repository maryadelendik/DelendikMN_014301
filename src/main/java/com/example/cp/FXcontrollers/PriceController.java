package com.example.cp.FXcontrollers;

import com.example.cp.Connect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.cp.prices.PricesDB;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.ResourceBundle;

public class PriceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField price_field;
    @FXML
    private ComboBox<String> materials_cb;
    @FXML
    private Button set_price;

    @FXML
    private Label supplier_label;

    @FXML
    private Label warning_label;
    private final ObservableList<String> mat_cb = FXCollections.observableArrayList();
    private static final String BASE_MATERIAL = "http://localhost:8080/material";
    private static final String BASE_PRICE = "http://localhost:8080/price";
    Integer sup_id;
    @FXML
    void initialize(Integer id_supplier, String name) throws IOException, InterruptedException {
        sup_id=id_supplier;
        supplier_label.setText(name);
        materials_cb.getItems().clear();
        materials_cb.setPromptText("Материал");

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_MATERIAL+"/find_materials"))
                .header("Content-Type", "application/json; charset=UTF-8")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            HashSet<String> materials  = objectMapper.readValue(response.body(), HashSet.class);
            mat_cb.addAll(materials);
            materials_cb.setItems(mat_cb.sorted());
        }
    }
    @FXML
    void SetPriceOnAction(ActionEvent event) throws IOException {
        if (materials_cb.getValue()==null || price_field.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля.");
            alert.showAndWait();
        }
        else {
            if (!price_field.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Некорректное значение поля 'Цена'.");
                alert.showAndWait();
            }
            else {
                PricesDB pricesDB = new PricesDB();
                pricesDB.setSupplier(sup_id);
                pricesDB.setMat_name(materials_cb.getValue());
                pricesDB.setPrice(Float.valueOf(price_field.getText().trim()));

                org.apache.http.client.HttpClient client = HttpClients.createDefault();
                HttpPost request = new HttpPost(BASE_PRICE+"/add");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonRequestBody = objectMapper.writeValueAsString(pricesDB);
                StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                entity.setContentType("application/json; charset=UTF-8");
                request.setEntity(entity);
                org.apache.http.HttpResponse response = client.execute(request);
                if(response.getStatusLine().getStatusCode()==200) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ОК");
                    alert.setHeaderText(null);
                    alert.setContentText("Цена установлена.");
                    alert.showAndWait();
                }
            }
        }
    }
    @FXML
    void SearchMaterialsButtonOnAction(ActionEvent event) throws IOException {
        price_field.clear();
        PricesDB pricesDB = new PricesDB();
        pricesDB.setSupplier(sup_id);
        pricesDB.setMat_name(materials_cb.getValue());

        org.apache.http.client.HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BASE_PRICE+"/check");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(pricesDB);
        StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
        entity.setContentType("application/json; charset=UTF-8");
        request.setEntity(entity);
        org.apache.http.HttpResponse response = client.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        if(objectMapper.readValue(responseBody, Boolean.class)){
            HttpPost request_find = new HttpPost(BASE_PRICE+"/find");
            StringEntity entity_find = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
            entity_find.setContentType("application/json; charset=UTF-8");
            request_find.setEntity(entity_find);
            org.apache.http.HttpResponse response_find = client.execute(request_find);
            String responseFind = EntityUtils.toString(response_find.getEntity());

            Float price = objectMapper.readValue(responseFind, Float.class);
            price_field.setText(String.valueOf(price));
        }
    }



}
