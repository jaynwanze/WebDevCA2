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
                    <a href="additems.jsp">Add Item</a>
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
                    <s:property value="#session.currentUser.username" />, you are currently logged in!
                </h1>
                <h2>User Profile:
                    <s:property value="user.username" />
                </h2>
                <div class="content-row">
                    <div class="content-display">
                        <table class="table-profile">
                            <tr>
                                <th>Username</th>
                            </tr>
                            <tr>
                                <td>
                                    <s:property value="user.username" /><br>
                                </td>
                            </tr>
                            <tr>
                                <th>Email</th>
                            </tr>
                            <tr>
                                <td>
                                    <s:property value="user.email" /><br>
                                </td>
                            </tr>
                            <tr>
                                <th>First Name</th>
                            </tr>
                            <tr>
                                <td>
                                    <s:property value="user.firstName" /><br>
                                </td>
                            </tr>
                            <tr>
                                <th>Last Name</th>
                            </tr>
                            <tr>
                                <td>
                                    <s:property value="user.lastName" /><br>
                                </td>
                            </tr>
                            <tr>
                                <th>Date/Time Joined</th>
                            </tr>
                            <tr>
                                <td>
                                    <s:property value="user.dateJoined" /><br>
                                </td>
                            </tr>
                        </table>

                    </div>
                </div>
            </div>

            <section>
                <div id="errMsgDiv"> </div>
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
            };
        </script>