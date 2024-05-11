package com.example.cp.FXcontrollers;

import com.example.cp.RestClient;
import com.example.cp.authorization.Authorization;
import com.example.cp.history.HistoryDB;
import com.example.cp.materials.MaterialsDB;
import com.example.cp.materials.MaterialsDBProperty;
import com.example.cp.materials.SearchByType;
import com.example.cp.prices.PricesDB;
import com.example.cp.production_orders.ProductionOrdersDB;
import com.example.cp.production_orders.ProductionOrdersDBProperty;
import com.example.cp.production_orders.Report;
import com.example.cp.production_orders.SearchProductionOrders;
import com.example.cp.suppliers.SuppliersDB;
import com.example.cp.suppliers.SuppliersDBProperty;
import com.example.cp.supply_documents.SupplyDocumentsDB;
import com.example.cp.supply_documents.SupplyDocumentsDBProperty;
import com.example.cp.users.UsersDB;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class AdminMenuController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button add_material;
    @FXML
    private Button align_button;
    @FXML
    private Button add_supplier;
    @FXML
    private TextField addr_sup;
    @FXML
    private TextArea report_text;
    @FXML
    private TableColumn<SuppliersDBProperty, String> address_col;
    @FXML
    private Label choose_role;
    @FXML
    private Button clean_button;
    @FXML
    private Label create_login_label;
    @FXML
    private Button create_user;
    @FXML
    private Button delete_material;
    @FXML
    private Button delete_sup;
    @FXML
    private Button delete_user;
    @FXML
    private Button edit_material;
    @FXML
    private Button edit_supplier;
    @FXML
    private Button lots_button;
    @FXML
    private TableColumn<SuppliersDBProperty, String> email_col;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, Integer> rej_sup_col;
    @FXML
    private TextField email_sup;
    @FXML
    private Button exit_button;
    @FXML
    private Button change_password;
    @FXML
    private PasswordField first_password;
    @FXML
    private Button history_materials;
    @FXML
    private Button load_button;
    @FXML
    private Tab home;
    @FXML
    private Label login_label;
    @FXML
    private TableColumn<SuppliersDBProperty, String> name_col;
    @FXML
    private TextField name_mat;
    @FXML
    private TableColumn<MaterialsDBProperty, String> name_mat_col;
    @FXML
    private TextField name_sup;
    @FXML
    private TableColumn<MaterialsDBProperty, String> number_col;
    @FXML
    private TextField number_mat;
    @FXML
    private DatePicker date_from;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, Float> price_item_col;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, Integer> month_leftovers_col;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, String> lot_col;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, Integer> current_stock_col;
    @FXML
    private DatePicker date_to;
    @FXML
    private Button consumption_choice;
    @FXML
    private Button quality_choice;
    @FXML
    private Button suppliers_choice;
    @FXML
    private TableColumn<MaterialsDBProperty,Integer> order_quant_col;
    @FXML
    private TableColumn<SuppliersDBProperty, String> phone_col;
    @FXML
    private TextField phone_sup;
    @FXML
    private Rectangle rectangle_create_user;
    @FXML
    private ComboBox<String> roles;
    @FXML
    private Button save_user;
    @FXML
    private TextField search_field;
    @FXML
    private TextField search_field_mat;
   // @FXML
   // private TextField stock_mat;
    @FXML
    private TableColumn<MaterialsDBProperty, Integer> stock_quant_col;
    @FXML
    private Tab suppliers_tab;
    @FXML
    private Tab materials_tab;
    @FXML
    private TableView<MaterialsDBProperty> table_materials;
    @FXML
    private TableView<SuppliersDBProperty> table_suppliers;
    @FXML
    private TableColumn<MaterialsDBProperty, String> type_col;
    @FXML
    private TextField unit;
    @FXML
    private RadioButton each_rb;
    @FXML
    private RadioButton fifo_rb;
    @FXML
    private RadioButton mid_rb;
    @FXML
    private TableColumn<MaterialsDBProperty, String> unit_col;
    @FXML
    private ImageView menu_picture;
    @FXML
    private TextField type_mat;
    @FXML
    private ComboBox<String> type_mat_comb;
    @FXML
    private TextField user_login;
    @FXML
    private Tab users;
    @FXML
    private Button view_prices;
    @FXML
    private Button add_document;
    @FXML
    private DatePicker date_doc;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, String> date_doc_col;
    @FXML
    private Button delete_document;
    @FXML
    private Button edit_document;
    @FXML
    private Tab supply_tab;
    @FXML
    private Button generate_price;
    @FXML
    private TextField lot_doc;
    @FXML
    private TextField mat_doc;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, String> mat_doc_col;
    @FXML
    private TextField num_doc;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, String> num_doc_col;
    @FXML
    private Label label1;

    @FXML
    private Label label2;
    @FXML
    private TextField price_doc;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, Float> price_doc_col;
    @FXML
    private TextField quant_doc;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, Integer> quant_doc_col;
    @FXML
    private TextField search_field_doc;
    @FXML
    private ComboBox<String> sup_doc;
    @FXML
    private TableColumn<SupplyDocumentsDBProperty, String> sup_doc_col;
    @FXML
    private TableView<SupplyDocumentsDBProperty> table_supplies;
    @FXML
    private DatePicker date_filter_po;
    @FXML
    private TextField search_field_po;
    @FXML
    private CheckBox open_check;
    @FXML
    private TextField material_po;
    @FXML
    private DatePicker date_po;
    @FXML
    private Button show_diagram;
    @FXML
    private Button show_report;
    @FXML
    private ComboBox<String> mat_report;
    @FXML
    private TableColumn<ProductionOrdersDBProperty, String> date_po_col;
    @FXML
    private Button delete_po;
    @FXML
    private ImageView user_image;
    @FXML
    private Button edit_po;
    @FXML
    private CheckBox avg_check;
    @FXML
    private TableColumn<ProductionOrdersDBProperty, String> mat_po_col;
    @FXML
    private TextField need_quant_po;
    @FXML
    private Button add_po;
    @FXML
    private TableView<ProductionOrdersDBProperty> table_po;
    @FXML
    private Button wrire_off;
    @FXML
    private Button add_reject;
    @FXML
    private Button back_write;
    @FXML
    private TableColumn<ProductionOrdersDBProperty, Integer> quant_po_col;
    @FXML
    private TableColumn<ProductionOrdersDBProperty, Integer> rej_po_col;
    @FXML
    private Tab prod_orders_tab;
    @FXML
    private Tab report_tab;
    @FXML
    private ImageView blue;

    @FXML
    private TabPane tab_pane;
    @FXML
    private ImageView green;
    ArrayList<Report> report_gist = new ArrayList<>();
    String for_report;
    private Integer report_type;
    @FXML
    private ImageView red;
    private final ObservableList<String> roles_cb = FXCollections.observableArrayList();
    private final ObservableList<String> mat_cb = FXCollections.observableArrayList();
    private final ObservableList<SuppliersDBProperty> tableSuppliersDBProperties = FXCollections.observableArrayList();
    private final ObservableList<SuppliersDBProperty> tableSearchSuppliersDBProperties = FXCollections.observableArrayList();
    private final ObservableList<ProductionOrdersDBProperty> tableProductionOrdersDBProperties = FXCollections.observableArrayList();
    private final ObservableList<String> types_cb = FXCollections.observableArrayList();
    private final ObservableList<String> suppliers_cb = FXCollections.observableArrayList();
    private final ObservableList<MaterialsDBProperty> tableMaterialsDBProperties = FXCollections.observableArrayList();
    private final ObservableList<SupplyDocumentsDBProperty> tableSupplyDocumentsDBProperties = FXCollections.observableArrayList();
    private final ObservableList<MaterialsDBProperty> tableSearchMaterialsDBProperties = FXCollections.observableArrayList();
    private String main_login;
    private static final String BASE_PRICE = "http://localhost:8080/price";
    private static final String BASE_SUPPLIER = "http://localhost:8080/supplier";
    private static final String BASE_MATERIAL = "http://localhost:8080/material";
    private static final String BASE_SUPPLY_DOCUMENTS = "http://localhost:8080/supply_document";
    private static final String BASE_PRODUCTION_ORDER = "http://localhost:8080/production_order";
    private static final String BASE_USER = "http://localhost:8080/user";
    private static final String BASE_HISTORY = "http://localhost:8080/history";
    private static final String BASE_REPORT = "http://localhost:8080/report";
    private static final String BASE_WRITE_OFF = "http://localhost:8080/write_off";
    private PseudoClass open = PseudoClass.getPseudoClass("open");
    private PseudoClass closed = PseudoClass.getPseudoClass("closed");
    private PseudoClass reject = PseudoClass.getPseudoClass("reject");
    private PseudoClass little = PseudoClass.getPseudoClass("little");
    @FXML
    void initialize(Authorization auth_menu) throws IOException, InterruptedException {
        if(!auth_menu.getRole()){
            tab_pane.getTabs().remove(users);
            tab_pane.getTabs().remove(suppliers_tab);
            suppliers_choice.setVisible(false);
        }
        main_login=auth_menu.getLogin();
        home.setGraphic(buildImage("user.png", 16));
        name_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getName()));
        address_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getAddress()));
        email_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getEmail()));
        phone_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getPhone_number()));

        name_mat_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getName()));
        number_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getNumber()));
        type_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getType()));
        stock_quant_col.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getStock_quantity()).asObject());
        order_quant_col.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getOrder_quantity()).asObject());
        unit_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getUnit()));

        num_doc_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getNumber()));
        mat_doc_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getMaterial()));
        sup_doc_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getSupplier()));
        quant_doc_col.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getQuantity()).asObject());
        price_doc_col.setCellValueFactory(field -> new SimpleFloatProperty(field.getValue().getPrice()).asObject());
        date_doc_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getDate()));
        price_item_col.setCellValueFactory(field -> new SimpleFloatProperty(field.getValue().getPrice_item()).asObject());
        lot_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getLot()));
        current_stock_col.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getCurrent_stock()).asObject());
        month_leftovers_col.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getMonth_leftovers()).asObject());
        rej_sup_col.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getRejected()).asObject());

        mat_po_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getNumber_material()));
        date_po_col.setCellValueFactory(field -> new SimpleStringProperty(field.getValue().getDate()));
        quant_po_col.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getNeed_quantity()).asObject());
        rej_po_col.setCellValueFactory(field -> new SimpleIntegerProperty(field.getValue().getReject_quantity()).asObject());

        red.setImage(new Image(RestClient.class.getResource("red.png").openStream()));
        blue.setImage(new Image(RestClient.class.getResource("blue.png").openStream()));
        green.setImage(new Image(RestClient.class.getResource("green.png").openStream()));
        menu_picture.setImage(new Image(RestClient.class.getResource("menu_picture.jpg").openStream()));
        user_image.setImage(new Image(RestClient.class.getResource("user_image.png").openStream()));
        Tooltip red_tooltip =new Tooltip("Заявка с браком");
        Tooltip blue_tooltip =new Tooltip("Открытая заявка");
        Tooltip green_tooltip =new Tooltip("Закрытая заявка");
        Tooltip.install(red, red_tooltip);
        Tooltip.install(blue, blue_tooltip);
        Tooltip.install(green, green_tooltip);

        login_label.setText(auth_menu.getLogin());
        user_login.textProperty().addListener(
                (observable, oldValue, newValue) ->create_login_label.setText(newValue));
        search_field.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        search_suppliers(newValue);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        search_field_mat.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        search_materials(newValue);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        search_field_doc.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        search_supplyDocuments(newValue);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        search_field_po.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        search_productionOrders(newValue);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        update_suppliers();
        update_materials();
        update_supplies();
        update_production_orders();
    }
    private static ImageView buildImage(String path, Integer size) throws IOException {
        Image i = new Image(RestClient.class.getResource(path).openStream());
        ImageView imageView = new ImageView();
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        imageView.setImage(i);
        return imageView;
    }
    void update_suppliers() throws IOException, InterruptedException {
        table_suppliers.getItems().clear();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_SUPPLIER+"/view_all"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<SuppliersDB> suppliersDBS = Arrays.asList(objectMapper.readValue(response.body(), SuppliersDB[].class));
            for (SuppliersDB suppliersDB : suppliersDBS) {
                SuppliersDBProperty e = new SuppliersDBProperty(suppliersDB);
                tableSuppliersDBProperties.add(e);
            }
            table_suppliers.setItems(tableSuppliersDBProperties);
        } else {
            System.out.println("Данные не найдены");
        }
        name_sup.setText("");
        email_sup.setText("");
        phone_sup.setText("");
        addr_sup.setText("");
        search_field.setText("");
    }
    void update_materials() throws IOException, InterruptedException {
        table_materials.getItems().clear();
        table_materials.setStyle("-fx-background-color: transparent;");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_MATERIAL+"/view_all"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<MaterialsDB> materialsDBS = Arrays.asList(objectMapper.readValue(response.body(), MaterialsDB[].class));
        for (MaterialsDB materialsDB : materialsDBS) {
            MaterialsDBProperty e = new MaterialsDBProperty(materialsDB);
            tableMaterialsDBProperties.add(e);
        }
        table_materials.setItems(tableMaterialsDBProperties);
        } else {
            System.out.println("Данные не найдены");
        }
        name_mat.setText("");
        number_mat.setText("");
        type_mat.setText("");
        //stock_mat.setText("");
        unit.setText("");
        table_materials.refresh();
        table_materials.setRowFactory(tv -> new TableRow<MaterialsDBProperty>() {
            protected void updateItem(MaterialsDBProperty item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("");
                pseudoClassStateChanged(little,false);
                if (item == null )
                    setStyle("");
                else if (item.getStock_quantity()<10) {
                    setStyle("-fx-background-color: #FFDCDC;");
                    pseudoClassStateChanged(little,true);
                }
            }
        });
        update_types();
    }
    void update_supplies() throws IOException, InterruptedException {
        table_supplies.getItems().clear();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_SUPPLY_DOCUMENTS+"/view_all"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<SupplyDocumentsDB> supplyDocumentsDBS = Arrays.asList(objectMapper.readValue(response.body(), SupplyDocumentsDB[].class));
            for (SupplyDocumentsDB supplyDocumentsDB : supplyDocumentsDBS) {
                SupplyDocumentsDBProperty e = new SupplyDocumentsDBProperty(supplyDocumentsDB);
                tableSupplyDocumentsDBProperties.add(e);
            }
            table_supplies.setItems(tableSupplyDocumentsDBProperties);
        }
        num_doc.setText("");
        mat_doc.setText("");
        quant_doc.setText("");
        price_doc.setText("");
        date_doc.setValue(null);
        lot_doc.setText("");
        search_field.setText("");
        update_suppliers_doc();
    }
    void update_production_orders() throws IOException, InterruptedException {
        table_po.getItems().clear();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_PRODUCTION_ORDER+"/view_all"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductionOrdersDB> productionOrdersDBS = Arrays.asList(objectMapper.readValue(response.body(), ProductionOrdersDB[].class));
            for (ProductionOrdersDB productionOrdersDB : productionOrdersDBS) {
                ProductionOrdersDBProperty e = new ProductionOrdersDBProperty(productionOrdersDB);
                tableProductionOrdersDBProperties.add(e);
            }
            table_po.setItems(tableProductionOrdersDBProperties);
        }
        material_po.setText("");
        need_quant_po.setText("");
        date_po.setValue(null);

        table_po.setRowFactory(tv -> new TableRow<ProductionOrdersDBProperty>() {
            protected void updateItem(ProductionOrdersDBProperty item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null )
                    setStyle("");
                else if (item.getReject_quantity()!=0) {
                    setStyle("-fx-background-color: #FFDCDC;");
                    pseudoClassStateChanged(reject,true);
                } else{
                    if (item.isStatus()){
                        setStyle("-fx-background-color: #DCFFDC;");
                    pseudoClassStateChanged(closed,true);}
                    else{
                        setStyle("-fx-background-color: #DCDCFF;");
                    pseudoClassStateChanged(open,true);}
                }
            }
        });
    }

    @FXML
    void CleanButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        search_field_mat.setText("");
     //   type_mat_comb.getItems().clear();
        update_materials();
    }
    void update_types() throws IOException, InterruptedException {
        ObservableList<String> emptyList = FXCollections.observableArrayList();
        type_mat_comb.setItems(emptyList);
        types_cb.clear();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_MATERIAL+"/find_types"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            HashSet<String> types = objectMapper.readValue(response.body(), HashSet.class);
            types_cb.addAll(types);
        }

       // type_mat_comb.getItems().clear();
        type_mat_comb.setEditable(true);
        type_mat_comb.setPromptText("Тип материала");
        type_mat_comb.setEditable(false);

        type_mat_comb.setItems(types_cb.sorted());
    }
    void update_suppliers_doc() throws IOException, InterruptedException {
        suppliers_cb.clear();
        sup_doc.getItems().clear();
      //  sup_doc.setPromptText("Поставщик");
        sup_doc.getSelectionModel().select("Поставщик");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_SUPPLIER+"/find_suppliers"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            HashSet<String> suppliers = objectMapper.readValue(response.body(), HashSet.class);
            suppliers_cb.addAll(suppliers);
            sup_doc.setItems(suppliers_cb.sorted());
        }
    }
    boolean check_if_exists_material(String number) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String encodedParam = URLEncoder.encode(number, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_MATERIAL+"/check_if_exists/"+encodedParam))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), Boolean.class);
    }
    boolean check_if_exists_supplier(String name) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String encodedParam = URLEncoder.encode(name, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_SUPPLIER+"/check_if_exists/"+encodedParam))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), Boolean.class);
    }
    boolean check_material_for_update(MaterialsDB materialsDB) throws IOException {
        org.apache.http.client.HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BASE_MATERIAL+"/check_for_update");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(materialsDB);
        StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
        entity.setContentType("application/json; charset=UTF-8");
        request.setEntity(entity);
        org.apache.http.HttpResponse response = client.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        return objectMapper.readValue(responseBody, Boolean.class);
    }
    boolean check_supplier_for_update(SuppliersDB suppliersDB) throws IOException {
        org.apache.http.client.HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BASE_SUPPLIER+"/check_for_update");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(suppliersDB);
        StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
        entity.setContentType("application/json; charset=UTF-8");
        request.setEntity(entity);
        org.apache.http.HttpResponse response = client.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        return objectMapper.readValue(responseBody, Boolean.class);
    }
    void check_search() throws IOException, InterruptedException {
        if(!search_field_mat.getText().trim().equals("")){
            search_materials(search_field_mat.getText());
        }
        else if(search_field_mat.getText()==null&&type_mat_comb.getValue()!=null){
            search_by_types();
        }
        else{
            update_materials();
        }
    }
    void check_search_suppliers() throws IOException, InterruptedException {
        if(!Objects.equals(search_field.getText().trim(), "")){
            search_suppliers(search_field.getText());
        }
        else{
            update_suppliers();
        }
    }
    void check_search_supply_documents() throws IOException, InterruptedException {
        if(!search_field_doc.getText().trim().equals("")){
            search_supplyDocuments(search_field_doc.getText());
        }
        else{
            update_supplies();
        }
    }
    void search_by_types() throws IOException, InterruptedException {
        table_materials.getItems().clear();

        HttpClient httpClient = HttpClient.newHttpClient();
        String encodedParam = URLEncoder.encode(type_mat_comb.getSelectionModel().getSelectedItem(), StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_MATERIAL+"/view_by_type?type="+encodedParam))
                .header("Content-Type", "application/json; charset=UTF-8")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        List<MaterialsDB> materialsDBS = Arrays.asList(objectMapper.readValue(response.body(), MaterialsDB[].class));

       /* org.apache.http.client.HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BASE_MATERIAL+"/view_by_type");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(type_mat_comb.getValue());
        StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
        entity.setContentType("application/json; charset=UTF-8");
        request.setEntity(entity);
        org.apache.http.HttpResponse response = client.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        List<MaterialsDB> materialsDBS = Arrays.asList(objectMapper.readValue(responseBody, MaterialsDB[].class));*/
        for (MaterialsDB materialsDB : materialsDBS) {
            MaterialsDBProperty e = new MaterialsDBProperty(materialsDB);
            tableMaterialsDBProperties.add(e);
        }
        table_materials.setItems(tableMaterialsDBProperties);
        name_mat.setText("");
        number_mat.setText("");
        type_mat.setText("");
        unit.setText("");
        //stock_mat.setText("");
    //    HttpClient httpClient = HttpClient.newHttpClient();
        String value = type_mat_comb.getSelectionModel().getSelectedItem();

    /*    ObservableList<String> emptyList = FXCollections.observableArrayList();
        type_mat_comb.setItems(emptyList);*/
        types_cb.clear();
        type_mat_comb.getItems().clear();
        ObjectMapper objectMapper_types = new ObjectMapper();
        HttpRequest request_types = HttpRequest.newBuilder()
                .uri(URI.create(BASE_MATERIAL+"/find_types"))
                .header("Content-Type", "application/json; charset=UTF-8")
                .GET()
                .build();
        HttpResponse<String> response_types = httpClient.send(request_types, HttpResponse.BodyHandlers.ofString());
        if (response_types.statusCode() == 200) {
            HashSet<String> types = objectMapper_types.readValue(response_types.body(), HashSet.class);
            types_cb.addAll(types);
        }
        type_mat_comb.setItems(types_cb.sorted());

        //type_mat_comb.setValue(value);
       // type_mat_comb.setItems(types_cb);
        type_mat_comb.getSelectionModel().select(value);
    /*    type_mat_comb.setEditable(true);
        type_mat_comb.setPromptText(value);
        type_mat_comb.setEditable(false);*/

        table_materials.refresh();
        table_materials.setRowFactory(tv -> new TableRow<MaterialsDBProperty>() {
            protected void updateItem(MaterialsDBProperty item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null )
                    setStyle("");
                else if (item.getStock_quantity()<15) {
                    setStyle("-fx-background-color: #FFDCDC;");
                    pseudoClassStateChanged(little,true);
                }
            }
        });
            }
    @FXML
    void SearchByTypesButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
       if(type_mat_comb.getValue()!=null){
           search_by_types();
        }
    }
    @FXML
    void SuppTableMouseClicked(MouseEvent event) {
        name_sup.setText(table_suppliers.getSelectionModel().getSelectedItem().getName());
        email_sup.setText(table_suppliers.getSelectionModel().getSelectedItem().getEmail());
        phone_sup.setText(table_suppliers.getSelectionModel().getSelectedItem().getPhone_number());
        addr_sup.setText(table_suppliers.getSelectionModel().getSelectedItem().getAddress());
    }
    @FXML
    void MatTableMouseClicked(MouseEvent event) {
        name_mat.setText(table_materials.getSelectionModel().getSelectedItem().getName());
        number_mat.setText(table_materials.getSelectionModel().getSelectedItem().getNumber());
        type_mat.setText(table_materials.getSelectionModel().getSelectedItem().getType());
      //  stock_mat.setText(String.valueOf(table_materials.getSelectionModel().getSelectedItem().getStock_quantity()));
        unit.setText(table_materials.getSelectionModel().getSelectedItem().getUnit());
    }
    @FXML
    void POTableMouseClicked(MouseEvent event) throws ParseException {
        material_po.setText(table_po.getSelectionModel().getSelectedItem().getNumber_material());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat oldDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        java.util.Date date = oldDateFormat.parse(table_po.getSelectionModel().getSelectedItem().getDate());
        String result = newDateFormat.format(date);
        date_po.setValue(LocalDate.parse(result));
        need_quant_po.setText(String.valueOf(table_po.getSelectionModel().getSelectedItem().getNeed_quantity()));
    }
    @FXML
    void SDTableMouseClicked(MouseEvent event) throws ParseException {
        num_doc.setText(table_supplies.getSelectionModel().getSelectedItem().getNumber());
        mat_doc.setText(table_supplies.getSelectionModel().getSelectedItem().getMaterial());
        quant_doc.setText(String.valueOf(table_supplies.getSelectionModel().getSelectedItem().getQuantity()));
        price_doc.setText(String.valueOf(table_supplies.getSelectionModel().getSelectedItem().getPrice()));
        sup_doc.setValue(table_supplies.getSelectionModel().getSelectedItem().getSupplier());
        lot_doc.setText(table_supplies.getSelectionModel().getSelectedItem().getLot());
      //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      //  date_doc.setValue(LocalDate.parse(table_supplies.getSelectionModel().getSelectedItem().getDate(), formatter));

        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat oldDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        java.util.Date date = oldDateFormat.parse(table_supplies.getSelectionModel().getSelectedItem().getDate());
        String result = newDateFormat.format(date);
        date_doc.setValue(LocalDate.parse(result));
    }

    @FXML
    void CreateUserButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if(!user_login.getText().isEmpty()) {
            HttpClient httpClient = HttpClient.newHttpClient();
            String encodedParam = URLEncoder.encode(user_login.getText(), StandardCharsets.UTF_8);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_USER + "/check_if_exists/" + encodedParam))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            Integer res = objectMapper.readValue(response.body(), Integer.class);
            if (response.statusCode() == 200) {
                if (res == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Пользователь с таким логином уже существует!");
                    alert.showAndWait();
                } else {
                    roles.setPromptText("Роль");
                    roles_cb.add("Ответственный");
                    roles_cb.add("Работник склада");
                    rectangle_create_user.setVisible(true);
                    create_login_label.setText(user_login.getText());
                    create_login_label.setVisible(true);
                    first_password.setVisible(true);
                    choose_role.setVisible(true);
                    roles.setVisible(true);
                    save_user.setVisible(true);
                    label1.setVisible(true);
                    label2.setVisible(true);
                    roles.setItems(roles_cb);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Пустое поле");
                alert.setHeaderText(null);
                alert.setContentText("Введите логин.");
                alert.showAndWait();
            }
        }
    }
    @FXML
    void DeleteUserButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (Objects.equals(user_login.getText(), main_login)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Удаление текущего пользователя невозможно");
            alert.showAndWait();
        } else if (!user_login.getText().isEmpty()) {
                HttpClient httpCheck = HttpClient.newHttpClient();
                String encodedParam = URLEncoder.encode(user_login.getText(), StandardCharsets.UTF_8);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BASE_USER + "/check_if_exists/" + encodedParam))
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();
                HttpResponse<String> response_check = httpCheck.send(request, HttpResponse.BodyHandlers.ofString());
                ObjectMapper objectMapper = new ObjectMapper();
                Integer res = objectMapper.readValue(response_check.body(), Integer.class);
                if (response_check.statusCode() == 200) {
                    if (res == 0) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Уточнение");
                        alert.setHeaderText(null);
                        alert.setContentText("Удалить пользователя " + user_login.getText() + "?");
                        Optional<ButtonType> option = alert.showAndWait();
                        if (option.get() == ButtonType.OK) {
                            CloseableHttpClient httpClient = HttpClients.createDefault();
                            HttpPost httpPost = new HttpPost(BASE_USER + "/delete/" + user_login.getText());
                            org.apache.http.HttpResponse response = httpClient.execute(httpPost);
                            if (response.getStatusLine().getStatusCode() != 200) {
                                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                alert2.setTitle("Ошибка");
                                alert2.setHeaderText(null);
                                alert2.setContentText("Произошёл сбой. Попробуйте ещё раз.");
                                alert2.showAndWait();
                            } else {
                                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                                alert3.setTitle("ОК");
                                alert3.setHeaderText(null);
                                alert3.setContentText("Пользователь " + user_login.getText() + " удалён.");
                                alert3.showAndWait();
                                user_login.setText("");
                            }
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка");
                        alert.setHeaderText(null);
                        alert.setContentText("Пользователя с таким логином не существует!");
                        alert.showAndWait();
                    }
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Пустое поле");
                alert.setHeaderText(null);
                alert.setContentText("Введите логин.");
                alert.showAndWait();
            }
    }
    @FXML
    void SaveUserButtonOnAction(ActionEvent event) throws IOException {
        if (!user_login.getText().isEmpty() && !first_password.getText().isEmpty() && roles.getValue() != null) {
            UsersDB usersDB = new UsersDB();
            usersDB.setLogin(user_login.getText().trim());
            usersDB.setFirst_password(first_password.getText());
            usersDB.setRole(Objects.equals(roles.getValue(), "Ответственный"));

            org.apache.http.client.HttpClient client = HttpClients.createDefault();
            HttpPost request = new HttpPost(BASE_USER + "/save");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequestBody = objectMapper.writeValueAsString(usersDB);
            StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
            entity.setContentType("application/json; charset=UTF-8");
            request.setEntity(entity);
            org.apache.http.HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Произошёл сбой. Попробуйте ещё раз.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ОК");
                alert.setHeaderText(null);
                alert.setContentText("Пользователь добавлен. Для входа в систему сообщите ему логин и начальный пароль.");
                alert.showAndWait();

                user_login.setText("");
                first_password.setText("");
                roles.getItems().clear();
                roles.setPromptText("Роль");
                rectangle_create_user.setVisible(false);
                first_password.setVisible(false);
                choose_role.setVisible(false);
                roles.setVisible(false);
                save_user.setVisible(false);
                create_login_label.setVisible(false);
                label1.setVisible(false);
                label2.setVisible(false);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля.");
            alert.showAndWait();
        }

    }
    @FXML
    void ViewPricesButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (!table_suppliers.getSelectionModel().isEmpty()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(RestClient.class.getResource("prices.fxml"));
            fxmlLoader.load();
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Цены поставщиков");
            stage.setScene(new Scene(root));
            PriceController controller = fxmlLoader.getController();
            controller.initialize(table_suppliers.getSelectionModel().getSelectedItem().getId(), table_suppliers.getSelectionModel().getSelectedItem().getName());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }
    }
    @FXML
    void AddSupplierButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (name_sup.getText().isEmpty() || email_sup.getText().isEmpty()||phone_sup.getText().isEmpty() ||
                addr_sup.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля.");
            alert.showAndWait();
        }
        else {
            if(check_if_exists_supplier(name_sup.getText().trim())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Данный поставщик уже есть в системе в системе.");
                alert.showAndWait();
            }else {
                SuppliersDB suppliersDB = new SuppliersDB();
                suppliersDB.setPhone_number(phone_sup.getText().trim());
                suppliersDB.setEmail(email_sup.getText().trim());
                suppliersDB.setAddress(addr_sup.getText().trim());
                suppliersDB.setName(name_sup.getText().trim());

                org.apache.http.client.HttpClient client = HttpClients.createDefault();
                HttpPost request = new HttpPost(BASE_SUPPLIER+"/add");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonRequestBody = objectMapper.writeValueAsString(suppliersDB);
                StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                entity.setContentType("application/json; charset=UTF-8");
                request.setEntity(entity);
                org.apache.http.HttpResponse response = client.execute(request);

                if(response.getStatusLine().getStatusCode()==200) {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("ОК");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Поставщик добавлен.");
                    alert3.showAndWait();
                }
                check_search_suppliers();
                update_suppliers_doc();
            }
        }

    }
    @FXML
    void DeleteSupplierButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (!table_suppliers.getSelectionModel().isEmpty()){
            String name = table_suppliers.getSelectionModel().getSelectedItem().getName();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Уточнение");
            alert.setHeaderText(null);
            alert.setContentText("Удалить поставщика " + name +"?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BASE_SUPPLIER+"/delete/"+table_suppliers.getSelectionModel().getSelectedItem().getId()))
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    check_search_suppliers();
                    if(response.statusCode()==200){
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("ОК");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Поставщик "+ name+" удалён.");
                    alert3.showAndWait();
                    user_login.setText("");}
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }
    }
    @FXML
    void EditSupplierButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (table_suppliers.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Выберите поставщика из таблицы.");
            alert.showAndWait();
        }
        else {
            if (name_sup.getText().isEmpty() || email_sup.getText().isEmpty() || phone_sup.getText().isEmpty() ||
                    addr_sup.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля.");
                alert.showAndWait();
            } else {
                SuppliersDB suppliersDB = new SuppliersDB();
                suppliersDB.setId(table_suppliers.getSelectionModel().getSelectedItem().getId());
                suppliersDB.setPhone_number(phone_sup.getText().trim());
                suppliersDB.setEmail(email_sup.getText().trim());
                suppliersDB.setAddress(addr_sup.getText().trim());
                suppliersDB.setName(name_sup.getText().trim());

                if (!check_supplier_for_update(suppliersDB)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Данный поставщик уже есть в системе.");
                    alert.showAndWait();
                }
                else {
                    org.apache.http.client.HttpClient client = HttpClients.createDefault();
                    HttpPost request = new HttpPost(BASE_SUPPLIER+"/update");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonRequestBody = objectMapper.writeValueAsString(suppliersDB);
                    StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                    entity.setContentType("application/json; charset=UTF-8");
                    request.setEntity(entity);
                    client.execute(request);

                    check_search_suppliers();
                }
             }

        }
    }
    @FXML
    void AddMaterialButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (name_mat.getText().isEmpty() || number_mat.getText().isEmpty()||type_mat.getText().isEmpty() ||
                 unit.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля.");
            alert.showAndWait();
        }
        else {
        /*    if (!stock_mat.getText().matches("[-+]?\\d+")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Некорректное значение поля 'Количество на складе'.");
                alert.showAndWait();
            }   else*/ if(check_if_exists_material(number_mat.getText().trim())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Данный материал уже есть в системе.");
                alert.showAndWait();
            }
            else {
                MaterialsDB materialsDB = new MaterialsDB();
                materialsDB.setName(name_mat.getText().trim());
                materialsDB.setNumber(number_mat.getText().trim());
                materialsDB.setType(type_mat.getText().trim());
               // materialsDB.setStock_quantity(Integer.valueOf(stock_mat.getText().trim()));
                materialsDB.setUser_login(main_login);
                materialsDB.setUnit(unit.getText().trim());

                org.apache.http.client.HttpClient client = HttpClients.createDefault();
                HttpPost request = new HttpPost(BASE_MATERIAL+"/add");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonRequestBody = objectMapper.writeValueAsString(materialsDB);
                StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                entity.setContentType("application/json; charset=UTF-8");
                request.setEntity(entity);
                org.apache.http.HttpResponse response = client.execute(request);

                if(response.getStatusLine().getStatusCode()==200) {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("ОК");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Материал добавлен.");
                    alert3.showAndWait();
                }

              check_search();
            }

        }

    }
    @FXML
    void DeleteMaterialButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
            if (!table_materials.getSelectionModel().isEmpty()){
            String number =table_materials.getSelectionModel().getSelectedItem().getNumber();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Уточнение");
            alert.setHeaderText(null);
            alert.setContentText("Удалить материал " +number +"?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BASE_MATERIAL+"/delete/"+table_materials.getSelectionModel().getSelectedItem().getId()))
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if(response.statusCode()==200){
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("ОК");
                alert3.setHeaderText(null);
                alert3.setContentText("Материал "+ number+" удалён.");
                alert3.showAndWait();
                }
                check_search();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }
    }
    @FXML
    void EditMaterialButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (table_materials.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Выберите материал из таблицы.");
            alert.showAndWait();
        }
        else {
            if (name_mat.getText().isEmpty() || number_mat.getText().isEmpty()||type_mat.getText().isEmpty() ||
                    unit.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля.");
                alert.showAndWait();
            } else {
                if (!Objects.equals(number_mat.getText(), table_materials.getSelectionModel().getSelectedItem().getNumber())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Номенклатурный номер не может быть изменен.");
                    alert.showAndWait();
                /*(!stock_mat.getText().matches("[-+]?\\d+")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Некорректное начение поля 'Количество на складе'.");
                    alert.showAndWait();*/
                }
                else {
                    MaterialsDB materialsDB = new MaterialsDB();
                    materialsDB.setId(table_materials.getSelectionModel().getSelectedItem().getId());
                    materialsDB.setName(name_mat.getText().trim());
                    materialsDB.setNumber(number_mat.getText().trim());
                    materialsDB.setType(type_mat.getText().trim());
                    materialsDB.setStock_quantity(table_materials.getSelectionModel().getSelectedItem().getStock_quantity());
                    materialsDB.setOrder_quantity(table_materials.getSelectionModel().getSelectedItem().getOrder_quantity());
                    materialsDB.setUnit(unit.getText().trim());
                    if (!check_material_for_update(materialsDB)){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка");
                        alert.setHeaderText(null);
                        alert.setContentText("Данный материал уже есть в системе.");
                        alert.showAndWait();
                    }
                    else {
                        org.apache.http.client.HttpClient client_history = HttpClients.createDefault();
                        HttpPost request_history = new HttpPost(BASE_HISTORY+"/value");
                        ObjectMapper objectMapper_history = new ObjectMapper();
                        String jsonRequestBody_history = objectMapper_history.writeValueAsString(materialsDB);
                        StringEntity entity_history = new StringEntity(jsonRequestBody_history, StandardCharsets.UTF_8);
                        entity_history.setContentType("application/json; charset=UTF-8");
                        request_history.setEntity(entity_history);
                        org.apache.http.HttpResponse response_history = client_history.execute(request_history);
                        String responseBody_history = EntityUtils.toString(response_history.getEntity());
                        List<HistoryDB> history_changes = Arrays.asList(objectMapper_history.readValue(responseBody_history, HistoryDB[].class));
                        org.apache.http.client.HttpClient client = HttpClients.createDefault();
                        for (int i=0;i<history_changes.size();i++){
                            HistoryDB historyDB = new HistoryDB();
                            historyDB.setUser(main_login);
                            historyDB.setMaterial(table_materials.getSelectionModel().getSelectedItem().getId());
                            historyDB.setOld_value(history_changes.get(i).getOld_value());
                            historyDB.setNew_value(history_changes.get(i).getNew_value());

                            HttpPost request = new HttpPost(BASE_HISTORY+"/add");
                            ObjectMapper objectMapper = new ObjectMapper();
                            String jsonRequestBody = objectMapper.writeValueAsString(historyDB);
                            StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                            entity.setContentType("application/json; charset=UTF-8");
                            request.setEntity(entity);
                            client.execute(request);
                        }
                        HttpPost request = new HttpPost(BASE_MATERIAL+"/update");
                        ObjectMapper objectMapper = new ObjectMapper();
                        String jsonRequestBody = objectMapper.writeValueAsString(materialsDB);
                        StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                        entity.setContentType("application/json; charset=UTF-8");
                        request.setEntity(entity);
                        client.execute(request);
                        check_search();
                    }
                }
            }
        }
    }
    @FXML
    void ViewHistoryButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
      if (!table_materials.getSelectionModel().isEmpty()){
          FXMLLoader fxmlLoader = new FXMLLoader();
          fxmlLoader.setLocation(RestClient.class.getResource("history.fxml"));
          fxmlLoader.load();
          Parent root = fxmlLoader.getRoot();
          Stage stage = new Stage();
          stage.setTitle("История изменения материалов");
          stage.setScene(new Scene(root));
          HistoryController controller = fxmlLoader.getController();
          controller.initialize(table_materials.getSelectionModel().getSelectedItem().getId(), table_materials.getSelectionModel().getSelectedItem().getNumber());
          stage.initModality(Modality.APPLICATION_MODAL);
          stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }
    }
    @FXML
    void AddDocumentButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (num_doc.getText().isEmpty() || lot_doc.getText().isEmpty() || mat_doc.getText().isEmpty()||quant_doc.getText().isEmpty() ||
                price_doc.getText().isEmpty()||date_doc.getValue()==null||sup_doc.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля.");
            alert.showAndWait();
        }
        else {
            if (!quant_doc.getText().matches("[-+]?\\d+")||!price_doc.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Некорректное значение числовых полей.");
                alert.showAndWait();
            }
            else if(!check_if_exists_material(mat_doc.getText().trim())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Данный материал отсутствует в системе.");
                alert.showAndWait();
            }
            else {
                SupplyDocumentsDB supplyDocumentsDB = new SupplyDocumentsDB();
                supplyDocumentsDB.setNumber(num_doc.getText().trim());
                supplyDocumentsDB.setLot(lot_doc.getText().trim());
                supplyDocumentsDB.setMaterial(mat_doc.getText().trim());
                supplyDocumentsDB.setQuantity(Integer.valueOf(quant_doc.getText().trim()));
                supplyDocumentsDB.setCurrent_stock(Integer.valueOf(quant_doc.getText().trim()));
                supplyDocumentsDB.setPrice(Float.valueOf(price_doc.getText().trim()));
                supplyDocumentsDB.setDate(String.valueOf(date_doc.getValue()));
                supplyDocumentsDB.setSupplier(sup_doc.getValue());

                org.apache.http.client.HttpClient client = HttpClients.createDefault();
                HttpPost request = new HttpPost(BASE_SUPPLY_DOCUMENTS+"/add");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonRequestBody = objectMapper.writeValueAsString(supplyDocumentsDB);
                StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                entity.setContentType("application/json; charset=UTF-8");
                request.setEntity(entity);
                org.apache.http.HttpResponse response = client.execute(request);

                if(response.getStatusLine().getStatusCode()==200) {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("ОК");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Поставка добавлена.");
                    alert3.showAndWait();
                    check_search();
                    check_search_supply_documents();
                }
            }
        }
    }
    @FXML
    void DeleteDocumentButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (!table_supplies.getSelectionModel().isEmpty()){
            String number =table_supplies.getSelectionModel().getSelectedItem().getNumber();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Уточнение");
            alert.setHeaderText(null);
            alert.setContentText("Удалить документ поставки  " +number +"?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BASE_SUPPLY_DOCUMENTS+"/delete/"+table_supplies.getSelectionModel().getSelectedItem().getId()))
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if(response.statusCode()==200){
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("ОК");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Поставка "+ number+" удалена.");
                    alert3.showAndWait();
                }
                check_search_supply_documents();
                check_search();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }
    }
    @FXML
    void EditDocumentButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (table_supplies.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Выберите запись поставки из таблицы.");
            alert.showAndWait();
        }
        else {
            if (num_doc.getText().isEmpty() || lot_doc.getText().isEmpty() || mat_doc.getText().isEmpty()||quant_doc.getText().isEmpty() ||
                    price_doc.getText().isEmpty()||date_doc.getValue()==null||sup_doc.getValue()==null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля.");
                alert.showAndWait();
            } else {
                if (!quant_doc.getText().matches("[-+]?\\d+")||!price_doc.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Некорректное значение числовых полей.");
                    alert.showAndWait();
                }
                else if (!Objects.equals(mat_doc.getText(), table_supplies.getSelectionModel().getSelectedItem().getMaterial())||
                        Integer.parseInt(quant_doc.getText())!=table_supplies.getSelectionModel().getSelectedItem().getQuantity()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Материал и его количество в поставке не могут быть изменены.");
                    alert.showAndWait();
                }
                else if(!check_if_exists_material(mat_doc.getText().trim())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Данный материал отсутствует в системе.");
                    alert.showAndWait();
                }
                else {
                    SupplyDocumentsDB supplyDocumentsDB = new SupplyDocumentsDB();
                    supplyDocumentsDB.setId(table_supplies.getSelectionModel().getSelectedItem().getId());
                    supplyDocumentsDB.setNumber(num_doc.getText().trim());
                    supplyDocumentsDB.setLot(lot_doc.getText().trim());
                    supplyDocumentsDB.setMaterial(table_supplies.getSelectionModel().getSelectedItem().getMaterial());
                    supplyDocumentsDB.setQuantity(table_supplies.getSelectionModel().getSelectedItem().getQuantity());
                    supplyDocumentsDB.setPrice(Float.valueOf(price_doc.getText().trim()));
                    supplyDocumentsDB.setDate(String.valueOf(date_doc.getValue()));
                    supplyDocumentsDB.setSupplier(sup_doc.getValue());
                    supplyDocumentsDB.setMonth_leftovers(table_supplies.getSelectionModel().getSelectedItem().getMonth_leftovers());
                    supplyDocumentsDB.setCurrent_stock(table_supplies.getSelectionModel().getSelectedItem().getCurrent_stock());

                    org.apache.http.client.HttpClient client = HttpClients.createDefault();
                    HttpPost request = new HttpPost(BASE_SUPPLY_DOCUMENTS+"/update");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonRequestBody = objectMapper.writeValueAsString(supplyDocumentsDB);
                    StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                    entity.setContentType("application/json; charset=UTF-8");
                    request.setEntity(entity);
                    client.execute(request);

                    check_search_supply_documents();
                }
            }
        }
    }
    @FXML
    void search_suppliers(String string) throws IOException, InterruptedException {
        table_suppliers.getItems().clear();
        HttpClient client = HttpClient.newHttpClient();
        String encodedParam = URLEncoder.encode(string, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_SUPPLIER + "/search/" + encodedParam))
                .header("Content-Type", "application/json; charset=UTF-8")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<SuppliersDB> suppliersSearch = Arrays.asList(objectMapper.readValue(response.body(), SuppliersDB[].class));
            for (SuppliersDB suppliersDB : suppliersSearch) {
                SuppliersDBProperty e = new SuppliersDBProperty(suppliersDB);
                tableSearchSuppliersDBProperties.add(e);
            }
            table_suppliers.setItems(tableSearchSuppliersDBProperties);
        }
        if (search_field.getText().trim().equals(""))
        {
            update_suppliers();
        }
    }
    @FXML
    void search_materials(String string) throws IOException, InterruptedException {
        table_materials.getItems().clear();
        SearchByType searchByType = new SearchByType();
        searchByType.setString(string);
        if(type_mat_comb.getValue()==null) {
            searchByType.setHas_type(false);
            searchByType.setType(null);
        }
        else{
            searchByType.setHas_type(true);
            searchByType.setType(type_mat_comb.getValue());
        }
        org.apache.http.client.HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BASE_MATERIAL+"/search");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(searchByType);
        StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
        entity.setContentType("application/json; charset=UTF-8");
        request.setEntity(entity);
        org.apache.http.HttpResponse response = client.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        List<MaterialsDB> materialsSearch = Arrays.asList(objectMapper.readValue(responseBody, MaterialsDB[].class));
            for (MaterialsDB materialsDB : materialsSearch) {
                MaterialsDBProperty e = new MaterialsDBProperty(materialsDB);
                tableSearchMaterialsDBProperties.add(e);
            }
            table_materials.setItems(tableSearchMaterialsDBProperties);
        table_materials.refresh();
        table_materials.setRowFactory(tv -> new TableRow<MaterialsDBProperty>() {
            protected void updateItem(MaterialsDBProperty item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null )
                    setStyle("");
                else if (item.getStock_quantity()<15) {
                    setStyle("-fx-background-color: #FFDCDC;");
                    pseudoClassStateChanged(little,true);
                }
            }
        });
        /*if (search_field_mat.getText().trim().equals(""))
        {
            update_materials();
        }*/
    }
    public void ExitButtonOnAction (ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(RestClient.class.getResource("login.fxml"));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Вход в систему");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        exit_button.getScene().getWindow().hide();
    }
    @FXML
    void search_supplyDocuments(String string) throws IOException, InterruptedException {
        table_supplies.getItems().clear();
        HttpClient client = HttpClient.newHttpClient();
        String encodedParam = URLEncoder.encode(string, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_SUPPLY_DOCUMENTS + "/search/" + encodedParam))
                .header("Content-Type", "application/json; charset=UTF-8")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<SupplyDocumentsDB> supplyDocumentsDBS = Arrays.asList(objectMapper.readValue(response.body(), SupplyDocumentsDB[].class));
            for (SupplyDocumentsDB supplyDocumentsDB : supplyDocumentsDBS) {
                SupplyDocumentsDBProperty e = new SupplyDocumentsDBProperty(supplyDocumentsDB);
                tableSupplyDocumentsDBProperties.add(e);
            }
            table_supplies.setItems(tableSupplyDocumentsDBProperties);
        }
        if (search_field_doc.getText().trim().equals(""))
        {
            update_supplies();
        }

    }
    @FXML
    void search_productionOrders(String string) throws IOException {
        table_po.getItems().clear();
        table_po.setStyle("-fx-background-color: transparent;");
        SearchProductionOrders searchProductionOrders = new SearchProductionOrders();
        searchProductionOrders.setSearch(search_field_po.getText().trim());
        searchProductionOrders.setIs_open(open_check.isSelected());
        if (date_filter_po.getValue()==null){
            searchProductionOrders.setDate(null);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            searchProductionOrders.setDate(String.valueOf(LocalDate.parse(String.valueOf(date_filter_po.getValue()), formatter)));
        }
        org.apache.http.client.HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BASE_PRODUCTION_ORDER+"/search");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(searchProductionOrders);
        StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
        entity.setContentType("application/json; charset=UTF-8");
        request.setEntity(entity);
        org.apache.http.HttpResponse response = client.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        List<ProductionOrdersDB> productionOrdersDBS = Arrays.asList(objectMapper.readValue(responseBody, ProductionOrdersDB[].class));
        for (ProductionOrdersDB productionOrdersDB : productionOrdersDBS) {
            ProductionOrdersDBProperty e = new ProductionOrdersDBProperty(productionOrdersDB);
            tableProductionOrdersDBProperties.add(e);
        }
        table_po.setItems(tableProductionOrdersDBProperties);

        table_po.refresh();
        table_po.setRowFactory(tv -> new TableRow<ProductionOrdersDBProperty>() {
            protected void updateItem(ProductionOrdersDBProperty item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null)
                    setStyle("");
                else if (item.getReject_quantity()!=0) {
                    setStyle("-fx-background-color: #FFDCDC;");
                    pseudoClassStateChanged(reject,true);
                } else{
                    if (item.isStatus()){
                        setStyle("-fx-background-color: #DCFFDC;");
                        pseudoClassStateChanged(closed,true);}
                    else{
                        setStyle("-fx-background-color: #DCDCFF;");
                        pseudoClassStateChanged(open,true);}
                }
            }
        });
    }
    @FXML
    void IsOpenPressedOnAction(ActionEvent event) throws IOException, InterruptedException {
        if(!open_check.isSelected()&&Objects.equals(search_field_po.getText().trim(),"")&&date_filter_po.getValue()==null)
        {
            update_production_orders();
        } else {
            search_productionOrders(search_field_po.getText().trim());
        }
    }
    @FXML
    void DateChoiceOnAction(ActionEvent event) throws IOException, InterruptedException {
        if(!open_check.isSelected()&&Objects.equals(search_field_po.getText().trim(),"")&&date_filter_po.getValue()==null)
        {
            update_production_orders();
        } else search_productionOrders(search_field_po.getText().trim());
    }
    @FXML
    void GeneratePriceButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (mat_doc.getText().isEmpty()||quant_doc.getText().isEmpty() ||sup_doc.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля.");
            alert.showAndWait();
        }
        else {
            if (!quant_doc.getText().matches("[-+]?\\d+")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Некорректное значение числовых полей.");
                alert.showAndWait();
            }
            else if(!check_if_exists_material(mat_doc.getText().trim())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Данный материал отсутствует в системе.");
                alert.showAndWait();
            }
            else {
                PricesDB pricesDB = new PricesDB();
                pricesDB.setSup_name(sup_doc.getValue());
                pricesDB.setMat_name(mat_doc.getText().trim());

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
                    SupplyDocumentsDB supplyDocumentsDB = new SupplyDocumentsDB();
                    supplyDocumentsDB.setSupplier(sup_doc.getValue());
                    supplyDocumentsDB.setMaterial(mat_doc.getText().trim());
                    supplyDocumentsDB.setQuantity(Integer.valueOf(quant_doc.getText().trim()));

                    HttpPost request_count = new HttpPost(BASE_PRICE+"/count");
                    String jsonRequestBody_count = objectMapper.writeValueAsString(supplyDocumentsDB);
                    StringEntity entity_count = new StringEntity(jsonRequestBody_count, StandardCharsets.UTF_8);
                    entity_count.setContentType("application/json; charset=UTF-8");
                    request_count.setEntity(entity_count);
                    org.apache.http.HttpResponse response_count = client.execute(request_count);
                    String responseBody_count = EntityUtils.toString(response_count.getEntity());
                    price_doc.setText(String.valueOf(objectMapper.readValue(responseBody_count, Float.class)));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Цена для данной комбинации материал-поставщик не задана.");
                    alert.showAndWait();
                }
            }
        }
    }
    @FXML
    void AddProductionOrderButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (material_po.getText().isEmpty() || need_quant_po.getText().isEmpty()||date_po.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля.");
            alert.showAndWait();
        }
        else {
            if (!need_quant_po.getText().matches("[-+]?\\d+")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Некорректное значение числового полей.");
                alert.showAndWait();
            }
            else if(!check_if_exists_material(material_po.getText().trim())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Данный материал отсутствует в системе.");
                alert.showAndWait();
            }
            else {
                ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
                productionOrdersDB.setNumber_material(material_po.getText().trim());
                productionOrdersDB.setDate(String.valueOf(date_po.getValue()));
                productionOrdersDB.setNeed_quantity(Integer.valueOf(need_quant_po.getText()));

                org.apache.http.client.HttpClient client = HttpClients.createDefault();
                HttpPost request = new HttpPost(BASE_PRODUCTION_ORDER+"/add");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonRequestBody = objectMapper.writeValueAsString(productionOrdersDB);
                StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                entity.setContentType("application/json; charset=UTF-8");
                request.setEntity(entity);
                org.apache.http.HttpResponse response = client.execute(request);

                if(response.getStatusLine().getStatusCode()==200) {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("ОК");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Заказ отдела производства добавлен.");
                    alert3.showAndWait();
                    check_search();
                    search_productionOrders(null);
                }
            }
        }
    }
    @FXML
    void EditProductionOrderButtonOnAction(ActionEvent event) throws IOException {
        if (table_po.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Выберите заказ отдела производства из таблицы.");
            alert.showAndWait();
        }
        else {
            if (material_po.getText().isEmpty()||need_quant_po.getText().isEmpty()||date_po.getValue()==null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля.");
                alert.showAndWait();
            } else {
                if (!Objects.equals(table_po.getSelectionModel().getSelectedItem().getNumber_material(),material_po.getText().trim())||
                !Objects.equals(table_po.getSelectionModel().getSelectedItem().getNeed_quantity(),Integer.valueOf(need_quant_po.getText().trim()))){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Материал и количество изменять нельзя!");
                    alert.showAndWait();
                } else if (table_po.getSelectionModel().getSelectedItem().isStatus()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Заказ закрыт. Изменение невозможно.");
                    alert.showAndWait();
                } else {
                    ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
                    productionOrdersDB.setId(table_po.getSelectionModel().getSelectedItem().getId());
                    productionOrdersDB.setDate(String.valueOf(date_po.getValue()));

                    org.apache.http.client.HttpClient client = HttpClients.createDefault();
                    HttpPost request = new HttpPost(BASE_PRODUCTION_ORDER+"/update");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonRequestBody = objectMapper.writeValueAsString(productionOrdersDB);
                    StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                    entity.setContentType("application/json; charset=UTF-8");
                    request.setEntity(entity);
                    client.execute(request);

                    search_productionOrders(null);
                }
            }
        }
    }
    @FXML
    void AddRejectedMaterialButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
          if (!table_po.getSelectionModel().isEmpty()){
                if(!table_po.getSelectionModel().getSelectedItem().isStatus()){
                    Alert alert4 = new Alert(Alert.AlertType.ERROR);
                    alert4.setTitle("Ошибка");
                    alert4.setHeaderText(null);
                    alert4.setContentText("Заказ находится в статусе 'открыто'. Внесение брака невозможно.");
                    alert4.showAndWait();
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(RestClient.class.getResource("reject.fxml"));
                    fxmlLoader.load();
                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setTitle("Выберите количество бракованного материала из партий " );
                    stage.setScene(new Scene(root));
                    RejectController controller = fxmlLoader.getController();
                    controller.initialize(table_po.getSelectionModel().getSelectedItem().getMaterial(),
                            table_po.getSelectionModel().getSelectedItem().getNumber_material(),
                            table_po.getSelectionModel().getSelectedItem().getId());
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    search_productionOrders(null);
                    check_search_supply_documents();
                    }
          } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }
    }
    @FXML
    void DeleteProductionOrderButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (!table_po.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Уточнение");
            alert.setHeaderText(null);
            alert.setContentText("Удалить заказ?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BASE_PRODUCTION_ORDER+"/delete/"+table_po.getSelectionModel().getSelectedItem().getId()))
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if(response.statusCode()==200){
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("ОК");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Заказ удалён.");
                    alert3.showAndWait();
                }
                search_productionOrders(null);
                check_search();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }
    }
    @FXML
    void WriteOffButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (!table_po.getSelectionModel().isEmpty()) {
            if (table_po.getSelectionModel().getSelectedItem().isStatus()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Заказ закрыт. Списание невозможно.");
                alert.showAndWait();
            } else {
                ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
                productionOrdersDB.setId(table_po.getSelectionModel().getSelectedItem().getId());
                productionOrdersDB.setNeed_quantity(table_po.getSelectionModel().getSelectedItem().getNeed_quantity());
                productionOrdersDB.setNumber_material(table_po.getSelectionModel().getSelectedItem().getNumber_material());
                productionOrdersDB.setMaterial(table_po.getSelectionModel().getSelectedItem().getMaterial());
                productionOrdersDB.setDate(table_po.getSelectionModel().getSelectedItem().getDate());
                if (fifo_rb.isSelected()) {
                    org.apache.http.client.HttpClient client = HttpClients.createDefault();
                    HttpPost request = new HttpPost(BASE_WRITE_OFF + "/fifo");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonRequestBody = objectMapper.writeValueAsString(productionOrdersDB);
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
                            search_productionOrders(null);
                            check_search();
                            check_search_supply_documents();
                        }
                    }
                } else if (mid_rb.isSelected()) {
                    org.apache.http.client.HttpClient client = HttpClients.createDefault();
                    HttpPost request = new HttpPost(BASE_WRITE_OFF + "/mid");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonRequestBody = objectMapper.writeValueAsString(productionOrdersDB);
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
                            search_productionOrders(null);
                            check_search();
                            check_search_supply_documents();
                        }
                    }
                } else if (each_rb.isSelected()) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(RestClient.class.getResource("each.fxml"));
                    fxmlLoader.load();
                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setTitle("Выберите количество материала из партий " );
                    stage.setScene(new Scene(root));
                    EachController controller = fxmlLoader.getController();
                    controller.initialize(table_po.getSelectionModel().getSelectedItem().getMaterial(),
                            table_po.getSelectionModel().getSelectedItem().getNeed_quantity(),
                            table_po.getSelectionModel().getSelectedItem().getNumber_material(),
                            table_po.getSelectionModel().getSelectedItem().getId());
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    search_productionOrders(null);
                    check_search();
                    check_search_supply_documents();
                  /*  org.apache.http.client.HttpClient client = HttpClients.createDefault();
                    HttpPost request = new HttpPost(BASE_WRITE_OFF + "/each");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonRequestBody = objectMapper.writeValueAsString(productionOrdersDB);
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
                            search_productionOrders(null);
                            check_search();
                            check_search_supply_documents();
                        }
                    }

                   */
                } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Выберите вариант списания");
                alert.showAndWait();
            }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }

       /* if (!table_po.getSelectionModel().isEmpty()){
            if (table_po.getSelectionModel().getSelectedItem().isStatus()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Заказ закрыт. Списание невозможно.");
                alert.showAndWait();
            } else {
                ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
                productionOrdersDB.setId(table_po.getSelectionModel().getSelectedItem().getId());
                productionOrdersDB.setNeed_quantity(table_po.getSelectionModel().getSelectedItem().getNeed_quantity());
                productionOrdersDB.setNumber_material(table_po.getSelectionModel().getSelectedItem().getNumber_material());
                productionOrdersDB.setMaterial(table_po.getSelectionModel().getSelectedItem().getMaterial());

                org.apache.http.client.HttpClient client = HttpClients.createDefault();
                HttpPost request = new HttpPost(BASE_PRODUCTION_ORDER+"/write_off");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonRequestBody = objectMapper.writeValueAsString(productionOrdersDB);
                StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                entity.setContentType("application/json; charset=UTF-8");
                request.setEntity(entity);
                org.apache.http.HttpResponse response = client.execute(request);
                String responseBody = EntityUtils.toString(response.getEntity());
                Integer enough = objectMapper.readValue(responseBody, Integer.class);

                if(response.getStatusLine().getStatusCode()==200) {
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
                            alert4.setContentText("На складе осталось меньше 15 единиц материала.");
                            alert4.showAndWait();
                        }
                        search_productionOrders(null);
                        check_search();
                    }
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }*/
    }
    @FXML
    void BackWriteButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (!table_po.getSelectionModel().isEmpty()){
            if (!table_po.getSelectionModel().getSelectedItem().isStatus()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Заказ находится в статусе 'открыто'.");
                alert.showAndWait();
            } else {
                ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
                productionOrdersDB.setId(table_po.getSelectionModel().getSelectedItem().getId());
                productionOrdersDB.setNeed_quantity(table_po.getSelectionModel().getSelectedItem().getNeed_quantity());
                productionOrdersDB.setNumber_material(table_po.getSelectionModel().getSelectedItem().getNumber_material());
                productionOrdersDB.setMaterial(table_po.getSelectionModel().getSelectedItem().getMaterial());

                org.apache.http.client.HttpClient client = HttpClients.createDefault();
                HttpPost request = new HttpPost(BASE_WRITE_OFF+"/back");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonRequestBody = objectMapper.writeValueAsString(productionOrdersDB);
                StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                entity.setContentType("application/json; charset=UTF-8");
                request.setEntity(entity);
                org.apache.http.HttpResponse response = client.execute(request);
                if(response.getStatusLine().getStatusCode()==200) {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("ОК");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Списание отменено.");
                    alert3.showAndWait();
                    search_productionOrders(null);
                    check_search();
                    check_search_supply_documents();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }
    }
    @FXML
    void AlignButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Уточнение");
        alert.setHeaderText(null);
        alert.setContentText("Выровнять остатки на начало месяца?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
        org.apache.http.client.HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(BASE_SUPPLY_DOCUMENTS+"/align");
        StringEntity entity = new StringEntity("", StandardCharsets.UTF_8);
        entity.setContentType("application/json; charset=UTF-8");
        request.setEntity(entity);
        org.apache.http.HttpResponse response = client.execute(request);

        if(response.getStatusLine().getStatusCode()==200) {
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setTitle("ОК");
            alert3.setHeaderText(null);
            alert3.setContentText("Остатки выровнены.");
            alert3.showAndWait();
            check_search();
            check_search_supply_documents();
        }
        else {
            Alert alert3 = new Alert(Alert.AlertType.ERROR);
            alert3.setTitle("Ошибка");
            alert3.setHeaderText(null);
            alert3.setContentText("Ошибка сервера.");
            alert3.showAndWait();
            check_search();
            check_search_supply_documents();
        }
        check_search_supply_documents();
        }
    }
    @FXML
    void ChangePasswordOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(RestClient.class.getResource("first_entrance.fxml"));
        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Новый пароль");
        stage.setScene(new Scene(root));
        FirstEntranceController controller = fxmlLoader.getController();
        controller.initialize(true, main_login);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    @FXML
    void ConsumptionButtonOnAction(ActionEvent event) {
        avg_check.setVisible(false);
        mat_report.setVisible(false);
        date_from.setValue(null);
        date_to.setValue(null);
        date_from.setVisible(true);
        date_to.setVisible(true);
        show_report.setVisible(true);
        report_text.setVisible(true);
        report_text.clear();
        report_type=1;
    }
    @FXML
    void QualityButtonOnAction(ActionEvent event) {
        avg_check.setVisible(false);
        mat_report.setVisible(false);
        date_from.setValue(null);
        date_to.setValue(null);
        date_from.setVisible(true);
        date_to.setVisible(true);
        show_report.setVisible(true);
        report_text.setVisible(true);
        report_text.clear();
        report_type=2;
    }
    @FXML
    void SuppliersChoiceButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        date_from.setVisible(false);
        date_to.setVisible(false);
        avg_check.setVisible(true);
        mat_report.setVisible(true);
        show_report.setVisible(true);
        report_text.setVisible(true);
        report_text.clear();
        mat_report.getItems().clear();
        mat_report.setPromptText("Материал");

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
            mat_report.setItems(mat_cb.sorted());
            report_type=3;
        }
    }
    @FXML
    void ShowReportButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if(report_type==1){
        if(date_to.getValue()==null||date_from.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Заполните все поля.");
            alert.showAndWait();
        } else{
            if(date_to.getValue().compareTo(date_from.getValue())<0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Неверный интервал дат.");
                alert.showAndWait();
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

                SearchProductionOrders searchProductionOrders = new SearchProductionOrders();
                searchProductionOrders.setDate_from(String.valueOf(date_from.getValue()));
                searchProductionOrders.setDate_to(String.valueOf(date_to.getValue()));

                org.apache.http.client.HttpClient client = HttpClients.createDefault();
                HttpPost request = new HttpPost(BASE_REPORT+"/consumption");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonRequestBody = objectMapper.writeValueAsString(searchProductionOrders);
                StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                entity.setContentType("application/json; charset=UTF-8");
                request.setEntity(entity);
                org.apache.http.HttpResponse response = client.execute(request);
                String responseBody = EntityUtils.toString(response.getEntity());
                ArrayList<Report> report = objectMapper.readValue(responseBody, new TypeReference<ArrayList<Report>>() {});
               // ArrayList<Report> report = Arrays.asList(objectMapper.readValue(responseBody, ArrayList.class));
                report_gist.clear();
                report_gist=report;
                if (report.size()==0){
                    report_text.setText("Данные за указанный период отсутствуют.");
                } else {
                    LocalDate df = date_from.getValue();
                    String formattedDateFrom = df.format(formatter);
                    LocalDate dt = date_to.getValue();
                    String formattedDateTo = dt.format(formatter);
                    String report_string ="Расход материалов за период с "+formattedDateFrom+
                            " по "+formattedDateTo+" :\n";
                    for (int i=0; i<report.size();i++){
                        report_string += report.get(i).getMaterial() +"\t\t"+report.get(i).getQuantity()+"\n";
                    }
                    report_text.setText(report_string);
                    show_diagram.setVisible(true);
                    load_button.setVisible(true);
                    for_report=null;
                    for_report=report_string;
                }
            }
        }
        } else if (report_type==3) {
            if(mat_report.getValue()==null&&!avg_check.isSelected()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Выберите критерии для отчёта.");
                alert.showAndWait();
            } else{
                if(mat_report.getValue()!=null&&!avg_check.isSelected()){
                    ProductionOrdersDB productionOrdersDB = new ProductionOrdersDB();
                    productionOrdersDB.setNumber_material(mat_report.getValue());

                    org.apache.http.client.HttpClient client = HttpClients.createDefault();
                    HttpPost request = new HttpPost(BASE_REPORT+"/suppliers");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonRequestBody = objectMapper.writeValueAsString(productionOrdersDB);
                    StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                    entity.setContentType("application/json; charset=UTF-8");
                    request.setEntity(entity);
                    org.apache.http.HttpResponse response = client.execute(request);
                    String responseBody = EntityUtils.toString(response.getEntity());
                    ArrayList<Report> report = objectMapper.readValue(responseBody, new TypeReference<ArrayList<Report>>() {});
                    report_gist.clear();
                    report_gist=report;
                    if (report.size()==0){
                        report_text.setText("Данные по указанному материалу отсутствуют.");
                    } else {
                        String report_string ="Закупочная стоимость материала "+mat_report.getValue()+
                                " у различных поставщиков:\n";
                        for (int i=0; i<report.size();i++){
                            report_string += report.get(i).getSupplier() +"\t\t"+report.get(i).getPrice()+"\n";
                        }
                        report_text.setText(report_string);
                        show_diagram.setVisible(true);
                        load_button.setVisible(true);
                        for_report=null;
                        for_report=report_string;
                    }
                } else if (avg_check.isSelected()) {
                    HttpClient httpClient = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(BASE_REPORT+"/avg_suppliers"))
                            .header("Content-Type", "application/json; charset=UTF-8")
                            .GET()
                            .build();
                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                        ObjectMapper objectMapper = new ObjectMapper();
                    ArrayList<Report> report = objectMapper.readValue(response.body(),  new TypeReference<ArrayList<Report>>() {});
                       report_gist.clear();
                    report_gist=report;
                    if (report.size()==0){
                        report_text.setText("Данные отсутствуют.");
                    } else {
                        String report_string ="Средняя закупочная стоимость материалов у различных поставщиков:\n";
                        for (int i=0; i<report.size();i++){
                            report_string += report.get(i).getSupplier() +"\t\t"+report.get(i).getPrice()+"\n";
                        }
                        report_text.setText(report_string);
                        show_diagram.setVisible(true);
                        load_button.setVisible(true);
                        for_report=null;
                        for_report=report_string;
                }}
            }
        }else if (report_type==2) {
            if(date_to.getValue()==null||date_from.getValue()==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля.");
                alert.showAndWait();
            } else{
                if(date_to.getValue().compareTo(date_from.getValue())<0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Неверный интервал дат.");
                    alert.showAndWait();
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    SearchProductionOrders searchProductionOrders = new SearchProductionOrders();
                    searchProductionOrders.setDate_from(String.valueOf(date_from.getValue()));
                    searchProductionOrders.setDate_to(String.valueOf(date_to.getValue()));

                    org.apache.http.client.HttpClient client = HttpClients.createDefault();
                    HttpPost request = new HttpPost(BASE_REPORT+"/quality");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonRequestBody = objectMapper.writeValueAsString(searchProductionOrders);
                    StringEntity entity = new StringEntity(jsonRequestBody, StandardCharsets.UTF_8);
                    entity.setContentType("application/json; charset=UTF-8");
                    request.setEntity(entity);
                    org.apache.http.HttpResponse response = client.execute(request);
                    String responseBody = EntityUtils.toString(response.getEntity());
                    ArrayList<Report> report = objectMapper.readValue(responseBody, new TypeReference<ArrayList<Report>>() {});
                    report_gist.clear();
                    report_gist=report;
                    if (report.size()==0){
                        report_text.setText("Данные за указанный период отсутствуют.");
                    } else {
                        LocalDate df = date_from.getValue();
                        String formattedDateFrom = df.format(formatter);
                        LocalDate dt = date_to.getValue();
                        String formattedDateTo = dt.format(formatter);
                        String report_string ="Анализ качества материалов за период с "+formattedDateFrom+
                                " по "+formattedDateTo+" :\n Материал      Общее количество      Брак      Оценка";


                        for (int i=0; i<report.size();i++){
                            report_string += "\n\n"+report.get(i).getMaterial() +"\t\t\t\t\t"+report.get(i).getQuantity()+""
                                    +"\t\t\t\t"+report.get(i).getReject()+""+"\t\t"+report.get(i).getQuality()+"\n";

                        }
                        report_text.setText(report_string);
                        show_diagram.setVisible(true);
                        load_button.setVisible(true);
                        for_report=null;
                        for_report=report_string;
                    }
                }
            }
        }
    }
    @FXML
    void ShowDiagramButtonOnAction(ActionEvent event) {
        if(report_type==1){
        Stage stage = new Stage();
        CategoryAxis x = new CategoryAxis();
        x.setLabel("Материалы");
        NumberAxis y = new NumberAxis();
        y.setLabel("Количество");
        BarChart bc = new BarChart(x, y);
        XYChart.Series material = new XYChart.Series();
        for (int i = 0; i < report_gist.size(); i++) {
            material.getData().add(new XYChart.Data(report_gist.get(i).getMaterial(), report_gist.get(i).getQuantity()));
        }
        bc.getData().add(material);
        VBox vbox = new VBox(bc);
        Scene sc = new Scene(vbox, 400, 400);
        stage.setScene(sc);
        stage.setHeight(500);
        stage.setWidth(900);
        stage.show();
        } else if (report_type==3) {
            Stage stage = new Stage();
            CategoryAxis x = new CategoryAxis();
            x.setLabel("Поставщики");
            NumberAxis y = new NumberAxis();
            y.setLabel("Закупочная стоимость");
            BarChart bc = new BarChart(x, y);
            XYChart.Series material = new XYChart.Series();
            for (int i = 0; i < report_gist.size(); i++) {
                material.getData().add(new XYChart.Data(report_gist.get(i).getSupplier(), report_gist.get(i).getPrice()));
            }
            bc.getData().add(material);
            VBox vbox = new VBox(bc);
            Scene sc = new Scene(vbox, 400, 400);
            stage.setScene(sc);
            stage.setHeight(500);
            stage.setWidth(900);
            stage.show();
        }else if (report_type==2) {
            Stage stage = new Stage();
            CategoryAxis x = new CategoryAxis();
            x.setLabel("Материалы");
            NumberAxis y = new NumberAxis();
            y.setLabel("Количество");
            BarChart<String, Number> bc = new BarChart(x, y);
            XYChart.Series<String, Number> material = new XYChart.Series<>();
            material.setName("Общее количество");
            XYChart.Series<String, Number> material_rej = new XYChart.Series<>();
            material_rej.setName("Брак");
            for (int i = 0; i < report_gist.size(); i++) {
                material.getData().add(new XYChart.Data<>(report_gist.get(i).getMaterial(), report_gist.get(i).getQuantity()));
                material_rej.getData().add(new XYChart.Data<>(report_gist.get(i).getMaterial(), report_gist.get(i).getReject()));
            }
            bc.getData().addAll(material, material_rej);
            VBox vbox = new VBox(bc);
            Scene sc = new Scene(vbox, 400, 400);
            stage.setScene(sc);
            stage.setHeight(500);
            stage.setWidth(900);
            stage.show();
        }
    }
    @FXML
    void LoadButtonOnAction(ActionEvent event) throws IOException {
        BufferedWriter outputWriter = null;
        if(report_type==1){
            outputWriter = new BufferedWriter(new FileWriter("consumption_report.txt"));
        } else if (report_type==3) {
            if (avg_check.isSelected())
                outputWriter = new BufferedWriter(new FileWriter("avg_suppliers_report.txt"));
          else  outputWriter = new BufferedWriter(new FileWriter("suppliers_report.txt"));
        }
        else if (report_type==2) {
                outputWriter = new BufferedWriter(new FileWriter("quality_report.txt"));
        }
        assert outputWriter != null;
        outputWriter.write(for_report);
        outputWriter.flush();
        outputWriter.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Файл сохранён.");
        alert.showAndWait();
    }
    @FXML
    void AvgCheckPressedOnAction(ActionEvent event) {
        mat_report.setDisable(!mat_report.isDisabled());
    }
    @FXML
    void LotsButtonOnAction(ActionEvent event) throws IOException, InterruptedException {
        if (!table_materials.getSelectionModel().isEmpty()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(RestClient.class.getResource("lots.fxml"));
            fxmlLoader.load();
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Партии материала " );
            stage.setScene(new Scene(root));
            LotsController controller = fxmlLoader.getController();
            controller.initialize(table_materials.getSelectionModel().getSelectedItem().getId(), table_materials.getSelectionModel().getSelectedItem().getNumber());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Выберите строку!");
            alert.showAndWait();
        }
    }
}
