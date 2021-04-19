
package model;

import java.io.Serializable;

public class House implements Serializable{

    private String id;
    private String TypeOfHouse;
    private Integer bedrooms;
    private Integer bathrooms;
    private boolean garden;    
    private String area;

    public House() {
    }


    public House(String id, String TypeOfHouse,  Integer bedrooms, Integer bathrooms, boolean garden, String area) {
        this.id = id;
        this.TypeOfHouse = TypeOfHouse;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.garden = garden;
        this.area = area;
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

    @Override
    public String toString() {
        return  TypeOfHouse ;
    }


}
