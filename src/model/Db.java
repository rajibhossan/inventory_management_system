package model;

import java.io.Serializable;
import java.util.HashMap;

public class Db implements Serializable {

    private HashMap<String, Customer> cList = new HashMap<>();
    private HashMap<String, House> hList = new HashMap<>();
    private HashMap<String, Inventory> inList = new HashMap<>();
    private HashMap<String, Sales> slList = new HashMap<>();
    private HashMap<String, Rent> rlList = new HashMap<>();
    private HashMap<String, Booking> blList = new HashMap<>();
    private HashMap<String, EmailSetting> elList = new HashMap<>();

    public HashMap<String, Customer> getcList() {
        return cList;
    }

    public void setcList(HashMap<String, Customer> cList) {
        this.cList = cList;
    }

    public HashMap<String, House> gethList() {
        return hList;
    }

    public void sethList(HashMap<String, House> hList) {
        this.hList = hList;
    }

    public HashMap<String, Inventory> getInList() {
        return inList;
    }

    public void setInList(HashMap<String, Inventory> inList) {
        this.inList = inList;
    }

    public HashMap<String, Sales> getSlList() {
        return slList;
    }

    public void setSlList(HashMap<String, Sales> slList) {
        this.slList = slList;
    }

    public HashMap<String, Rent> getRlList() {
        return rlList;
    }

    public void setRlList(HashMap<String, Rent> rlList) {
        this.rlList = rlList;
    }

    public HashMap<String, Booking> getBlList() {
        return blList;
    }

    public void setBlList(HashMap<String, Booking> blList) {
        this.blList = blList;
    }

    public HashMap<String, EmailSetting> getElList() {
        return elList;
    }

    public void setElList(HashMap<String, EmailSetting> elList) {
        this.elList = elList;
    }
    
    

}
