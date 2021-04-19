
package model;

import java.util.ArrayList;

public class Payment {

    private ArrayList<Sales> sales = new ArrayList<>();

    public Payment() {
    }

    public ArrayList<Sales> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sales> sales) {
        this.sales = sales;
    }

    public void addSale(Sales sale) {
        sales.add(sale);
    }

}
