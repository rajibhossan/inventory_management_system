
import junit.framework.TestCase;
import model.Customer;
import model.House;

public class imsTesting extends TestCase {

    public void testEqual() {
        House house = new House("01", "Bungalow", 5, 2, Boolean.TRUE, "200");
        assertEquals(house.getTypeOfHouse(), "Bungalow");
        assertEquals(house.getBedrooms(), Integer.valueOf("5"));
        assertEquals(house.getBathrooms(), Integer.valueOf("2"));
        assertEquals(house.isGarden(), true);
        assertEquals(house.getArea(), "200"); 
    }

    public void testScalarMultiplication() {
        Customer c=new Customer("Molly B Ali", "07076438627", "44  Sandyhill Rd");        
        assertEquals(c.getName(), "Molly B Ali");
        assertEquals(c.getPhone(), "07076438627");
        assertEquals(c.getCurrentAddress(), "44  Sandyhill Rd");
       
       
    }

}
