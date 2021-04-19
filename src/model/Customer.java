
package model;

import java.io.Serializable;

public class Customer implements Serializable{

    private String name;
    private String phone;
    private String currentAddress;

    public Customer() {
    }
    
    public Customer(String name, String phone, String currentAddress) {
        this.name = name;
        this.phone = phone;
        this.currentAddress = currentAddress;
    }  
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }
    
    
}
