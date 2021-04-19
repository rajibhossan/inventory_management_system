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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Db;
import model.Rent;
import model.ReportRecipt;
import service.DBService;
import service.ReportController;

public class DepositPaymentController implements Initializable {

    @FXML
    private Button salebtn;
    @FXML
    private Button selectbtn1;
    @FXML
    private TextField pricetxt;

    @FXML
    private TableView<Rent> tableviewR;
    @FXML
    private TableColumn<Rent, String> idColumn;
    @FXML
    private TableColumn<Rent, String> cname;
    @FXML
    private TableColumn<Rent, String> cphone;
    @FXML
    private TableColumn<Rent, String> caddress;

    @FXML
    private TableColumn<Rent, String> typOfHouseColumn;
    @FXML
    private TableColumn<Rent, Integer> bedroomsColumn;
    @FXML
    private TableColumn<Rent, Integer> bathroomsColumn;
    @FXML
    private TableColumn<Rent, Boolean> gardenColumn;
    @FXML
    private TableColumn<Rent, String> areaColumn;
    @FXML
    private TableColumn<Rent, Double> deposit1Column;
    @FXML
    private TableColumn<Rent, Double> deposit2Column;
    @FXML
    private TableColumn<Rent, Double> deposit3Column;
    @FXML
    private TableColumn<Rent, String> statusColumn;

    @FXML
    private TextField depositAgent;
    @FXML
    private TextField deposit1;
    @FXML
    private TextField deposit2;
    @FXML
    private TextField deposit3;
    @FXML
    private TextField damagetxt;

    private Rent rent = null;

    private Db dbco;
    private DBService dBService;
    private HashMap<String, Rent> rlList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Rent Table
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        cphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        caddress.setCellValueFactory(new PropertyValueFactory<>("adress"));

        typOfHouseColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfHouse"));
        bedroomsColumn.setCellValueFactory(new PropertyValueFactory<>("bedrooms"));
        bathroomsColumn.setCellValueFactory(new PropertyValueFactory<>("bathrooms"));
        gardenColumn.setCellValueFactory(new PropertyValueFactory<>("garden"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));

        deposit1Column.setCellValueFactory(new PropertyValueFactory<>("deposit1"));
        deposit2Column.setCellValueFactory(new PropertyValueFactory<>("deposit2"));
        deposit3Column.setCellValueFactory(new PropertyValueFactory<>("deposit3"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("depositStatus"));

        depositAgent.setEditable(false);
        tableviewR.setItems(FXCollections.observableArrayList(getAll()));

    }

    public List<Rent> getAll() {
        dBService = new DBService();
        dbco = dBService.getDB();

        rlList = dbco.getRlList();

        List<Rent> getAllc = new ArrayList(rlList.values());

//        List<Rent> result = getAllc.stream()
//                .filter(a -> Objects.equals(a.getDepositStatus(), "PARTIAL_PAID"))
//                .collect(Collectors.toList());
        return getAllc;
    }

    private void showTable() {
        tableviewR.setItems(FXCollections.observableArrayList(getAll()));

    }

    @FXML
    private void selectHouseAction(ActionEvent event) {

        rent = tableviewR.getSelectionModel().selectedItemProperty().get();
        deposit1.setText(rent.getDeposit1() + "");
        if (null != rent.getDeposit2()) {

            deposit2.setText(rent.getDeposit2() + "");
            if (null != rent.getDeposit3()) {
                 deposit3.setText(rent.getDeposit3() + "");
            } else {

            }
            deposit3.setEditable(true);
            damagetxt.setEditable(true);

        } else {
            deposit2.setEditable(true);
            deposit3.setEditable(false);

        }

    }

    @FXML
    private void paymentAction(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText(null);

        rent = tableviewR.getSelectionModel().selectedItemProperty().get();

        if (rent == null) {
            a.setContentText("Please Select Rent row From Rent Table!");
            a.show();
            return;
        }

        if (null == rent.getDeposit2()) {
            Double depo2 = Double.valueOf(deposit2.getText());
            rent.setDeposit2(depo2);
        } else {
            Double damage = Double.valueOf(damagetxt.getText());
            Double depo3 = Double.valueOf(deposit3.getText());
            rent.setDeposit3(depo3);
            rent.setDepositStatus("PAID");
            rent.setDamage(damage);
        }

        String id = rent.getId();
        rlList.replace(id, rent);

        dBService = new DBService();
        dbco = dBService.getDB();

        dbco.setRlList(rlList);
        dBService.saveDb(dbco);

        showTable();

        if (rent.getDeposit3() != null) {
            a.setContentText("You have Paid all money");
            a.showAndWait();
            receitGenerate(rent);
        }

    }

    private void receitGenerate(Rent rent) {

        ReportRecipt recipt = new ReportRecipt(rent);
        List<ReportRecipt> reciptList = new ArrayList<>();
        reciptList.add(recipt);
        ReportController r = new ReportController();
        r.createPDF(reciptList);
    }

}
