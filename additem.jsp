<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <div >
        <a href="homepage.jsp">Home</a>
        <a href="myprofile.jsp">My Profile</a>
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
    <h1>Hi <s:property value="#session.currentUser.username" />, you are currently logged in!</h1>
<section>
<!-- Row 1 -->
    <div class="content-row">
        <div class="addItem-input">
        <s:form action="addItemForSale" method="POST" id="addItem">
        <h2>Add Item For Sale</h2>
            <label for="itemName">Enter Item Name:</label>
            <input type="text" id="itemName" name = "itemName" class="textbox" placeholder="Item Name" required><br>
            <label for="price">Enter Price Amount:</label>
            <input type="text" id="price" id="price" class="textbox" placeholder="Price" required><br>
            <input type="submit"class ="btn">
            </s:form>
        </div>
</div>

<section>
</div>
     </body>
     <!--Forms -->
<s:form action="viewAllUsers" method="POST" id="allUsers"></s:form>


 </html>
 <script>
        window.onload = function () {
            var errMsg = document.getElementById('errMsgDiv');
            if ('<s:property value="errorMessage" />' == null || '<s:property value="errorMessage" />' === '' ) {
                errMsg.style.visibility = 'hidden';
            } else {
                errMsg.style.visibility = 'visible';
                errMsg.classList.add('errMsgBox');
                errMsg.innerHTML = '<s:property value="errorMessage" />'
            }        
        };

</script>

