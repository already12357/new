// Form
let FormUtil = {
    usernameCheck_notEmpty : function(userName) {
        return (null != userName && userName.length > 0);
    },
    passwdCheck_lengthLimit : function(passWd, minLength) {
        return (null != userName && passWd.length >= minLength);
    },
    passwdCheck_illegalCharacter : function(passWd, illegalCharacters) {
        for (let i = 0; i < illegalCharacters.length; i++) {
            if (passWd.indexOf(illegalCharacters[i]) != -1) {
                return false;
            }
        }

        return true;
    },
    passwdCheck_contentTypeLimit : function(passWd) {
        
    }
};

// Common Util
let CommonUtil = {
    // get requestparam from url by his name
    getUrlParams : function(param) {
        let paramsArr = window.location.search.split('&');
        paramsArr[0] = paramsArr[0].substring(1);

        for (let i = 0; i < paramsArr.length; i++) {
            let paramName = paramsArr[i].split('=')[0];
            
            if (paramName == param) {
                return decodeURIComponent(paramsArr[i].split('=')[1]);
            }
        }

        return "";
    },
    // create loading view when process certain function
    processLoading : function(handler) {
        top.layer.closeAll();
        // loading layer page
        let loadMask = layer.load(2, {
            shade: [0.8, '#fff']
        });

        handler();

        // close loading layer page
        layer.close(loadMask);
    },
    // confirm and loading
    confirmAndLoading : function(succHandler, failHandler) {

    }
}