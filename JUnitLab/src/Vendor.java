import java.util.*;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    private HashMap<String, Item> Stock = new HashMap<String, Item>();
    private double balance;
    private String[] listOfOtherItems;
    private static HashMap<String, Integer> Tracker = new HashMap<String, Integer>(); //Item and number of times purchased
    private ArrayList<String> mostPurchased = new ArrayList<>();

    Vending(int numCandy, int numGum) {
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
        this.balance = 0;

        listOfOtherItems = new String[]{"Chocolate", "Chips", "Drinks"};
    }

    /**
     * resets the Balance to 0
     */
    void resetBalance() {
        this.balance = 0;
    }

    /**
     * returns the current balance
     */
    double getBalance() {
        return this.balance;
    }

    /**
     * adds money to the machine's balance
     *
     * @param amt how much money to add
     *
     */
    void addMoney(double amt) {
        this.balance = this.balance + amt;
    }

    /**
     * attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select(String name){
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if ((balance >= item.price) && (item.stock > 0)) {
                item.purchase(1);
                this.balance = this.balance - item.price;

                if (Tracker.containsKey(name)) {
                    Tracker.replace(name, Tracker.get(name) + 1);
                } else {
                    Tracker.put(name, 1);
                }
                //Stock.replace(name, new Item(item.price, item.stock-1));
            } else
                System.out.println("Insufficient funds or item out of stock");
        } else System.out.println("Sorry, don't know that item");
    }

    int getStock(String name) {
        if (Stock.containsKey(name)) {
            return Stock.get(name).stock;
        }
        return 0;
    }

    double getPrice(String name) {
        if (Stock.containsKey(name)) {
            return Stock.get(name).price;
        }
        return 0;
    }

    boolean isbestSeller(String name) {
        if (Stock.containsKey(name)) {
            return Stock.get(name).bestSeller;
        }
        return false;
    }

    Set<String> getStockNames() {
        return Stock.keySet();
    }

    void restock(int num) {
        Random gen = new Random();
        int count = 0;

        for (String e : this.getStockNames()) {
            if (Stock.get(e).stock == 0) {
                Stock.get(e).restock(num);// = num;
                count++;
            }
        }
        if (count > 0) {
            String name = listOfOtherItems[gen.nextInt(listOfOtherItems.length)];
            if (Stock.containsKey(name)) {
            } else {
                this.addItem(name, 5, 5);
            }
        }
    }

    void addItem(String itemName, double price, int amount) {
        Stock.put(itemName, new Item(price, amount));
    }

    void changeName(String oldItem, String newItem) {
        if (Stock.containsKey(oldItem)) {
            Item i = Stock.remove(oldItem);
            Stock.put(newItem, i);
        }
    }

    void printIven() {
        System.out.println("Item:Stock");
        for (String e : this.getStockNames()) {
            System.out.println(e + ":" + this.getStock(e));
        }

    }

    void removeItem(String item) {
        if (Stock.containsKey(item)) {
            Stock.remove(item);
        }
    }

    void printPurchaseHistory() {
        System.out.println("Item:Times Purchased");
        if (Tracker.isEmpty()) {
            System.out.println("No items have been purchased yet");
            return;
        }
        for (String e : Tracker.keySet()) {
            System.out.println(e + ":" + Tracker.get(e));
        }
    }

    void getDescription(String name, String verdict) {
        //Scanner sc = new Scanner(System.in);
        if (Stock.containsKey(name)) {
            System.out.println("Would you like a description of this item? Y/N");
            //String verdict = sc.nextLine();
            if (verdict.equalsIgnoreCase("Y") || verdict.equalsIgnoreCase("Yes")) {
                if (Stock.get(name).description != null) {
                    System.out.println(Stock.get(name).getDescription());
                } else {
                    System.out.println("No description available");
                }
            }
        } else {
            System.out.println("Sorry, don't know that item");
        }
    }

    void setDescription(String item, String desc){
        if (Stock.containsKey(item)){
            Stock.get(item).setDescription(desc);
        }
    }

    void setDiscount(String item, double num){
        if (Stock.containsKey(item) && ((num>= 0) &&(num<=100))) {
            int stock = Stock.get(item).stock;
            double discount = 1.0-(num/100.0);
            double price = ((Stock.get(item).price)*(discount));
            Stock.replace(item, new Item(price, stock));
        }
    }

    void bestSeller(){
        if(Tracker.isEmpty()){
            return;
        }
        int maxValue = 0;
//        ArrayList<String> mostPurchased = new ArrayList<>();
        for (String e: Tracker.keySet()){
            int value = Tracker.get(e);
            if(value > maxValue){
                maxValue = value;
                mostPurchased.clear();
                mostPurchased.add(e);
            } else if (value == maxValue) {
                mostPurchased.add(e);
            }
        }
        for (String s : mostPurchased) {
            for (String k : Tracker.keySet()) {
                if (k.equals(s)) {
                    Stock.get(k).setBestSellerTrue();
                } else {
                    Stock.get(k).setBestSellerFalse();
                }
            }

        }

        if(!mostPurchased.isEmpty()){
            if(mostPurchased.size()>1) {
                System.out.println("The best selling items are the following:");
            } else if (mostPurchased.size()==1) {
                System.out.println("The best selling item is:");
            } else if (mostPurchased.size()==0) {
                System.out.println("No items have been purchased");
                return;
            }
            for(String e: mostPurchased){
                System.out.println(e);
            }
        }
    }

    void printBestSellers(){
        if(!mostPurchased.isEmpty()){
            if(mostPurchased.size()>1) {
                System.out.println("The best selling items are the following:");
            } else if (mostPurchased.size()==1) {
                System.out.println("The best selling item is:");
            } else if (mostPurchased.size()==0) {
                System.out.println("No items have been purchased");
                return;
            }
            for(String e: mostPurchased){
            System.out.println(e);
        }
    }}

}



//class Examples {
//}

