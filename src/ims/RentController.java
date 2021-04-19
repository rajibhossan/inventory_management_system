package ims;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Db;
import model.Inventory;
import model.Rent;
import service.DBService;

public class RentController implements Initializable {

    @FXML
    private TableView<Inventory> tableviewH;
    @FXML
    private TableColumn<Inventory, String> idColumn;
    @FXML
    private TableColumn<Inventory, String> typOfHouseColumn;
    @FXML
    private TableColumn<Inventory, Integer> bedroomsColumn;
    @FXML
    private TableColumn<Inventory, Integer> bathroomsColumn;
    @FXML
    private TableColumn<Inventory, Boolean> gardenColumn;
    @FXML
    private TableColumn<Inventory, String> areaColumn;
    @FXML
    private TableColumn<Inventory, Double> priceColumn;
    @FXML
    private TableColumn<Inventory, Integer> stockColumn;
    @FXML
    private TableColumn<Inventory, String> statusColumn;
    @FXML
    private Button salebtn;
    @FXML
    private Button selectbtn1;
    @FXML
    private TextField pricetxt;
    @FXML
    private TextField deposit1;
    @FXML
    private TableView<Customer> tableviewC;
    @FXML
    private TableColumn<Customer, String> cname;
    @FXML
    private TableColumn<Customer, String> cphone;
    @FXML
    private TableColumn<Customer, String> caddress;

    private HashMap<String, Inventory> inList;
    private HashMap<String, Customer> cList;
    private HashMap<String, Rent> rlList;

    private Customer c = null;
    private Inventory inv = null;

    private Db dbco;
    private DBService dBService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typOfHouseColumn.setCellValueFactory(new PropertyValueFactory<>("TypeOfHouse"));
        bedroomsColumn.setCellValueFactory(new PropertyValueFactory<>("bedrooms"));
        bathroomsColumn.setCellValueFactory(new PropertyValueFactory<>("bathrooms"));
        gardenColumn.setCellValueFactory(new PropertyValueFactory<>("garden"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));

        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("quntity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        cphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        caddress.setCellValueFactory(new PropertyValueFactory<>("currentAddress"));

        deposit1.setEditable(false);

        dBService = new DBService();
        dbco = dBService.getDB();

        inList = dbco.getInList();
        cList = dbco.getcList();
        rlList = dbco.getRlList();

        List<Customer> getAllc = new ArrayList(cList.values());

        tableviewC.setItems(FXCollections.observableArrayList(getAllc));
        tableviewH.setItems(FXCollections.observableArrayList(getAll()));
    }

    public List<Inventory> getAll() {
        dBService = new DBService();
        dbco = dBService.getDB();
        inList = dbco.getInList();

        List<Inventory> getAll = new ArrayList(inList.values());
        List<Inventory> result = getAll.stream()
                .filter(a -> Objects.equals(a.getStatus(), "RENT"))
                .collect(Collectors.toList());
        return result;
    }

    private void showTable() {
        List<Inventory> getAll = new ArrayList(getAll());
        tableviewH.setItems(FXCollections.observableArrayList(getAll));
    }

    @FXML
    private void selectHouseAction(ActionEvent event) {

        inv = tableviewH.getSelectionModel().selectedItemProperty().get();
        deposit1.setEditable(true);

    }

    @FXML
    private void bookAction(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText(null);

        dBService = new DBService();
        dbco = dBService.getDB();

        inv = tableviewH.getSelectionModel().selectedItemProperty().get();
        c = tableviewC.getSelectionModel().selectedItemProperty().get();

        if (inv == null) {
            a.setContentText("Please Select House From House Table!");
            a.show();
        }
        if (c == null) {

            a.setContentText("Please Select Customer From Curomer Table!");
            a.show();
        }
        if (inv.getQuntity() >= 1) {

            Double depo1 = Double.valueOf(deposit1.getText());
            Integer id = 0;
            if (rlList == null) {
                id = 0;
            } else {
                id = (rlList.size() + 1);
            }
            Rent s = new Rent("" + id, c, inv.getHouse(), depo1, "BOOKED", "PARTIAL_PAID");

            rlList.put("" + id, s);

            Integer qty = inv.getQuntity();
            inv.setQuntity((qty - 1));
            inList.replace(inv.getId(), inv);

            dbco.setRlList(rlList);
            dbco.setInList(inList);
            
            dBService.saveDb(dbco);
            inv = null;

            a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText(null);
            a.setContentText("Congratulation Mr." + c.getName() + ", Book Successfull!!");
            a.show();

        } else {

            a.setContentText("Quntity Out Of Stock!!!");
            a.show();

        }
        showTable();
    }

}
