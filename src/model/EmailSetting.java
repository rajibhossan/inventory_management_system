package model;

import java.io.Serializable;

public class EmailSetting implements Serializable {

    private String emailAddress;
    private String password;

    public EmailSetting() {
    }

    
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    

}
