function showPasswordToggle() {
    var pass = document.getElementById("user_password");
    var passConf = document.getElementById("user_password_conf");

    if (pass.type === "password" && passConf.type === "password") {
        pass.type = "text";
        passConf.type = "text";
    } else {
        pass.type = "password";
        passConf.type = "password";
    }
}

function setPasswordType() {
    var pass = document.getElementById("user_password");
    var passConf = document.getElementById("user_password_conf");

    if (pass.type != "password" && passConf.type != "password") {
        pass.type = "password";
        passConf.type = "password";
    }
}

function showPasswordToggleLogin() {
    var pass = document.getElementById("user_password");

    if (pass.type === "password") {
        pass.type = "text";
    } else {
        pass.type = "password";
    }
}

function setPasswordTypeLogin() {
    var pass = document.getElementById("user_password");

    if (pass.type != "password") {
        pass.type = "password";
    }
}
function logoutForm() {
    document.getElementById("logout").submit();
}
function viewAllUsersForm() {
    document.getElementById("allUsers").submit();
}
function addItemForSaleForm() {
    document.getElementById("addItem").submit();
}

function viewProfileForm(username) {
    
    // Set the value of the hidden input field
    document.getElementById('usernameInput').value = username;
    document.getElementById('viewProfile').submit();
}

function viewAllItemsForm() {
    document.getElementById("viewItems").submit();
}



