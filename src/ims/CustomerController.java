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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import model.Customer;
import model.Db;
import service.DBService;

public class CustomerController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextArea address;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> cname;
    @FXML
    private TableColumn<Customer, String> cphone;
    @FXML
    private TableColumn<Customer, String> caddress;
    @FXML
    private Button update;

    HashMap<String, Customer> cList;
    Customer d = null;
    private Db dbco;
    private DBService dBService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        cphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        caddress.setCellValueFactory(new PropertyValueFactory<>("currentAddress"));

        dBService = new DBService();
        dbco = dBService.getDB();
        cList = dbco.getcList();

        List<Customer> getAll = new ArrayList(cList.values());
        customerTable.setItems(FXCollections.observableArrayList(getAll));
    }

    private void showTable() {
        dBService = new DBService();
        dbco = dBService.getDB();
        cList = dbco.getcList();
        List<Customer> getAll = new ArrayList(cList.values());
        customerTable.setItems(FXCollections.observableArrayList(getAll));
    }

    @FXML
    private void addAction(ActionEvent event) {

        dBService = new DBService();
        dbco = dBService.getDB();
        cList = dbco.getcList();

        Customer c = new Customer(name.getText(), phone.getText(), address.getText());

        if (cList.get(phone.getText()) == null) {
            cList.put(phone.getText(), c);

            name.setText(null);
            phone.setText(null);
            address.setText(null);

            dbco.setcList(cList);
            dBService.saveDb(dbco);

            showTable();
        } else {
            JOptionPane.showMessageDialog(null, "Customer Alread Exist", "Alert", JOptionPane.WARNING_MESSAGE);
        }

    }

    @FXML
    private void editAction(ActionEvent event) {
        d = customerTable.getSelectionModel().selectedItemProperty().get();
        name.setText(d.getName());
        phone.setText(d.getPhone());
        phone.setEditable(false);
        address.setText(d.getCurrentAddress());
    }

    @FXML
    private void deleteAction(ActionEvent event) {

        dBService = new DBService();
        dbco = dBService.getDB();
        cList = dbco.getcList();
        d = customerTable.getSelectionModel().selectedItemProperty().get();

        if (d != null) {
            cList.remove(d.getPhone());
            d = null;

            dbco.setcList(cList);
            dBService.saveDb(dbco);
            showTable();
        }
    }

    @FXML
    private void updateAction(ActionEvent event) {

        dBService = new DBService();
        dbco = dBService.getDB();
        cList = dbco.getcList();

        Customer c = new Customer();
        c.setName(name.getText());
        c.setCurrentAddress(address.getText());
        c.setPhone(d.getPhone());

        cList.replace(d.getPhone(), c);
        System.out.println();

        d = null;
        name.setText(null);
        phone.setText(null);
        address.setText(null);
        phone.setEditable(true);

       
        dbco.setcList(cList);
        dBService.saveDb(dbco);
        
         showTable();
    }

}
