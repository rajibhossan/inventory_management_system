package model;

import java.io.Serializable;

public class Booking implements Serializable {

    private Customer customer;

    private String name;

    private String phone;

    private String address;

    private String email;

    public Booking() {
    }

    
    
    public Booking(Customer customer) {
        this.customer = customer;
        customerInformation(customer);
    }

    private void customerInformation(Customer customer) {

        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.address = customer.getCurrentAddress();

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

}
