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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import model.Booking;
import model.Customer;
import model.Db;
import service.DBService;

public class BookingController implements Initializable {

    @FXML
    private TableView<Customer> tableview;
    @FXML
    private Button bookingbtn;
    @FXML
    private TextField nametext;
    @FXML
    private TextField phonetxt;
    @FXML
    private TextField addresstext;
    @FXML
    private Button selectbtn;

    @FXML
    private TableColumn<Customer, String> cname;
    @FXML
    private TableColumn<Customer, String> cphone;
    @FXML
    private TableColumn<Customer, String> caddress;

    HashMap<String, Customer> cList;
    HashMap<String, Booking> bList;
    Customer d = null;
    private Db dbco;
    private DBService dBService;
    @FXML
    private TextField emailtxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        cphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        caddress.setCellValueFactory(new PropertyValueFactory<>("currentAddress"));

        dBService = new DBService();
        dbco = dBService.getDB();
        cList = dbco.getcList();
        bList = dbco.getBlList();

        List<Customer> getAll = new ArrayList(cList.values());
        tableview.setItems(FXCollections.observableArrayList(getAll));

    }

    @FXML
    private void addBookingAction(ActionEvent event) {
        d = tableview.getSelectionModel().selectedItemProperty().get();

        Booking b = new Booking(d);
        b.setEmail(emailtxt.getText());

        if (bList.get(b.getPhone()) == null) {

            bList.put(b.getPhone(), b);
            nametext.setText(null);
            phonetxt.setText(null);
            addresstext.setText(null);
            emailtxt.setText(null);
            dbco.setcList(cList);
            dBService.saveDb(dbco);
            JOptionPane.showMessageDialog(null, "Thanks Mr." + b.getName() + ", You book is done", "Alert", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Customer Alread Exist", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    private void selectAction(ActionEvent event) {

        d = tableview.getSelectionModel().selectedItemProperty().get();
        nametext.setText(d.getName());
        phonetxt.setText(d.getPhone());
        addresstext.setText(d.getCurrentAddress());
    }

}
