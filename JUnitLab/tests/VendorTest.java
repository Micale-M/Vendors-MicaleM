import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                poor.select(e.toString());
            }
            assertEquals(0, poor.getStock(e) );
        }
    }

}