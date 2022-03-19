window.onload = function() {
    let setting = {
        /**
         * return format
         * var rtn = {
                errorType : "-1",
                check : true
            }
            
            // success
            if (true) {
                return rtn;
            }
            // failure
            else {
                return rtn;
            }
         */
        logCheck : function(username, password) {
            var rtn = {};

            if (!FormUtil.notEmpty(username)) {
                rtn.errorType = "1";
                rtn.check = false;
            }
            else if (!FormUtil.containedCharacter(password, ['#'])) {
                rtn.errorType = "2";
                rtn.check = false;
            }
            else if (!FormUtil.txtLongerThan(password, 6)) {
                rtn.errorType = "0";
                rtn.check = false;
            }
            else {
                rtn.errorType = "-1";
                rtn.check = true;
            }

            return rtn;
        },
        logSucc : function() {
            alert("success");
        }, 
        logFail : function() {
            alert("fail");
        },
        log : function(username, password) {
            var rtn = {
                errorType : "-1",
                check : true
            }
            
            // success
            if (true) {
                return rtn;
            }
            // failure
            else {
                return rtn;
            }
        },
        errorMsg: function(errorType) {
            // or use custom errorType returned in usernameCheck(), passwdCheck(), loginHandler()
            return ErrorHandler.getErrorText(ErrorConst.Code.login, errorType);
        },
        renderMsg: function(comJqSelector, errorTxt) {
            top.layer.msg(errorTxt);
        }
    };

    load('.log-div', setting);
}

/**
 * setting
 * {
 *     logCheck : function(username, password) { ... },
 *     logSucc : function() {}
 *     logFail : function() {}
 * }
 */
function load(comJqSelector, settings) {
    // bind event
    bindEvent(comJqSelector, settings);
}

function bindEvent(comJqSelector, settings) {
    // submit event bind 
    $("" + comJqSelector + " form").submit(function (e) {
        var username = $('' + comJqSelector + ' form .log-username').val();
        var passwd = $('' + comJqSelector + ' form .log-password').val();
        // var checkResult = loginCheck_Form1(settings, username, passwd);
        var checkResult = settings.logCheck(username, passwd);

        // when log check success
        if (checkResult.check) {
            // then login
            var loadingLayer = LayerUtil.startLoading();
            var loginResult = settings.log(username, passwd);
            LayerUtil.endLoading(loadingLayer);

            if (loginResult.check) {
                settings.logSucc();
            }
            else {
                settings.logFail();
                settings.renderMsg(comJqSelector, settings.errorMsg(loginResult.errorType));
            }

            return loginResult.check;
        }
        // log check fail 
        else {
            settings.renderMsg(comJqSelector, settings.errorMsg(checkResult.errorType));
            return false;
        }
    });
}