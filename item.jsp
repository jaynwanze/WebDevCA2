<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <%@taglib uri="/struts-tags" prefix="s" %>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" type="text/css" href="css/style1.css">
            <script src="scripts/script.js"></script>
            <title>E-Commerce Website</title>
        </head>
        <header>
            <h1>eCommerce Website</h1>
        </header>

        <body>
            <nav>
                <div>
                    <a href="homepage.jsp">Home</a>
                    <a href="myprofile.jsp">My Profile</a>
                    <a onclick="viewAllItemsForm()">View Items</a>
                    <a href="additem.jsp">Add Item</a>
                    <a onclick="viewAllUsersForm()">View All Users</a>
                </div>
                <div>
                    <s:form id="logout" action="logout" method="POST">
                        <button class="btn" onclick="logout()">Logout</button>
                    </s:form>
                </div>
            </nav>


            <div class="container">
                <h1>Hi
                    <s:property value="#session.currentUser.username" />, you are currently logged in | Item/Item Bids Page
                </h1>
            </div>
            <section class="homepage-content">
                <div class="content-row">
                    <div class="stock-item-container">
                        <div class="view-item">
                            <label>Item Name:</label>
                            <s:property value="itemName" />
                            <label>Item Price:</label>
                            <s:property value="itemPrice" />
                            <label>Seller:</label>
                            <s:property value="username" />
                            <label>Date Posted:</label>
                            <s:property value="datePosted" />
                            </form>
                        </div>
                    </div>

                    <div class="content-display-profile ">
                        <table>
                            <thead>
                                <tr>
                                    <th>Price</th>
                                    <th>Bidder</th>
                                    <th>Date Posted</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="bids">
                                    <tr>

                                        <td>
                                            <s:property value="bidPrice" />
                                        </td>
                                        <td>
                                            <s:property value="bidder" />
                                        </td>
                                        <td>
                                            <s:property value="datePosted" />
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                        <div id="errMsgDiv"> </div>
                    </div>
                </div>
                <div id="bidErrMsgDiv"> </div>
                <div id="successMsgDiv"> </div>

                    <div class="additem-form">
                        <s:form action="makeBid" method="POST" id="makeBid">
                            <h2>Make Bid On Item</h2>
                            <label class="labelMakeBid" for="bidItemPrice">Enter Bid Price Amount:</label>
                            <input type="hidden" name="username" value='<s:property value="username" />' />
                            <input type="hidden" name="itemName" value='<s:property value="itemName" />' />
                            <input type="hidden" name="itemPrice" value='<s:property value="itemPrice" />' />
                            <input type="hidden" name="datePosted" value='<s:property value="datePosted" />' />
                            <input class="inputLogin" type="text" id="bidItemPrice" name="bidItemPrice" class="textbox"
                                placeholder="Bid price" required><br>
                            <input type="submit" class="btn">
                        </s:form>
                    </div>
                <!--MAKE BID -->
                <!--CAN MAKE BID -->
                <!--IF SESSION CUREENT USER IS EQUAL TO USERNAME = THE CANT MAKE BID pass current username and session 
                    and prcie cant be less than price or highest bid-->
                <section>

        </body>
        <!--Forms -->
        <s:form action="viewAllUsers" method="POST" id="allUsers"></s:form>
        <s:form action="viewAllItemsForSale" method="POST" id="viewItems"></s:form>




        </html>
        <script>
            window.onload = function () {
                var errMsg = document.getElementById('errMsgDiv');
                if ('<s:property value="errorMessage" />' == null || '<s:property value="errorMessage" />' === '') {
                    errMsg.style.visibility = 'hidden';
                } else {
                    errMsg.style.visibility = 'visible';
                    errMsg.classList.add('errMsgBox');
                    errMsg.innerHTML = '<s:property value="errorMessage" />'
                }
                var bidErrMsg = document.getElementById('bidErrMsgDiv');
                if ('<s:property value="bidErrorMessage" />' == null || '<s:property value="bidErrorMessage" />' === '') {
                    bidErrMsg.style.visibility = 'hidden';
                } else {
                    bidErrMsg.style.visibility = 'visible';
                    bidErrMsg.classList.add('errMsgBox');
                    bidErrMsg.innerHTML = '<s:property value="bidErrorMessage" />'
                }
                var successMsg = document.getElementById('successMsgDiv');
                if ('<s:property value="successMessage" />' == null || '<s:property value="successMessage" />' === '') {
                    successMsg.style.visibility = 'hidden';
                } else {
                    successMsg.style.visibility = 'visible';
                    successMsg.classList.add('successMsgBox');
                    successMsg.innerHTML = '<s:property value="successMessage" />';
                }

                setTimeout(function () {
                    bidErrMsg.style.visibility = 'hidden';
                    successMsg.style.visibility = 'hidden';
                }, 10000);
            };

        </script>