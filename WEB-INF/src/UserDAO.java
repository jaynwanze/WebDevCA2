import java.util.ArrayList;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection; // Database connection

    private String errorMessage;
    private ArrayList<String> users;
    private ArrayList<Item> items;
    private ArrayList<Bid> bids;

    public UserDAO(Connection connection) {
        // Connection to database
        this.connection = connection;

    }

    public boolean validateBlankInputReg(String username, String password, String passwordConf, String email,
            String firstName, String lastName) {
        // if user inputs is blank or has whitespaces
        if (!(username.isBlank()) && !(username.matches(".*\\s.*")) && !(password.isBlank())
                && !(password.matches(".*\\s.*")) && !(passwordConf.isBlank())
                && !(passwordConf.matches(".*\\s.*")) && !(email.isBlank()) && !(email.matches(".*\\s.*"))
                && !(firstName.isBlank())
                && !(firstName.matches(".*\\s.*")) && !(lastName.isBlank())
                && !(lastName.matches(".*\\s.*"))) {
            return true;
        }
        return false;
    }

    public boolean validateLettersOnly(String firstName, String lastName) {
        // if user inputs is letters
        if (firstName.matches("[a-zA-Z]+\\.?") && lastName.matches("[a-zA-Z]+\\.?")) {
            return true;
        }
        return false;
    }

    public boolean validateBlankInputLogin(String username, String password) {
        // if user inputs is blank or has whitespaces
        if (!(username.isBlank()) && !(username.matches(".*\\s.*")) && !(password.isBlank())
                && !(password.matches(".*\\s.*"))) {
            return true;
        }
        return false;
    }

    public String validatePasswords(String password, String passwordConf) {
        String result = "Error"; // default

        if (password.length() >= 8 && password.length() <= 20 && password.equals(passwordConf)) {
            return result = "";// if password meets requirements
        }
        // Check if password or password confirmation is correct length
        else if (password.length() < 8 || password.length() > 20) {
            // return "IncorrectLength" if password or password confirmation isnt correct
            // length
            return result = "IncorrectLength";
        } else if (!(password.equals(passwordConf))) {
            // Return "PasswordsDontMatch" if password or password confirmation dont match
            return result = "PasswordsDontMatch";
        }
        return result;
    }

    public boolean validatePassword(String password) {
        // Check if password or password confirmation is correct length
        if (password.length() >= 8 && password.length() <= 20) {
            return true;
        }
        return false;

    }

    public boolean checkUsernameExists(String username) {
        PreparedStatement checkIfUsernameIsTaken = null;
        ResultSet rs = null;
        try {
            checkIfUsernameIsTaken = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            checkIfUsernameIsTaken.setString(1, username);
            rs = checkIfUsernameIsTaken.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            if (rs.next()) {
                rs.close();
                checkIfUsernameIsTaken.close();
                return true; // Username already exists

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (checkIfUsernameIsTaken != null)
                    checkIfUsernameIsTaken.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // Username is not taken
    }

    public boolean checkPasswordMatches(String username, String password) {
        PreparedStatement checkUserLoginDetails = null;
        ResultSet rs = null;
        try {
            // Prepared statement to check to if user and password match
            checkUserLoginDetails = connection
                    .prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            checkUserLoginDetails.setString(1, username);
            checkUserLoginDetails.setString(2, password);
            // Executes sql query tocheck if username already exists /stores results in rs
            rs = checkUserLoginDetails
                    .executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            // Checks if resultset is returned
            if (rs.next()) {
                rs.close();
                checkUserLoginDetails.close();
                return true;// User Exists
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (checkUserLoginDetails != null)
                    checkUserLoginDetails.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean addUserToDB(String username, String email, String firstName, String lastName, String password) {
        PreparedStatement createUser = null;
        try {
            // Execute query to create a new user within
            // database
            createUser = connection.prepareStatement(
                    "INSERT into users "
                            + "(username, email, first_name, last_name, password)" + " VALUES (?, ?, ?, ?, ?)");
            // Pass in the values as paramaters into sql statement
            createUser.setString(1, username);
            createUser.setString(2, email);
            createUser.setString(3, firstName);
            createUser.setString(4, lastName);
            createUser.setString(5, password);

            int rowsUpdated = createUser.executeUpdate();
            if (rowsUpdated > 0) {
                createUser.close();
                return true;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            try {

                if (createUser != null)
                    createUser.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public User getUserProfile(String usernameString) {
        User user;
        PreparedStatement getUserProfile = null;
        ResultSet rs = null;
        String username = null;
        PreparedStatement checkUsername = null;

        try {
            // Check if the given usernameString exists in the users table
            checkUsername = connection.prepareStatement("SELECT username FROM users");
            rs = checkUsername.executeQuery();
            while (rs.next()) {
                String dbUsername = rs.getString("username");
                if (usernameString.contains(dbUsername)) {
                    username = dbUsername;
                    break;
                }
            }
            rs.close();
            checkUsername.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            if (username != null) {
                // Prepared statement to check to if user and password match
                getUserProfile = connection
                        .prepareStatement(
                                "SELECT username, email, first_name, last_name, created_at FROM users WHERE username = ?");
                getUserProfile.setString(1, username);

                // Executes sql query tocheck if username already exists /stores results in rs
                rs = getUserProfile
                        .executeQuery();
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            if (rs.next()) {
                String email = rs.getString("email");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String dateJoined = rs.getTimestamp("created_at").toString();
                user = new User(username, email, firstName, lastName, dateJoined);
                return user;
            }
            try {
                rs.close();
                getUserProfile.close();
                checkUsername.close();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (getUserProfile != null)
                    getUserProfile.close();
                if (checkUsername != null) {
                    checkUsername.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public ArrayList<String> getUsers() {
        users = new ArrayList<String>();
        PreparedStatement getUsersFromDB = null;
        ResultSet rs = null;
        try {
            getUsersFromDB = connection.prepareStatement("SELECT username FROM users");
            rs = getUsersFromDB.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            while (rs.next()) {
                String username = rs.getString("username");
                users.add(username);

            }
            try {
                rs.close();
                getUsersFromDB.close();
                return users;

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (getUsersFromDB != null)
                    getUsersFromDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public ArrayList<Item> getItems() {
        items = new ArrayList<Item>();
        PreparedStatement getItemsFromDB = null;
        ResultSet rs = null;

        try {
            getItemsFromDB = connection.prepareStatement(
                    "SELECT i.item_name, i.price, u.username, i.created_at FROM items i JOIN users u ON seller_id = user_id");
            rs = getItemsFromDB.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        try {
            while (rs.next()) {
                String itemName = rs.getString("item_name");
                String itemPrice = String.valueOf(rs.getDouble("price"));
                String username = rs.getString("username");
                String datePosted = rs.getString("created_at");
                Item item = new Item(itemName, itemPrice, username, datePosted);
                items.add(item);
            }

            try {
                rs.close();
                getItemsFromDB.close();
                return items;

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (getItemsFromDB != null)
                    getItemsFromDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public boolean addItemToDB(String username, String itemName, double itemPrice) {

        PreparedStatement getUserId = null;
        PreparedStatement addItem = null;
        ResultSet rs = null;

        try {
            getUserId = connection.prepareStatement("SELECT user_id FROM users WHERE username = ?");
            getUserId.setString(1, username);
            rs = getUserId.executeQuery();
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }
        try {
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                try {
                    addItem = connection
                            .prepareStatement("INSERT INTO items (seller_id, item_name, price) VALUES (?, ?, ?)");
                    addItem.setInt(1, userId);
                    addItem.setString(2, itemName);
                    addItem.setDouble(3, itemPrice);

                    int rowsUpdated = addItem.executeUpdate();
                    if (rowsUpdated > 0) {
                        rs.close();
                        getUserId.close();
                        addItem.close();
                        return true;
                    }

                } catch (SQLException e) {
                    // Handle SQL exception
                    e.printStackTrace();
                }
            }
            rs.close();
            getUserId.close();
            addItem.close();
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }

        return false;

    }

    public ArrayList<Bid> getItemsBids(String itemName) {
        PreparedStatement getItemId = null;
        PreparedStatement getItemsBids = null;
        ResultSet rs = null;
        bids = new ArrayList<Bid>();

        try {
            getItemId = connection.prepareStatement("SELECT item_id FROM items WHERE item_name = ?");
            getItemId.setString(1, itemName);
            rs = getItemId.executeQuery();
            try {
                if (rs.next()) {
                    int itemId = rs.getInt("item_id");

                    try {
                        getItemsBids = connection
                                .prepareStatement(
                                        "SELECT b.bid_amount, u.username, b.created_at FROM bids b JOIN users u ON u.user_id = b.bidder_id JOIN items i ON b.item_id = i.item_id WHERE b.item_id = ?;");

                        getItemsBids.setInt(1, itemId);

                        rs = getItemsBids.executeQuery();
                        if (rs != null) {
                            while (rs.next()) {
                                String bidPrice = String.valueOf(rs.getDouble("bid_amount"));
                                String bidder = rs.getString("username");
                                String datePosted = rs.getString("created_at");
                                Bid bid = new Bid(bidPrice, bidder, datePosted, itemName);
                                bids.add(bid);
                            }

                        }

                    } catch (SQLException e) {
                        // Handle SQL exception
                        e.printStackTrace();
                    }

                }
                try {
                    rs.close();
                    getItemId.close();
                    getItemsBids.close();
                    return bids;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } catch (SQLException e) {
                // Handle SQL exception
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }

        return null;

    }

    public Double getHighestBid(String itemName) {
        PreparedStatement getItemId = null;
        PreparedStatement getHighestBid = null;
        ResultSet rs = null;

        try {
            getItemId = connection.prepareStatement("SELECT item_id FROM items WHERE item_name = ?");
            getItemId.setString(1, itemName);
            rs = getItemId.executeQuery();
            try {
                if (rs.next()) {
                    int itemId = rs.getInt("item_id");

                    try {
                        getHighestBid = connection
                                .prepareStatement(
                                        "SELECT b.bid_amount FROM bids b JOIN users u ON b.bidder_id = u.user_id WHERE b.item_id = ? ORDER BY b.bid_amount DESC LIMIT 1;");

                        getHighestBid.setInt(1, itemId);

                        rs = getHighestBid.executeQuery();
                        if (rs.next()) {
                            Double bidPrice = (rs.getDouble("bid_amount"));
                            return bidPrice;
                        }

                    } catch (SQLException e) {
                        // Handle SQL exception
                        e.printStackTrace();
                    }

                }
                try {
                    rs.close();
                    getItemId.close();
                    getHighestBid.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } catch (SQLException e) {
                // Handle SQL exception
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }

        return null;

    }

    public ArrayList<Bid> getUserBids(String username) {
        PreparedStatement getUserId = null;
        PreparedStatement getItemsBids = null;
        ResultSet rs = null;
        bids = new ArrayList<Bid>();

        try {
            getUserId = connection.prepareStatement("SELECT user_id FROM users WHERE username = ?");
            getUserId.setString(1, username);
            rs = getUserId.executeQuery();
            try {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");

                    try {
                        getItemsBids = connection
                                .prepareStatement(
                                        "SELECT i.item_name, b.bid_amount, u.username, b.created_at FROM bids b JOIN items i ON b.item_id = i.item_id JOIN users u ON b.bidder_id = u.user_id WHERE b.bidder_id = ?;");

                        getItemsBids.setInt(1, userId);

                        rs = getItemsBids.executeQuery();
                        if (rs != null) {
                            while (rs.next()) {
                                String itemName = rs.getString("item_name");
                                String bidPrice = String.valueOf(rs.getDouble("bid_amount"));
                                String bidder = rs.getString("username");
                                String datePosted = rs.getString("created_at");
                                Bid bid = new Bid(bidPrice, bidder, datePosted, itemName);
                                bids.add(bid);
                            }

                        }

                    } catch (SQLException e) {
                        // Handle SQL exception
                        e.printStackTrace();
                    }

                }
                try {
                    rs.close();
                    getUserId.close();
                    getItemsBids.close();
                    return bids;
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } catch (SQLException e) {
                // Handle SQL exception
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }

        return null;

    }

    public boolean addBidToDB(String username, String itemName, double bidPrice) {

        PreparedStatement getUserAndItemId = null;
        PreparedStatement addBid = null;
        ResultSet rs = null;

        try {
            getUserAndItemId = connection
                    .prepareStatement("SELECT user_id, item_id FROM users, items WHERE username = ? AND item_name = ?");
            getUserAndItemId.setString(1, username);
            getUserAndItemId.setString(2, itemName);

            rs = getUserAndItemId.executeQuery();
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }
        try {
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                int itemId = rs.getInt("item_id");

                try {
                    // use userId to insert a new item
                    addBid = connection
                            .prepareStatement("INSERT INTO bids (bidder_id, item_id, bid_amount) VALUES (?, ?, ?)");
                    // Assuming item details are provided as parameters
                    addBid.setInt(1, userId);
                    addBid.setInt(2, itemId);
                    addBid.setDouble(3, bidPrice);

                    // Execute the insert statement
                    int rowsUpdated = addBid.executeUpdate();
                    if (rowsUpdated > 0) {
                        rs.close();
                        getUserAndItemId.close();
                        addBid.close();
                        return true;
                    }

                } catch (SQLException e) {
                    // Handle SQL exception
                    e.printStackTrace();
                }
            }
            rs.close();
            getUserAndItemId.close();
            addBid.close();
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        }

        return false;

    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
