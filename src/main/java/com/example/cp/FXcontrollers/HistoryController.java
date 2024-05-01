package com.example.cp.FXcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.cp.history.HistoryDB;
import com.example.cp.history.HistoryDBProperty;

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

public class HistoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<HistoryDBProperty, String> action_column;

    @FXML
    private TableColumn<HistoryDBProperty, String> new_value_column;

    @FXML
    private TableColumn<HistoryDBProperty, String> old_value_column;

    @FXML
    private TableView<HistoryDBProperty> table;

    @FXML
    private TableColumn<HistoryDBProperty, String> time_column;

    @FXML
    private TableColumn<HistoryDBProperty, String> user_column;
    private static final String BASE_HISTORY = "http://localhost:8080/history";

    private final ObservableList<HistoryDBProperty> tableHistoryDBProperties = FXCollections.observableArrayList();
    ArrayList<String> surnames = new ArrayList<>();
    ArrayList<Float> marks = new ArrayList<>();
    @FXML
    void initialize(Integer id_material, String number) throws IOException, InterruptedException {
        user_column.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getUser()));
        time_column.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getDate_time()));
        action_column.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getChanging_type()));
        old_value_column.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getOld_value()));
        new_value_column.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getNew_value()));

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_HISTORY+"/view/"+id_material))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<HistoryDB> historyDBS = Arrays.asList(objectMapper.readValue(response.body(), HistoryDB[].class));
            for (HistoryDB historyDB : historyDBS) {
                HistoryDBProperty e = new HistoryDBProperty(historyDB);
                tableHistoryDBProperties.add(e);
            }
            table.setItems(tableHistoryDBProperties);
        } else {
            System.out.println("Данные не найдены");
        }
    }

}
