package ims;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Db;
import model.EmailSetting;
import service.DBService;

public class EmailSettingController implements Initializable {

    @FXML
    private TextField emailtxt;
    @FXML
    private PasswordField passwordtxt;
    @FXML
    private Button bt1;
    @FXML
    private Button bt2;

    HashMap<String, EmailSetting> eList;
    EmailSetting d = null;
    private Db dbco;
    private DBService dBService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dBService = new DBService();
        dbco = dBService.getDB();
        eList = dbco.getElList();

        List<EmailSetting> getAll = new ArrayList(eList.values());

        if (getAll.size() != 0) {
            EmailSetting e = getAll.get(0);
            emailtxt.setText(e.getEmailAddress());
            emailtxt.setDisable(true);
            passwordtxt.setText(e.getPassword());
            passwordtxt.setDisable(true);
            bt1.setDisable(true);

        }
    }

    @FXML
    private void okAction(ActionEvent event) {
        dBService = new DBService();
        dbco = dBService.getDB();

        String email = emailtxt.getText();
        String pass = passwordtxt.getText();
        EmailSetting e = new EmailSetting();
        e.setEmailAddress(email);
        e.setPassword(pass);
        eList.put("0", e);
        dbco.setElList(eList);
        dBService.saveDb(dbco);

        emailtxt.setDisable(true);
        passwordtxt.setDisable(true);
        bt1.setDisable(true);
        bt2.setDisable(false);

    }

    @FXML
    private void editAction(ActionEvent event) {

        emailtxt.setDisable(false);
        passwordtxt.setDisable(false);
        bt2.setDisable(true);
        bt1.setDisable(false);
    }

}
