<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib uri="/struts-tags" prefix="s" %>
        <!DOCTYPE html>

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" type="text/css" href="css/style1.css">
            <script src="scripts/script.js"></script>
            <title>eCommerce Website</title>
        </head>

        <body>

            <header>
                <h1>eCommerce Website</h1>
            </header>
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
                    <s:property value="#session.currentUser.username" />, you are currently logged in | Home Page
                </h1>
            </div>
            <!-- Homepage Content Section -->
            <section class="homepage-content">
                <div class="content-row">
                    <!-- Column 1: View All Bids -->
                    <div class="content-display-homepage-bids">
                        <div class="clickable" onclick="viewMyBidsForm()">
                            <h2>View My bids</h2>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Item Name</th>
                                    <th>Price</th>
                                    <th>Bidder</th>
                                    <th>Date Posted</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="#session.currentUserBids" id="currentBid">
                                    <tr>
                                        <td>
                                            <s:property value="#currentBid.itemName" />
                                        </td>
                                        <td>
                                            <s:property value="#currentBid.bidPrice" />
                                        </td>
                                        <td>
                                            <s:property value="#currentBid.bidder" />
                                        </td>
                                        <td>
                                            <s:property value="#currentBid.datePosted" />
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                        <div id="noBidsErrMsgDiv"> </div>
                    </div>
                    <!-- Column3: View All Items for Sale -->
                    <div class="content-display-homepage">
                        <div class="clickable" onclick="viewAllItemsForm()">
                            <h2>View All Items for Sale</h2>
                        </div>
                        <div class="stock-items-container-homepage ">
                            <s:iterator value="#session.currentItemsForSale" id="currentItem">
                                <s:form action="viewItemAndBids" method="POST" id="itemAndBids">
                                    <div class="stock-item">
                                        <label>Item Name:</label>
                                        <s:property value="#currentItem.itemName" />
                                        <input type="hidden" name="itemName" value='<s:property value="#currentItem.itemName" />' />
                                        <label>Item Price:</label>
                                        <s:property value="#currentItem.itemPrice" />
                                        <input type="hidden" name="itemPrice" value='<s:property value="#currentItem.itemPrice" />' />
                                        <label>Seller:</label>
                                        <s:property value="#currentItem.username" />
                                        <input type="hidden" name="username" value='<s:property value="#currentItem.username" />' />
                                        <label>Date Posted:</label>
                                        <s:property value="#currentItem.datePosted" />
                                        <input type="hidden" name="datePosted" value='<s:property value="#currentItem.datePosted" />' />
                                        <button type="submit" class="classButton" onclick="viewItemAndBidsForm()">View
                                            Item</button>
                                    </div>
                                </s:form>
                            </s:iterator>
                        </div>
                    </div>
                    <div class="content-display-myprofile">
                        <div class="clickable" onclick="window.location.href='myprofile.jsp';">
                            <h2>My Profile</h2>
                        </div>
                        <table class="table-profile">
                            <th> Username</th>
                            <tr>
                                <td>
                                    <s:property value="#session.currentUser.username" /><br>
                                </td>
                            </tr>
                            <th> Email</th>
                            <tr>
                                <td>
                                    <s:property value="#session.currentUser.email" /><br>
                                </td>
                            </tr>
                            <th> First Name</th>
                            <tr>
                                <td>
                                    <s:property value="#session.currentUser.firstName" /><br>
                                </td>
                            </tr>
                            <th>Last Name</th>
                            <tr>
                                <td>
                                    <s:property value="#session.currentUser.lastName" /><br>
                                </td>
                            </tr>
                            <th> Date/Time Joined</th>
                            <tr>
                                <td>
                                    <s:property value="#session.currentUser.dateJoined" /><br>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </section>
            <div class="container">
                <div id="errMsgDiv"> </div>
            </div>
            <footer>
                &copy; WebDevCA2 - Jamin Nwanze
            </footer>

        </body>

        <!--Forms -->
        <s:form action="viewAllUsers" method="POST" id="allUsers"></s:form>
        <s:form action="viewAllItemsForSale" method="POST" id="viewItems"></s:form>
        <s:form action="viewMyProfile" method="POST" id="myProfile"></s:form>
        <s:form action="viewMyBids" method="POST" id="myBids"></s:form>

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

                var noBidsErrMsg = document.getElementById('noBidsErrMsgDiv');
                if ('<s:property value="bids" />'.length == 0 ) {
                    noBidsErrMsg.style.visibility = 'visible';
                    noBidsErrMsg.classList.add('errMsgBox');
                    noBidsErrMsg.innerHTML = "You Currently Have No Bids";
                } else if('<s:property value="bids" />'.length > 0 ){
                    noBidsErrMsg.style.visibility = 'hidden';
                }

                if ('<s:property value="#session.currentUser" />' === 'undefined' || '<s:property value="#session.currentUser" />' === '') {
                    window.location.href = 'login.jsp';
                }
                
                setTimeout(function () {
                    errMsg.style.visibility = 'hidden';
                }, 10000); 
            };

        </script>

        </html>