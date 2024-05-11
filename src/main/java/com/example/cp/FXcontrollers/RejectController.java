package com.example.cp.FXcontrollers;

import com.example.cp.production_orders.ProductionOrdersDB;
import com.example.cp.supply_documents.SupplyDocumentsDB;
import com.example.cp.supply_documents.SupplyDocumentsDBProperty;
import com.example.cp.write_off.WriteOffDB;
import com.example.cp.write_off.WriteOffDBProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RejectController {
    @FXML
    private Button add_reject;

    @FXML
    private TableColumn<WriteOffDBProperty, String> lot_column;

    @FXML
    private Label material_label;

    @FXML
    private TableColumn<WriteOffDBProperty, Integer> quantity_column;

    @FXML
    private TextField rej_field;

    @FXML
    private TableColumn<WriteOffDBProperty, Integer> reject_column;

    Integer production_order;
    @FXML
    private TableView<WriteOffDBProperty> table;

    private static final String BASE_WRITE_OFF = "http://localhost:8080/write_off";

    private final ObservableList<WriteOffDBProperty> tableLotsDBProperties = FXCollections.observableArrayList();
    List<WriteOffDB> writeOffDBS;
    @FXML
    void initialize(Integer id_material, String mat_num, Integer prod_order) throws IOException, InterruptedException {
        lot_column.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getLot()));
        quantity_column.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getQuantity()).asObject());
        reject_column.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getReject()).asObject());
        material_label.setText("Материал: " + mat_num);
        production_order = prod_order;
        update_table();
    }
    void update_table() throws IOException, InterruptedException {
        table.getItems().clear();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_WRITE_OFF+"/view/"+production_order))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            writeOffDBS = Arrays.asList(objectMapper.readValue(response.body(), WriteOffDB[].class));
            for (WriteOffDB writeOffDB : writeOffDBS) {
                WriteOffDBProperty e = new WriteOffDBProperty(writeOffDB);
                tableLotsDBProperties.add(e);
            }
            table.setItems(tableLotsDBProperties);
        } else {
            System.out.println("Данные не найдены");
        }
        rej_field.setText("");
    }
    @FXML
    void AddRejectedButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (!table.getSelectionModel().isEmpty()&&rej_field.getText().matches("[-+]?\\d+")){
             if (table.getSelectionModel().getSelectedItem().getQuantity()<Integer.parseInt(rej_field.getText())){
                   Alert alert4 = new Alert(Alert.AlertType.ERROR);
                   alert4.setTitle("Ошибка");
                   alert4.setHeaderText(null);
                   alert4.setContentText("Количество брака превышает количество списанного материала.");
                   alert4.showAndWait();
                } else {
                 WriteOffDB writeOffDB = new WriteOffDB();
                 writeOffDB.setId(table.getSelectionModel().getSelectedItem().getId());
                 writeOffDB.setReject(Integer.parseInt(rej_field.getText()));
                 org.apache.http.client.HttpClient client = HttpClients.createDefault();
                 HttpPost request = new HttpPost(BASE_WRITE_OFF + "/add_rejected");
                 ObjectMapper objectMapper = new ObjectMapper();
                 String jsonRequestBody = objectMapper.writeValueAsString(writeOffDB);
                 StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                 entity.setContentType("application/json; charset=UTF-8");
                 request.setEntity(entity);
                 org.apache.http.HttpResponse response = client.execute(request);
                 if(response.getStatusLine().getStatusCode()==200) {
                      Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                      alert3.setTitle("ОК");
                      alert3.setHeaderText(null);
                      alert3.setContentText("Бракованный материал внесён.");
                      alert3.showAndWait();
                     update_table();
                    }
                }
        } else if(table.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Некорректное значение числового поля.");
            alert.showAndWait();
        }
    }
}
