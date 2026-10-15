class Item {
    double price;
    int stock;
    String description;
    boolean bestSeller = false;

    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
    }

    void setDescription(String desc){
        description = desc;
    }

    String getDescription(){
        return description;
    }

    void setBestSellerTrue(){
        bestSeller = true;
    }

    void setBestSellerFalse(){
        bestSeller = false;
    }

    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    void purchase(int amount) {
        this.stock = this.stock - amount;
    }
}