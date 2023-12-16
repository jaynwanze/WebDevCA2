import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class UserBidActions extends ActionSupport implements SessionAware {

    private ArrayList<Bid> bids = new ArrayList<Bid>();
    private UserDAO userDAO;
    private User user = new User();
    private Map<String, Object> session;
    private String errorMessage;
    private String bidErrorMessage;
    private String successMessage;
    private String username;
    private String itemName;
    private String itemPrice;
    private String datePosted;
    private String bidItemPrice;

    public UserBidActions() {

    }

    public Connection getConnection() {
        Connection connection = null;
        // Create connection to database
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC", "root", "root");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public String makeBid() {
        setErrorMessage(null);
        setSuccessMessage(null);
        setBidErrorMessage(null);

        double bidItemPriceDb = Double.parseDouble(bidItemPrice);
        double doubleItemPrice = Double.parseDouble(itemPrice);

        String result = "FAILURE";
        Connection connection = getConnection();
        // Create new userDAO instance
        userDAO = new UserDAO(connection);
        user = (User) session.get("currentUser");
        String currentUser = user.getUsername();

        // add validation for double input
        
        //add validation for not bidding lower than highest bidder
        if (username.equals(currentUser)) {
            setBidErrorMessage("Error Processing Request: User Cannot Bid On Own Item!");
        } else if (bidItemPriceDb < doubleItemPrice) {
            setBidErrorMessage("Error Processing Request: Cannot Make Lower Than Starting Price!");
        } else if (userDAO.addBidToDB(currentUser, itemName, bidItemPriceDb)) {
            setSuccessMessage("Operation Successful: Bid Was Sucessfully Made For This Item!");
            result = "SUCCESS";
        } else {
            setBidErrorMessage("Error Processing Request: Unable To Make Bid On This Item");
        }

        if (!(userDAO.getItemsBids(itemName).isEmpty())) {
            setBids(userDAO.getItemsBids(itemName));

        } else {
            setErrorMessage("There Are No Bids On This Item");

        }

        return result;

    }

    public String viewMyBids() {
        setErrorMessage(null);
        setBidErrorMessage(null);
        setSuccessMessage(null);

        String result = "FAILURE";
        Connection connection = getConnection();
        // Create new userDAO instance
        userDAO = new UserDAO(connection);
        user = (User) session.get("currentUser");
        String currentUser = user.getUsername();

        // add validation for double input
        if (!(userDAO.getUserBids(currentUser)).isEmpty()) {
            bids = userDAO.getUserBids(currentUser);
            setBids(bids);
            return result = "SUCCESS";
        } else if ((userDAO.getUserBids(currentUser)).isEmpty()) {
            setErrorMessage("There Are No Bids On This Item");
            return result = "SUCCESS";
        }
        setErrorMessage("Error Processing Request: Unable To Complete Operation!");
        return result;

    }

    public String getBidItemPrice() {
        return bidItemPrice;
    }

    public void setBidItemPrice(String bidItemPrice) {
        this.bidItemPrice = bidItemPrice;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public ArrayList<Bid> getBids() {
        return bids;
    }

    public void setBids(ArrayList<Bid> bids) {
        this.bids = bids;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setBidErrorMessage(String bidErrorMessage) {
        this.bidErrorMessage = bidErrorMessage;
    }

    public String getBidErrorMessage() {
        return bidErrorMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    @Override
    public void setSession(Map map) {
        session = map;

    }

    public Map<String, Object> getSession() {
        return session;
    }

}
