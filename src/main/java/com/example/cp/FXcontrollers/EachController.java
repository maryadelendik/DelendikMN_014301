package com.example.cp.FXcontrollers;

import com.example.cp.RestClient;
import com.example.cp.supply_documents.SupplyDocumentsDB;
import com.example.cp.supply_documents.SupplyDocumentsDBProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
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
    Integer need_check;
    Integer production_order;
    String material;
    
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, String> write_off_column;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, String> lot_column;
    private Callback<TableColumn.CellDataFeatures<SupplyDocumentsDBProperty, Integer>, ObservableValue<Integer>> callback;

    private static final String BASE_LOTS = "http://localhost:8080/lots";
    private static final String BASE_WRITE_OFF = "http://localhost:8080/write_off";

    private final ObservableList<SupplyDocumentsDBProperty> tableLotsDBProperties = FXCollections.observableArrayList();
    List<SupplyDocumentsDB> supplyDocumentsDBS;
    @FXML
    void initialize(Integer id_material, Integer need, String mat_num, Integer prod_order) throws IOException, InterruptedException {
        lot_column.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getLot()));
        quantity_column.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getCurrent_stock()).asObject());
    //    write_off_column.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getWrite_off()).asObject().asString());
     //   Callback<TableColumn<SupplyDocumentsDBProperty, String>, TableCell<SupplyDocumentsDBProperty, String>> cbtc = TextFieldTableCell.<SupplyDocumentsDBProperty>forTableColumn();
     //   write_off_column.setCellFactory(cbtc);
        need_check=need;
        production_order = prod_order;
        material = mat_num;
        write_off_column.setCellFactory(TextFieldTableCell.forTableColumn());
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
            supplyDocumentsDBS = Arrays.asList(objectMapper.readValue(response.body(), SupplyDocumentsDB[].class));
            for (SupplyDocumentsDB supplyDocumentsDB : supplyDocumentsDBS) {
                SupplyDocumentsDBProperty e = new SupplyDocumentsDBProperty(supplyDocumentsDB);
                tableLotsDBProperties.add(e);
            }
            table.setItems(tableLotsDBProperties);
            write_off_column.setOnEditCommit(event -> {
                if (!event.getNewValue().matches("[-+]?\\d+")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Для списания используются нечисловые значения");
                    alert.showAndWait();
                } else {
                    supplyDocumentsDBS.get(event.getTablePosition().getRow()).setWrite_off(Integer.parseInt(event.getNewValue()));
                }
            });
        } else {
            System.out.println("Данные не найдены");
        }
    }
    @FXML
    void WriteOffButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        Integer sum = 0;
        for(int i = 0; i < table.getItems().size(); i++){
            sum +=supplyDocumentsDBS.get(i).getWrite_off();
            supplyDocumentsDBS.get(i).setProd_order(production_order);
            supplyDocumentsDBS.get(i).setMaterial(material);
            supplyDocumentsDBS.get(i).setQuantity(need_check);
            if(supplyDocumentsDBS.get(i).getWrite_off()>supplyDocumentsDBS.get(i).getCurrent_stock())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Количество списываемого материала превышает запасы.");
                alert.showAndWait();
                return;
            }
        }
        if(sum.equals(need_check)){
            org.apache.http.client.HttpClient client = HttpClients.createDefault();
            HttpPost request = new HttpPost(BASE_WRITE_OFF + "/each");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequestBody = objectMapper.writeValueAsString(supplyDocumentsDBS);
            StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
            entity.setContentType("application/json; charset=UTF-8");
            request.setEntity(entity);
            org.apache.http.HttpResponse response = client.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            Integer enough = objectMapper.readValue(responseBody, Integer.class);

            if (response.getStatusLine().getStatusCode() == 200) {
                if (enough == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Недостаточно материала на складе. Списание невозможно.");
                    alert.showAndWait();
                } else {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("ОК");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Материал списан со склада.");
                    alert3.showAndWait();

                    if (enough == 2) {
                        Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                        alert4.setTitle("ВНИМАНИЕ");
                        alert4.setHeaderText(null);
                        alert4.setContentText("На складе осталось меньше 10 единиц материала.");
                        alert4.showAndWait();
                    }
                    write_off.getScene().getWindow().hide();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Введенные числа не соответствуют количеству материала для списания");
            alert.showAndWait();
        }
    }
}
