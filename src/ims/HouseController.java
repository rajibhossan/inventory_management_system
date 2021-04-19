package ims;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Db;
import model.House;
import service.DBService;

public class HouseController implements Initializable {

    @FXML
    private TextField bedrooms;
    @FXML
    private TextField area;
    @FXML
    private TextField bathrooms;

    @FXML
    private Button addbtn;
    @FXML
    private Button editbtn;
    @FXML
    private Button deletebtn;
    @FXML
    private Button updatebtn;

    @FXML
    private ComboBox<String> typHouseCb;
    @FXML
    private ComboBox<String> garden;

    @FXML
    private TableView<House> tableview;
    @FXML
    private TableColumn<House, String> houseTypeColumn;
    @FXML
    private TableColumn<House, String> bedroomsColumn;
    @FXML
    private TableColumn<House, String> bathroomsColumn;
    @FXML
    private TableColumn<House, Boolean> gardenColumn;
    @FXML
    private TableColumn<House, String> areaColumn;

    HashMap<String, House> hList;
    House d = null;
    private Db dbco;
    private DBService dBService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        houseTypeColumn.setCellValueFactory(new PropertyValueFactory<>("TypeOfHouse"));
        bedroomsColumn.setCellValueFactory(new PropertyValueFactory<>("bedrooms"));
        bathroomsColumn.setCellValueFactory(new PropertyValueFactory<>("bathrooms"));
        gardenColumn.setCellValueFactory(new PropertyValueFactory<>("garden"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));

        typHouseCb.setItems(FXCollections.observableArrayList(
                "Bungalow",
                "Flat",
                "Detached"));

        garden.setItems(FXCollections.observableArrayList(
                "TRUE",
                "FALSE"));

        dBService = new DBService();
        dbco = dBService.getDB();
        hList = dbco.gethList();

        List<House> getAll = new ArrayList(hList.values());
        tableview.setItems(FXCollections.observableArrayList(getAll));

    }

    private void showTable() {
        hList = dbco.gethList();
        List<House> getAll = new ArrayList(hList.values());
        tableview.setItems(FXCollections.observableArrayList(getAll));
    }

    @FXML
    private void addAction(ActionEvent event) {
        hList = dbco.gethList();

        Boolean gar = Boolean.parseBoolean(garden.getSelectionModel().getSelectedItem());

        Integer broom = 0;
        if (!bedrooms.getText().isEmpty()) {
            broom = Integer.valueOf(bedrooms.getText());
        }

        Integer baroomt = 0;
        if (!bathrooms.getText().isEmpty()) {
            baroomt = Integer.valueOf(bathrooms.getText());
        }

        Integer id = 0;
        if (hList == null) {
            id = 0;
        } else {
            id = (hList.size() + 1);
        }
        House c = new House("" + id, typHouseCb.getSelectionModel().getSelectedItem(), broom, baroomt, gar, area.getText());

        hList.put("" + id, c);
        bedrooms.setText(null);
        bathrooms.setText(null);
        area.setText(null);
        dbco.sethList(hList);
        dBService.saveDb(dbco);
        showTable();

    }

    @FXML
    private void editAction(ActionEvent event) {

        d = tableview.getSelectionModel().selectedItemProperty().get();
        typHouseCb.getSelectionModel().select(d.getTypeOfHouse());
        garden.getSelectionModel().select(Boolean.toString(d.isGarden()));
        bathrooms.setText(String.valueOf(d.getBathrooms()));
        bedrooms.setText(String.valueOf(d.getBedrooms()));
        area.setText(String.valueOf(d.getArea()));
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        d = tableview.getSelectionModel().selectedItemProperty().get();

        if (d != null) {
            hList.remove(d.getId());
            d = null;
            showTable();

            dbco.sethList(hList);
            dBService.saveDb(dbco);
        }
    }

    @FXML
    private void updateAction(ActionEvent event) {

        Boolean gar = Boolean.parseBoolean(garden.getSelectionModel().getSelectedItem());
        Integer broom = 0;
        if (!bedrooms.getText().isEmpty()) {
            broom = Integer.valueOf(bedrooms.getText());
        }

        Integer baroomt = 0;
        if (!bathrooms.getText().isEmpty()) {
            baroomt = Integer.valueOf(bathrooms.getText());
        }

        Integer id = 0;
        if (hList == null) {
            id = 0;
        } else {
            id = (hList.size() + 1);
        }
        House c = new House("" + id, typHouseCb.getSelectionModel().getSelectedItem(), broom, baroomt, gar, area.getText());

        hList.replace(d.getId(), c);
        showTable();

        d = null;
        bedrooms.setText(null);
        bathrooms.setText(null);
        area.setText(null);
        dbco.sethList(hList);
        dBService.saveDb(dbco);
        showTable();

        dbco.sethList(hList);
        dBService.saveDb(dbco);
    }

}
