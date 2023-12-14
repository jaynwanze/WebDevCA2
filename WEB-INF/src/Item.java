public class Item {

    String itemName;
    String itemPrice;
    String username;;
    String datePosted;

    public Item() {

    }

    public Item(String itemName, String itemPrice, String username, String datePosted) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.username = username;
        this.datePosted = datePosted;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    
}
