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
                    <s:property value="#session.currentUser.username" />, you are currently logged in | Add Item Page
                </h1>
                <section>
                    <div class="content-row">
                        <div class="additem-form">
                            <s:form action="addItemForSale" method="POST" id="addItem">
                                <h2>Add Item For Sale</h2>
                                <!-- <input type = "hidden" value = '<s:property value="#session.currentUser.username" />' name = "username"> -->
                                <label class="labelLogin" for="itemName">Enter Item Name:</label>
                                <input class="inputLogin" type="text" id="itemName" name="itemName" class="textbox"
                                    placeholder="Item Name" required><br>
                                <label class="labelLogin" for="itemPrice">Enter Price Amount:</label>
                                <input class="inputLogin" type="text" id="itemPrice" name="itemPrice" class="textbox"
                                    placeholder="Item price" required><br>
                                <input type="submit" class="btn">
                            </s:form>
                        </div>
                    </div>

                    <section>
                        <div id="errMsgDiv"> </div>
                        <div id="successMsgDiv"> </div>
            </div>
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
                // Logout Message
                var successMsg = document.getElementById('successMsgDiv');
                if ('<s:property value="successMessage" />' == null || '<s:property value="successMessage" />' === '') {
                    successMsg.style.visibility = 'hidden';
                } else {
                    successMsg.style.visibility = 'visible';
                    successMsg.classList.add('successMsgBox');
                    successMsg.innerHTML = '<s:property value="successMessage" />';
                    // Set a timeout to hide the message after 10 seconds
                    setTimeout(function () {
                        errMsg.style.visibility = 'hidden';
                        successMsg.style.visibility = 'hidden';
                    }, 10000); 
                };
            };

        </script>