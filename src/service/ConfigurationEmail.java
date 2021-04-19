package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import model.Booking;
import model.Db;
import model.EmailSetting;

public class ConfigurationEmail {

    private List<Booking> list;
    private String file;

    private HashMap<String, EmailSetting> inList;
    private EmailSetting d = null;
    private Db dbco;
    private DBService dBService;

    public ConfigurationEmail( String file) {
        this.file = file;

        dBService = new DBService();
        dbco = dBService.getDB();
        inList = dbco.getElList();
    }

    public List<Booking> getList() {
        return list;
    }

    public void setList(List<Booking> list) {
        this.list = list;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public InternetAddress[] sendTo() throws AddressException {

        dbco = dBService.getDB();
        List<Booking> getList = new ArrayList(dbco.getBlList().values());;

        InternetAddress[] recipientAddress = new InternetAddress[getList.size()];
        for (int i = 0; i < getList.size(); i++) {
            recipientAddress[i] = new InternetAddress(getList.get(i).getEmail());
        }
        return recipientAddress;
    }

    public String getUserName() {
        return inList.get("0").getEmailAddress();
    }

    public String getPassword() {
        return inList.get("0").getPassword();
    }

}
