package ims;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Booking;
import model.Db;

import model.Inventory;
import model.Rent;
import model.ReportRecipt;
import service.ConfigurationEmail;
import service.DBService;
import service.Email;
import service.ReportController;

public class InventoryController implements Initializable {

    @FXML
    private TableView<Inventory> tableview;
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
    private TextField bedroomstxt;
    @FXML
    private TextField areatxt;
    @FXML
    private TextField priceSearchtxt;
    @FXML
    private TextField pricetxt;
    @FXML
    private Button updatebtn;
    @FXML
    private Button editbtn;
    @FXML
    private Button search;
    @FXML
    private TextField stocktxt;
    @FXML
    private TextField bathroomtxt;
    @FXML
    private ComboBox<String> typHouseCb;
    @FXML
    private ComboBox<String> garden;
    @FXML
    private ComboBox<String> statustCombotxt;

    private HashMap<String, Inventory> inList;
    private Inventory d = null;
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

        typHouseCb.setItems(FXCollections.observableArrayList(
                "Bungalow",
                "Flat",
                "Detached"));

        garden.setItems(FXCollections.observableArrayList(
                "TRUE",
                "FALSE"));

        statustCombotxt.setItems(FXCollections.observableArrayList(
                "SALE",
                "RENT"));

        dBService = new DBService();
        dbco = dBService.getDB();
        inList = dbco.getInList();

        List<Inventory> getAll = new ArrayList(inList.values());
        tableview.setItems(FXCollections.observableArrayList(getAll));

    }

    private void showTable() {
        dBService = new DBService();
        dbco = dBService.getDB();
        inList = dbco.getInList();

        List<Inventory> getAll = new ArrayList(inList.values());
        tableview.setItems(FXCollections.observableArrayList(getAll));
    }

    @FXML
    private void editAction(ActionEvent event) {
        d = tableview.getSelectionModel().selectedItemProperty().get();
        pricetxt.setText(d.getPrice() + "");
        stocktxt.setText(d.getQuntity() + "");

    }

    @FXML
    private void updateAction(ActionEvent event) {

        String pp = pricetxt.getText();
        String stt = stocktxt.getText();

        Inventory inv = inList.get(d.getId());

        if ("" != pp) {
            Double price = Double.valueOf(pp);
            inv.setPrice(price);

        }
        if ("" != stt) {
            Integer qntity = Integer.valueOf(stt);
            inv.setQuntity(qntity);
        }

        pricetxt.setText(null);
        stocktxt.setText(null);

        inList.replace(d.getId(), inv);
        dbco.setInList(inList);
        dBService.saveDb(dbco);

        showTable();
        receitGenerate(d);
        d = null;

    }

    @FXML
    private void searchAction(ActionEvent event) {

        String typHouseCbtx = typHouseCb.getSelectionModel().getSelectedItem();
        String gardent = garden.getSelectionModel().getSelectedItem();
        String statustC = statustCombotxt.getSelectionModel().getSelectedItem();

        String area = areatxt.getText();
        String price = priceSearchtxt.getText();
        String bedrooms = bedroomstxt.getText();
        String bathroom = bathroomtxt.getText();

        HashMap<String, Inventory> query = new HashMap<>();

        List<Inventory> getAll = new ArrayList(inList.values());
        List<Inventory> newValue = new ArrayList();

        String search = "";
        if (null != typHouseCbtx) {
            search += typHouseCbtx + " ";
        }

        if (null != gardent) {
            search += gardent + " ";
        }

        if (null != statustC) {
            search += statustC + " ";
        }

        if ("" != bathroom && !bathroom.isEmpty()) {
            search += bathroom + " ";
        }

        if ("" != area && !area.isEmpty()) {
            search += area + " ";
        }
        if ("" != price && !price.isEmpty()) {
            search += price + " ";
        }

        if ("" != bedrooms && !bedrooms.isEmpty()) {
            search += bedrooms + " ";
        }

        if ("" != search) {

            String[] v = search.toUpperCase().split(" ");

            for (Inventory m : getAll) {

                System.out.println(m.getSearchValues() + " =====" + search);

                int count = 0;
                for (String s : v) {
                    if (m.getSearchValues().contains(s)) {
                        count++;
                    } else {
                        count--;
                    }
                }
                if (v.length == count) {
                    newValue.add(m);
                }
            }

            tableview.setItems(FXCollections.observableArrayList(newValue));
        } else {
            tableview.setItems(FXCollections.observableArrayList(getAll));
        }

    }

    private void receitGenerate(Inventory inv) {

        ReportRecipt recipt = new ReportRecipt(inv);
        List<ReportRecipt> reciptList = new ArrayList<>();
        reciptList.add(recipt);
        ReportController r = new ReportController();
        String file = r.createPDF(reciptList, "bookingNotification.jrxml");
        ConfigurationEmail conf = new ConfigurationEmail(file);

        new Email(conf).sendAttatchFileEmail();
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText(null);
        a.setContentText("Succefull Updated and have infored Booking Customer!");
        a.showAndWait();

    }

}
