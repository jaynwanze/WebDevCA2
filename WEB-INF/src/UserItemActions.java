import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class UserItemActions extends ActionSupport implements SessionAware {

    private ArrayList<Item> items = new ArrayList<Item>();
    private User user = new User();
    private Item item = new Item();
    private Map<String, Object> session;
    private UserDAO userDAO;
    private String errorMessage;
    private String successMessage;
    private String itemName;
    private String itemPrice;

    public UserItemActions() {

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

    public String addItemForSale() {
        setErrorMessage(null);
        setSuccessMessage(null);
        double itemPriceDb = Double.parseDouble(itemPrice);
        String username;
        String result = "FAILURE";
        Connection connection = getConnection();
        // Create new userDAO instance
        userDAO = new UserDAO(connection);
        user = (User) session.get("currentUser");
        username = user.getUsername();

        // add validation for double input

        if (userDAO.addItemToDB(username, itemName, itemPriceDb)) {
            setSuccessMessage("Operation Successful: Item Was Added For Sale!");
            return result = "SUCCESS";
        } else {
            setErrorMessage("Error Processing Request: Unable To Add Item For Sale");
        }
        return result;
    }

    public String viewItemsForSale() {
        setErrorMessage(null);
        setSuccessMessage(null);

        String result = "FAILURE";
        Connection connection = getConnection();
        // Create new userDAO instance
        userDAO = new UserDAO(connection);
        
        // Set users list to be called in jsp
        if (userDAO.getItems() != null) {
            setItems(userDAO.getItems());
            return result = "SUCCESS";
        } else {
            setErrorMessage("Error Processing Request: Items List Is Empty");
        }
        return result;


    }

    public String viewItemForSale() {
        setErrorMessage(null);
        setSuccessMessage(null);
        return "";

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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Item getItem() {
        return item;
    }

    public void setItems(Item item) {
        this.item = item;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
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
