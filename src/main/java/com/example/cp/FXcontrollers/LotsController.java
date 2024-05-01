package com.example.cp.FXcontrollers;

import com.example.cp.RestClient;
import com.example.cp.history.HistoryDB;
import com.example.cp.history.HistoryDBProperty;
import com.example.cp.supply_documents.SupplyDocumentsDB;
import com.example.cp.supply_documents.SupplyDocumentsDBProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class LotsController {
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, String> lot_column;

    @FXML
    private ImageView lots_image;

    @FXML
    private Label material_label;

    @FXML
    private TableColumn<SupplyDocumentsDBProperty, Integer> quantity_column;

    @FXML
    private TableView<SupplyDocumentsDBProperty> table;

    private static final String BASE_LOTS = "http://localhost:8080/lots";

    private final ObservableList<SupplyDocumentsDBProperty> tableLotsDBProperties = FXCollections.observableArrayList();

    @FXML
    void initialize(Integer id_material, String number) throws IOException, InterruptedException {
        lots_image.setImage(new Image(RestClient.class.getResource("lots.png").openStream()));
        lot_column.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getLot()));
        quantity_column.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getCurrent_stock()).asObject());
        material_label.setText("Материал: " +number);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_LOTS+"/view/"+id_material))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<SupplyDocumentsDB> supplyDocumentsDBS = Arrays.asList(objectMapper.readValue(response.body(), SupplyDocumentsDB[].class));
            for (SupplyDocumentsDB supplyDocumentsDB : supplyDocumentsDBS) {
                SupplyDocumentsDBProperty e = new SupplyDocumentsDBProperty(supplyDocumentsDB);
                tableLotsDBProperties.add(e);
            }
            table.setItems(tableLotsDBProperties);
        } else {
            System.out.println("Данные не найдены");
        }
    }

}
