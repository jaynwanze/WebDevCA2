public class Bid {

    private String bidPrice;
    private String bidder;
    private String datePosted;
    private String itemName;

    public Bid() {

    }

    public Bid(String bidPrice, String bidder, String datePosted, String itemName) {
        this.bidPrice = bidPrice;
        this.bidder = bidder;
        this.datePosted = datePosted;
        this.itemName = itemName;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
