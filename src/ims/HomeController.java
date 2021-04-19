package ims;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.User;

public class HomeController implements Initializable {

    private final Stage stage = new Stage();
    
    private Parent root;
    private Scene scene;
    private FXMLLoader loader;
    
    @FXML
    private Button bt2;
    @FXML
    private Button bt3;
    @FXML
    private Button bt4;
    @FXML
    private Button bt5;
    @FXML
    private Button bt6;
    @FXML
    private Button bt7;
    @FXML
    private Button bt8;
    @FXML
    private Label lbrole;

    private User user;
    @FXML
    private AnchorPane anchorId;
    @FXML
    private Button customer;
    @FXML
    private Label userlab;
    @FXML
    private Button bt71;
    @FXML
    private Button bt72;

    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setRole(User user) {
        this.user = user;
        userlab.setText("Loing " + user.getName());
    }

    @FXML
    private void showCustomerForm(ActionEvent event) {

        try {
            if ("administrator".equals(user.getRole()) || "sales".equals(user.getRole())) {

                root = FXMLLoader.load(getClass().getResource("/view/Customer.fxml"));
                scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Customer Form");
                stage.show();

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText(null);
                a.setContentText("you don't haven permission");
                a.show();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void showHouseForm(ActionEvent event) {

        try {
            if ("administrator".equals(user.getRole()) || "sales".equals(user.getRole())) {

                root = FXMLLoader.load(getClass().getResource("/view/House.fxml"));
                scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("House Form");
                stage.show();

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText(null);
                a.setContentText("you don't haven permission");
                a.show();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addInventoryAction(ActionEvent event) {

        try {
            if ("administrator".equals(user.getRole()) || "stock".equals(user.getRole())) {
                root = FXMLLoader.load(getClass().getResource("/view/addtoInventory.fxml"));
                scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("House Form");
                stage.show();

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText(null);
                a.setContentText("you don't haven permission");
                a.show();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void inventoryAction(ActionEvent event) {

        try {
            if ("administrator".equals(user.getRole()) || "stock".equals(user.getRole())) {
                root = FXMLLoader.load(getClass().getResource("/view/Inventory.fxml"));
                scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Inventory Form");
                stage.show();

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText(null);
                a.setContentText("you don't haven permission");
                a.show();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void salesAction(ActionEvent event) {

        try {
            if ("administrator".equals(user.getRole()) || "sales".equals(user.getRole())) {
                root = FXMLLoader.load(getClass().getResource("/view/Sales.fxml"));
                scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Sales Form");
                stage.show();

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText(null);
                a.setContentText("you don't haven permission");
                a.show();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void rentAction(ActionEvent event) {
        try {
            if ("administrator".equals(user.getRole()) || "sales".equals(user.getRole())) {
                root = FXMLLoader.load(getClass().getResource("/view/Rent.fxml"));
                scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Rent Form");
                stage.show();

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText(null);
                a.setContentText("you don't haven permission");
                a.show();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void bookingAction(ActionEvent event) {

        try {
            if ("administrator".equals(user.getRole()) || "sales".equals(user.getRole())) {
                root = FXMLLoader.load(getClass().getResource("/view/Booking.fxml"));
                scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Booking Form");
                stage.show();

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText(null);
                a.setContentText("you don't haven permission");
                a.show();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void depositAction(ActionEvent event) {

        try {
            if ("administrator".equals(user.getRole()) || "sales".equals(user.getRole())) {
                root = FXMLLoader.load(getClass().getResource("/view/DepositPayment.fxml"));
                scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Deposit Payment Form");
                stage.show();

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText(null);
                a.setContentText("you don't haven permission");
                a.show();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void emailSettingAction(ActionEvent event) {
        
        try {
            if ("administrator".equals(user.getRole())) {
                root = FXMLLoader.load(getClass().getResource("/view/emailSetting.fxml"));
                scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Email Setting Form");
                stage.show();

            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText(null);
                a.setContentText("you don't haven permission");
                a.show();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void LogoutAction(ActionEvent event) {
        
        Stage primaryStage = (Stage) anchorId.getScene().getWindow();
        try {

            root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            scene = new Scene(root);

            stage.centerOnScreen();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.setResizable(false);
            stage.show();

            primaryStage.hide();

            stage.setOnCloseRequest((WindowEvent e) -> {
                Platform.exit();
                System.exit(0);
            });

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
