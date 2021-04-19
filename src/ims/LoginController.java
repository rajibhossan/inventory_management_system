/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ims;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;


public class LoginController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    
     private Stage stage = new Stage();
     
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void submitLoginAction(ActionEvent event) {
        
        String usernametext=username.getText();
        String usernamePassword=password.getText();
        
        String role="";
        
        if(usernametext.equals("sales") && usernamePassword.equals("123") )
            role="sales";
        if(usernametext.equals("stock")&& usernamePassword.equals("123") )
            role="stock";
        if(usernametext.equals("admin")&& usernamePassword.equals("123") )
            role="administrator";
        
        if (!"".equals(role)) {
            try {

                User user=new User(usernametext,usernamePassword, role);
                
                               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Home.fxml"));
                Parent root = loader.load();
                HomeController controller = loader.getController();
                controller.setRole(user);

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Invenory Management System");
                stage.setResizable(true);     
                stage.show();
                
                ((Stage) ((Node) event.getSource()).getScene().getWindow()).hide();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                label.setText(ex.getMessage());
            }
        } else {
            label.setText("UserId and Password Worng !");
        }
        
    }
    
}
