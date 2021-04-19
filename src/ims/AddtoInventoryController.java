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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Db;
import model.House;
import model.Inventory;
import service.DBService;

public class AddtoInventoryController implements Initializable {

    @FXML
    private TableView<House> tableview;
    @FXML
    private TextField pricetx;
    @FXML
    private TextField quintitytxt;
    @FXML
    private Button addIbtn;
    @FXML
    private ComboBox<String> saletypeCombo;
    
    
    
    @FXML
    private TableColumn<House, String> idColumn;
    @FXML
    private TableColumn<House, String> typOfHouseColumn;
    @FXML
    private TableColumn<House, Integer> bedroomsColumn;
    @FXML
    private TableColumn<House, Integer> bathroomsColumn;
    @FXML
    private TableColumn<House, Boolean> gardenColumn;
    @FXML
    private TableColumn<House, String> areaColumn;

    private HashMap<String, House> hList;
    private HashMap<String, Inventory> inList;
    private House d = null;
    private Db dbco;
    private DBService dBService;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        typOfHouseColumn.setCellValueFactory(new PropertyValueFactory<>("TypeOfHouse"));
        bedroomsColumn.setCellValueFactory(new PropertyValueFactory<>("bedrooms"));
        bathroomsColumn.setCellValueFactory(new PropertyValueFactory<>("bathrooms"));
        gardenColumn.setCellValueFactory(new PropertyValueFactory<>("garden"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));

        saletypeCombo.setItems(FXCollections.observableArrayList(
                "SALE",
                "RENT"));
        
                
        dBService = new DBService();
        dbco = dBService.getDB();

        List<House> getAll = new ArrayList(showValues().values());
        tableview.setItems(FXCollections.observableArrayList(getAll));

    }

    private void showTable() {
        hList = dbco.gethList();
        List<House> getAll = new ArrayList(showValues().values());
        tableview.setItems(FXCollections.observableArrayList(getAll));
    }

    private HashMap<String, House> showValues() {
        hList = dbco.gethList();
        inList = dbco.getInList();

        HashMap<String, House> a = new HashMap<>(hList);
        if(inList!=null){
            for (Map.Entry<String, Inventory> e : inList.entrySet()) {
                Inventory i = e.getValue();
                a.remove(i.getHouse().getId());
            }
        }
        return a;
    }

    @FXML
    private void addInventoryAction(ActionEvent event) {

        d = tableview.getSelectionModel().selectedItemProperty().get();

        Integer id = 0;
        if (inList == null) {
            id = 0;
        } else {
            id = (inList.size() + 1);
        }

        Double price = Double.valueOf(pricetx.getText());
        Integer quantity=Integer.valueOf(quintitytxt.getText());
        
        String saleType= saletypeCombo.getSelectionModel().getSelectedItem();
        
        
        Inventory c = new Inventory("" + id, d, price,quantity,saleType);
        //Inventory(String id, House house, Double Price, Integer quntity, String status)        
        
        
        inList.put("" + id, c);        
        dbco.sethList(hList);
        dBService.saveDb(dbco);
        showTable();

    }

}
