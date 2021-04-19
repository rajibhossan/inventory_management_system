package model;

import java.io.Serializable;

public class Rent implements Serializable {

    private String id;
    private Customer customer;
    private House house;

    private String name;
    private String phone;
    private String adress;

    private String typeOfHouse;
    private Integer bedrooms;
    private Integer bathrooms;
    private boolean garden;
    private String area;

    private Double deposit1;
    private Double deposit2;
    private Double deposit3;

    private Double damage;
    private String status;
    private String depositStatus;

    public Rent() {
    }

    public Rent(String id, Customer customer, House house, Double deposit1, String status, String depositStatus) {

        this.id = id;
        this.customer = customer;
        this.house = house;
        this.deposit1 = deposit1;
        this.status = status;
        this.depositStatus = depositStatus;

        setCustomerDetails(customer);
        setCustomerDetails(house);
    }

    private void setCustomerDetails(Customer customer) {
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.adress = customer.getCurrentAddress();
    }

    private void setCustomerDetails(House house) {
        this.typeOfHouse = house.getTypeOfHouse();
        this.bedrooms = house.getBedrooms();
        this.bathrooms = house.getBathrooms();
        this.garden = house.isGarden();
        this.area = house.getArea();
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

    public Double getDeposit1() {
        return deposit1;
    }

    public void setDeposit1(Double deposit1) {
        this.deposit1 = deposit1;
    }

    public Double getDeposit2() {
        return deposit2;
    }

    public void setDeposit2(Double deposit2) {
        this.deposit2 = deposit2;
    }

    public Double getDeposit3() {
        return deposit3;
    }

    public void setDeposit3(Double deposit3) {
        this.deposit3 = deposit3;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTypeOfHouse() {
        return typeOfHouse;
    }

    public void setTypeOfHouse(String typeOfHouse) {
        this.typeOfHouse = typeOfHouse;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    
    
    

}
