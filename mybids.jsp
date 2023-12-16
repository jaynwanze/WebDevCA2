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
                    <s:property value="#session.currentUser.username" />, you are currently logged in! | My Bids Page
                </h1>
                <h2>My Bids:
                </h2>
            </div>

            <section class="homepage-content">
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
                        <s:iterator value="bids">
                            <tr>
                                <td>
                                    <s:property value="itemName" />
                                </td>
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
            </section>

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
            };

        </script>