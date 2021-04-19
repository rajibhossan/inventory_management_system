package model;

public class ReportRecipt {
//House 

    private String id;
    private String typeOfHouse;
    private Integer bedrooms;
    private Integer bathrooms;
    private Boolean garden;
    private String area;
    private Double price;
    private Integer quntity;
    private String status;

    // Reciet
    private String name;
    private String phone;
    private String address;

    private Double deposit1;
    private Double deposit2;
    private Double deposit3;
    private Double damage = 0.0d;

    private Double agentFee;
    private Double totalDepositAmonut;

    public ReportRecipt() {
    }

    public ReportRecipt(Rent rent) {
        this.name = rent.getName();
        this.phone = rent.getPhone();
        this.address = rent.getAdress();
        this.deposit1 = rent.getDeposit1();
        this.deposit2 = rent.getDeposit2();
        this.deposit3 = rent.getDeposit3();
        this.damage = rent.getDamage();
        this.agentFee = 13.5;
        this.totalDepositAmonut = (deposit1 + deposit2 + deposit3 + agentFee + damage);
    }

    public ReportRecipt(Inventory inv) {
        this.id = inv.getId();
        this.typeOfHouse = inv.getHouse().getTypeOfHouse();
        this.bedrooms = inv.getHouse().getBedrooms();
        this.bathrooms = inv.getHouse().getBathrooms();
        this.garden = inv.getHouse().isGarden();
        this.area = inv.getHouse().getArea();
        this.price = inv.getPrice();
        this.quntity = inv.getQuntity();
        this.status = inv.getStatus();

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

    public Double getAgentFee() {
        return agentFee;
    }

    public void setAgentFee(Double agentFee) {
        this.agentFee = agentFee;
    }

    public Double getTotalDepositAmonut() {
        return totalDepositAmonut;
    }

    public void setTotalDepositAmonut(Double totalDepositAmonut) {
        this.totalDepositAmonut = totalDepositAmonut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getGarden() {
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
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
