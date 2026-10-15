//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class VendorTest {

        Vending vend = new Vending(5, 5);


    @Test
    void addMoneyTest() {
        double balance = vend.getBalance();
        vend.addMoney(3);
        assertEquals((balance +3), vend.getBalance());
    }

    @Test
    void buyItemTest(){
        addMoneyTest();
        String name = "Candy";
       int candyStock = vend.getStock(name);
        vend.select(name);
       assertEquals(candyStock-1,vend.getStock(name));
    }

    @Test
    void itemStockTest(){
        assertEquals(5, vend.getStock("Candy"));
    }

    @Test
    void emptyIvenTest(){
        Vending poor = new Vending(1,1);
        poor.addMoney(100);
        for(String e: poor.getStockNames()){
            for (int i = poor.getStock(e); i >= 0 ; i--) {
                poor.select(e);
            }
            assertEquals(0, poor.getStock(e) );
        }
    }

    @Test
    void restockTest(){
        Vending none = new Vending(0,0);
        none.restock(5);
        for(String e: none.getStockNames()){
            assertEquals(5, none.getStock(e));
        }
    }

    @Test
    void newItemTest(){
        Vending two = new Vending(0,0);
       int currentIven = two.getStockNames().size();
        two.restock(5);
        assertEquals(currentIven + 1, two.getStockNames().size());
    }

    @Test
    void itemRenameTest(){
        Set<String> oldNames = new HashSet<>( vend.getStockNames());
        vend.changeName("Candy", "Gumball");

        boolean actual = oldNames.equals(vend.getStockNames());
        assertEquals(oldNames.size(), vend.getStockNames().size());
        assertFalse(actual);
    }

    @Test
    void printIvenTest(){
        ArrayList<Vending> vendorSystem = new ArrayList<>();
        for (int i = 0; i < 6 ; i++) {
            //Vending name = new Vending(i,i);
            //name.printIven();
            vendorSystem.add(new Vending(i,i));
        }
        int f = 1;
        for(Vending h: vendorSystem){
//            int f = 1;
            System.out.println("Vendor " + f);

            h.printIven();
            f++;
        }
    }

    @Test
    void removeItemTest(){
        Set<String> oldNames = new HashSet<>( vend.getStockNames());
        vend.removeItem("Gum");

        boolean actual = oldNames.equals(vend.getStockNames());
        assertEquals(oldNames.size()-1, vend.getStockNames().size());
        assertFalse(actual);
    }

    @Test
    void trackerTest(){
        vend.addItem("Juice", 1, 3);
        vend.addMoney(12);
        vend.printPurchaseHistory();
        vend.select("Juice");
        vend.printPurchaseHistory();
    }


}