window.onload = function() {
    $("#back-btn").click(function (e) {
        window.location.href = "../index.html";
    });

    load_Layer1('.layer-style-1');
    load_Layer2('.layer-style-2');

    /** test Function */
    $("#test-layer").click(function (e) { 
        top.layer.msg("hello", 3);
    });
}

/********************************
 * Layer 1
 */
 function load_Layer1(comJqSelector) {
    // init layer form
    init_Layer1(comJqSelector);

    // bind event
    bindEvent_Layer1(comJqSelector);
}

function init_Layer1(comJqSelector) {
    //  url params to load the log info last time
    var loginMessage = CommonUtil.getUrlParams("logMSG");

    if (loginMessage != "") {
        $('' + comJqSelector + " form .log-error-msg").css("display", "inline-block")
        $('' + comJqSelector + " form .log-error-msg").html(loginMessage);
    }
}
function bindEvent_Layer1(comJqSelector) {
    $("" + comJqSelector + " form").submit(function (e) {
        var checkMessage = loginCheck_Layer1(comJqSelector);
        if (checkMessage == "Success") {
            CommonUtil.processLoading(login_Layer1(comJqSelector));
            return true;
        }
        else {
            $('' + comJqSelector + " form .log-error-msg").css("display", "inline-block")
            $('' + comJqSelector + " form .log-error-msg").html(checkMessage);
            return false;
        }
    });
}
/**
 * Login Logic
 */
// Before Login, check the input
function loginCheck_Layer1(comJqSelector) {
    var userNameCheck = usernameCheck_Layer1(comJqSelector);
    var passwordCheck = passwordCheck_Layer1(comJqSelector);

    if (userNameCheck == "Success" && passwordCheck == "Success") {
        return "Success";
    }
    else {
        if (userNameCheck == "Success") {
            return passwordCheck;
        }
        else {
            return userNameCheck;
        }
    }
}
// Layer1 username check
function usernameCheck_Layer1(comJqSelector) {
    var userName = $('' + comJqSelector + ' form .log-username').val();
    if (!FormUtil.usernameCheck_notEmpty(userName)) {
        return "Error : Please input the username";
    }

    return "Success"
}
// Layer1 password check
function passwordCheck_Layer1(comJqSelector) {
    var passWd = $('' + comJqSelector + ' form .log-password').val();
    if (!FormUtil.passwdCheck_lengthLimit(passWd, 6)) {
        return "Error : Passwords need to more than 6";
    }
    if (!FormUtil.passwdCheck_illegalCharacter(passWd, ['*', "_"])) {
        return "Error : Passwords contain illegal character";
    }

    return "Success";
}
// Layer1 login
function login_Layer1(comJqSelector) {
    // var reqData = {

    // };

    // $.ajax({
    //     type: "post",
    //     url: "...",
    //     data: JSON.stringify(reqData),
    //     dataType: "json",
    //     contentType: "application/json;charset=utf-8;",
    //     success: function (response) {
    //         // Login Call Back Function
    //         logCallBack_Layer1(response);
    //     }
    // });
}
// Layer1 LogCallBack
function logCallBack_Layer1(response) {
    
}



/********************************
 * Layer 2
 */
 function load_Layer2(comJqSelector) {
    // init 
    init_Layer2(comJqSelector);

    // event bind
    bindEvent_Layer2(comJqSelector);
}
function init_Layer2(comJqSelector) {
    // logMSG
    let loginMessage = CommonUtil.getUrlParams("logMSG");

    if (loginMessage != '') {
        top.layer.msg(loginMessage + '');
    }
}
function bindEvent_Layer2(comJqSelector) {
    $("" + comJqSelector + " form").submit(function (e) { 
        let checkMessage = loginCheck_Layer2(comJqSelector);
        if (checkMessage == "Success") {
            CommonUtil.processLoading(login_Layer2(comJqSelector));
            return true;
        }
        else {
            top.layer.msg(checkMessage + '');
            return false;
        }
    });
}
/**
 * Login Logic
 */
// Before Login, check the input
function loginCheck_Layer2(comJqSelector) {
    var userNameCheck = usernameCheck_Layer2(comJqSelector);
    var passwordCheck = passwordCheck_Layer2(comJqSelector);

    if (userNameCheck == "Success" && passwordCheck == "Success") {
        return "Success";
    }
    else {
        if (userNameCheck == "Success") {
            return passwordCheck;
        }
        else {
            return userNameCheck;
        }
    }
}
// Layer1 username check
function usernameCheck_Layer2(comJqSelector) {
    var userName = $('' + comJqSelector + ' form .log-username').val();
    if (!FormUtil.usernameCheck_notEmpty(userName)) {
        return "Error : Please input the username";
    }

    return "Success"
}
// Layer2 password check
function passwordCheck_Layer2(comJqSelector) {
    var passWd = $('' + comJqSelector + ' form .log-password').val();
    if (!FormUtil.passwdCheck_lengthLimit(passWd, 6)) {
        return "Error : Passwords need to more than 6";
    }
    if (!FormUtil.passwdCheck_illegalCharacter(passWd, ['*', "_"])) {
        return "Error : Passwords contain illegal character";
    }

    return "Success";
}
// Layer2 login
function login_Layer1(comJqSelector) {
    // var reqData = {

    // };

    // $.ajax({
    //     type: "post",
    //     url: "...",
    //     data: JSON.stringify(reqData),
    //     dataType: "json",
    //     contentType: "application/json;charset=utf-8;",
    //     success: function (response) {
    //         // Login Call Back Function
    //         logCallBack_Layer2(response);
    //     }
    // });
}
// Layer2 LogCallBack
function logCallBack_Layer2(response) {
    
}