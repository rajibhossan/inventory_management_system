package model;

import java.io.Serializable;

public class Sales implements Serializable{

    private Customer customer;
    private House house;

    private String id;
    private String cName;
    private String cPhone;
    private String cAdress;
    
    private String hTypeOfHouse;
    private Integer hBedrooms;
    private Integer hBathrooms;
    private boolean hGarden;
    private String hArea;
    
    private Double price;
    private Double totalPrice;
    private Double agentFee;
    private String status;



    public Sales() {}

    public Sales(String id, Customer customer, House house, Double price, Double agentFee, Double totalPrice, String status) {
        this.id=this.id;
        this.customer = customer;
        this.house = house;
        this.price = price;
        this.agentFee = agentFee;
        this.totalPrice = totalPrice;
        this.status = status;

        setCustomerDetails(customer);
        setCustomerDetails(house);
    }

    private void setCustomerDetails(Customer customer) {
        this.cName = customer.getName();
        this.cPhone = customer.getPhone();
        this.cAdress = customer.getCurrentAddress();
    }

    private void setCustomerDetails(House house) {
        this.hTypeOfHouse = house.getTypeOfHouse();
        this.hBedrooms = house.getBedrooms();
        this.hBathrooms = house.getBathrooms();
        this.hGarden = house.isGarden();
        this.hArea = house.getArea();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(Double agentFee) {
        this.agentFee = agentFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public String getcAdress() {
        return cAdress;
    }

    public void setcAdress(String cAdress) {
        this.cAdress = cAdress;
    }

    public String gethTypeOfHouse() {
        return hTypeOfHouse;
    }

    public void sethTypeOfHouse(String hTypeOfHouse) {
        this.hTypeOfHouse = hTypeOfHouse;
    }

    public Integer gethBedrooms() {
        return hBedrooms;
    }

    public void sethBedrooms(Integer hBedrooms) {
        this.hBedrooms = hBedrooms;
    }

    public Integer gethBathrooms() {
        return hBathrooms;
    }

    public void sethBathrooms(Integer hBathrooms) {
        this.hBathrooms = hBathrooms;
    }

    public boolean ishGarden() {
        return hGarden;
    }

    public void sethGarden(boolean hGarden) {
        this.hGarden = hGarden;
    }

    public String gethArea() {
        return hArea;
    }

    public void sethArea(String hArea) {
        this.hArea = hArea;
    }

}
