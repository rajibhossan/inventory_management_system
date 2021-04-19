package model;

import java.io.Serializable;

public class Inventory implements Serializable {

    private House house;

    private String id;
    private String TypeOfHouse;
    private Integer bedrooms;
    private Integer bathrooms;
    private Boolean garden;

    private String area;
    private Double Price;
    private Integer quntity;
    private String status;

    public String getSearchValues(){   
        return (TypeOfHouse+" "+bedrooms+" "+bathrooms+" "+garden+" "+area+" "+Price+" "+ status).toUpperCase();
    }
    
    public Inventory() {
    }

    public Inventory(String id, House house, Double Price, Integer quntity, String status) {
        this.id=id;
        this.house = house;
        this.Price = Price;
        this.quntity = quntity;
        this.status = status;        
        setValues(house);
    }
    
    
    private void setValues(House house){        
        this.TypeOfHouse=house.getTypeOfHouse();
        this.area=house.getArea();
        this.bathrooms=house.getBathrooms();
        this.bedrooms=house.getBedrooms();
        this.garden=house.isGarden();
    
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
        setValues(house);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeOfHouse() {
        return TypeOfHouse;
    }

    public void setTypeOfHouse(String TypeOfHouse) {
        this.TypeOfHouse = TypeOfHouse;
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

    public Boolean isGarden() {
        return garden;
    }

    public void setGarden(Boolean garden) {
        this.garden = garden;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public Integer getQuntity() {
        return quntity;
    }

    public void setQuntity(Integer quntity) {
        this.quntity = quntity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

    
    
}
