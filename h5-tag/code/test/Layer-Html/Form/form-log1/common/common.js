// Form
let FormUtil = {
    // new method to check input
    notEmpty : function(text) {
        return (null != text && text.length > 0);
    },
    txtLongerThan: function(text, length) {
        return (null != text && text.length >= length);
    },
    containedCharacter: function(text, characters) {
        for (var i = 0; i < characters.length; i++) {
            if (text.indexOf(characters[i]) != -1) {
                return false;
            }
        }

        return true;
    }
};

// Layui Layer Util
let LayerUtil = {
    startLoading: function() {
        top.layer.closeAll();
        let loadMask = layer.load(2, {
            shade: [0.8, '#fff']
        });

        return loadMask;
    },
    endLoading: function(layer) {
        layer.close(layer);
    },
    // create loading view when process certain function
    processing : function(handler) {
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
    confirmAndLoading : function(succHandler, failHandler, msg) {

    }
}

// Sweet Alert JS Util
let SweetAlertUtil = {
    // Alert Layer By SweetAlert
    msg : function(title, text, type) {
        swal({
            title: title,
            text: text,
            type: type
        });
    }

    // Submit Layer by SweetAlert

}

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
    // get context path
    getContextPath: function() {
        let pathName = document.location.pathname;
        let endIndex = pathName.substr(1).indexOf("/");
        let contextPath = pathName.substr(0, endIndex);
        return contextPath;
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
    confirmAndLoading : function(succHandler, failHandler, msg) {

    }
}