<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style1.css">
    <title>eCommerce Website</title>
</head>

<body>

    <header>
        <h1>eCommerce Website</h1>
    </header>

    <nav class= "nav1">
        <div>
        <a href="#">Home</a>
        <a href="#">My Profile</a>
        <a href="#">View Items</a> <!-- Click into item to see all bids/ Make a bid goes in here -->
        <a href="#">Add Item</a>
        </div>
            <div>
          <s:form action="logout" method="POST">
              <s:submit value= "Logout" />
          </s:form>
          </div>
    </nav>
    
    <div class="container">
     <h1>Hi <s:property value="#session.currentUser" />, you are currently logged in!</h1>
     </div>
  <!-- Homepage Content Section -->
<section class="homepage-content">
    <!-- Row 1 -->
    <div class="content-row">
     <!-- Column 3: Placeholder -->
        <div class="content-column">
            <h2>View My bids</h2>
            <!-- Add content here if needed, or leave it empty -->
        </div>
        <!-- Column 1: View All Bids on an Item -->
        <div class="content-column">
            <h2>View All Bids on an Item</h2>
            <!-- Add content related to viewing bids here -->
        </div>
        <!-- Column 3: View All Items for Sale -->
        <div class="content-column">
            <h2>View All Items for Sale</h2>
            <!-- Add content related to viewing items for sale here -->
        </div>
    </div>

    <!-- Row 2 -->
    <div class="content-row">
        <!-- Column 1: View My Profile -->
        <div class="content-column">
            <h2>View My Profile</h2>
            <!-- Add content related to viewing user profile here -->
        </div>
        <!-- Column 2: View All Users -->
        <div class="content-column">
            <h2>View All Users</h2>
            <!-- Add content related to viewing all users here -->
        </div>
        <!-- Column 2: Add an Item For Sale -->
        <div class="content-column">
            <h2>Add an Item For Sale</h2>
            <!-- Add content related to adding an item for sale here -->
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
 <script>
     window.onload = function() {
            var errMsg = document.getElementById('errMsgDiv');
            if ('<s:property value="errorMessage" />' == null || '<s:property value="errorMessage" />' === '') {
                errMsg.style.visibility = 'hidden';
            } else {
                errMsg.style.visibility = 'visible';
                errMsg.classList.add('errMsgBox');
                errMsg.innerHTML = '<s:property value="errorMessage" />'
            }
             // Set a timeout to hide the message after 10 seconds
        setTimeout(function() {
            errMsg.style.visibility = 'hidden';
        }, 10000); // 10000 milliseconds = 10 seconds
        };
  </script>
</html>
