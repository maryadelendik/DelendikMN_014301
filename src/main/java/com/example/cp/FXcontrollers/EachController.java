package com.example.cp.FXcontrollers;

import com.example.cp.supply_documents.SupplyDocumentsDB;
import com.example.cp.supply_documents.SupplyDocumentsDBProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.scene.control.TableCell;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class EachController {

    @FXML
    private Label material_label;

    @FXML
    private Label number;

    @FXML
    private TableColumn<SupplyDocumentsDBProperty, Integer> quantity_column;

    @FXML
    private TableView<SupplyDocumentsDBProperty> table;

    @FXML
    private Button write_off;

    @FXML
    private TableColumn<SupplyDocumentsDBProperty, String> write_off_column;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, String> lot_column;
    private Callback<TableColumn.CellDataFeatures<SupplyDocumentsDBProperty, Integer>, ObservableValue<Integer>> callback;

    private static final String BASE_LOTS = "http://localhost:8080/lots";
    private static final String BASE_WRITE_OFF = "http://localhost:8080/write_off";

    private final ObservableList<SupplyDocumentsDBProperty> tableLotsDBProperties = FXCollections.observableArrayList();

    @FXML
    void initialize(Integer id_material, Integer need, String mat_num) throws IOException, InterruptedException {
        lot_column.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getLot()));
        quantity_column.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getCurrent_stock()).asObject());
        Callback<TableColumn<SupplyDocumentsDBProperty, String>, TableCell<SupplyDocumentsDBProperty, String>> cbtc = TextFieldTableCell.<SupplyDocumentsDBProperty>forTableColumn();
        write_off_column.setCellFactory(cbtc);
        number.setText(String.valueOf(need));
        material_label.setText("Материал: " + mat_num);
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
