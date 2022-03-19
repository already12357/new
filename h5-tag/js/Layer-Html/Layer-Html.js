window.onload = function() {
    $("#back-btn").click(function (e) {
        window.location.href = "../index.html";
    });

    let settings_Form_Log1 = {
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
            $('' + comJqSelector + " form .log-error-msg").css("display", "inline-block")
            $('' + comJqSelector + " form .log-error-msg").html(errorTxt);
        }
    };

    let settings_Form_Log2 = {
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

    // form-log-*
    load_Form_Log('.form-log-style-1', settings_Form_Log1);
    load_Form_Log('.form-log-style-2', settings_Form_Log2);

    // input-date-*
    load_Input_Date1('.input-date-style-1');

    /** test Function */
    $("#test-layer").click(function (e) { 
        top.layer.msg("hello", 3);
    });
}


/********************************
 * Form-Log
 */
 function load_Form_Log(comJqSelector, settings) {
    // bind event
    bindEvent_Form_Log(comJqSelector, settings);
}

function bindEvent_Form_Log(comJqSelector, settings) {
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
        // fail 
        else {
            settings.renderMsg(comJqSelector, settings.errorMsg(checkResult.errorType));
            return false;
        }
    });
}


/**
 * Input-Date-1
 */
function load_Input_Date1(comJqSelector) {
    init_Input1(comJqSelector);
}
function init_Input1(comJqSelector) {
    layui.use(['laydate'], function() {
        var dateLayer = layui.laydate;
        dateLayer.render({
            elem : comJqSelector + " #input-start-date",
            format : "yyyy/MM/dd",
            isInitValue: false,
            range: true
        });
    });
}