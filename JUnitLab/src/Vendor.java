import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    private HashMap<String, Item> Stock = new HashMap<String,Item>();
    private double balance;
    private String[] listOfOtherItems;

    Vending(int numCandy, int numGum) {
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
        this.balance = 0;

        listOfOtherItems = new String[]{"Chocolate", "Chips", "Drinks"};
    }

    /** resets the Balance to 0 */
    void resetBalance () {
        this.balance = 0;
    }

    /** returns the current balance */
    double getBalance () {
        return this.balance;
    }

    /** adds money to the machine's balance
     * @param amt how much money to add
     * */
    void addMoney (double amt) {
        this.balance = this.balance + amt;
    }

    /** attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select (String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if ((balance >= item.price) && (item.stock > 0)) {
                item.purchase(1);
                this.balance = this.balance - item.price;
                //Stock.replace(name, new Item(item.price, item.stock-1));
            }
            else
                System.out.println("Insufficient funds or item out of stock");
        }
        else System.out.println("Sorry, don't know that item");
    }

    int getStock(String name){
        if (Stock.containsKey(name)) {
        return Stock.get(name).stock;
    }
        return 0;
    }
    
     Set<String> getStockNames(){
         return Stock.keySet();
    }

    void restock(int num){
        Random gen = new Random();
        int count = 0;

        for(String e: this.getStockNames()){
            if(Stock.get(e).stock == 0){
                Stock.get(e).stock = num;
                        count++;
                }
            }
        if(count > 0){
            String name = listOfOtherItems[gen.nextInt(listOfOtherItems.length)];
            if(Stock.containsKey(name)){
            } else {
                this.addItem(name, 5, 5);
        }
        }
    }

    void addItem(String itemName, double price, int amount){
        Stock.put(itemName, new Item(price, amount));
    }

    void changeName(String oldItem, String newItem ) {
        if (Stock.containsKey(oldItem)) {
            Item i = Stock.remove(oldItem);
            Stock.put(newItem, i);
        }
    }

    void printIven(){
            System.out.println("Item:Stock");
            for(String e: this.getStockNames()){
                System.out.println(e + ":" + this.getStock(e));
            }

        }

        void removeItem(String item){
            if (Stock.containsKey(item)) {
                Stock.remove(item);
            }
        }

}


//class Examples {
//}

