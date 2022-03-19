// path constant
let PathConst = {
    // jquery
    jQuery : "/ref/jQuery/jquery-1.12.min.js",
    // bootstrap
    bootstrap3 : {
        css : "/ref/Bootstrap/bootstrap-3.4.1-dist/css/bootstrap.css",
        js : "/ref/Bootstrap/bootstrap-3.4.1-dist/js/bootstrap.js"
    },
    // bootstrap3-plguin
    bootstrap3Plugin : {
        sweetalert: {
            js : "/ref/SweetAlert/sweetalert.js",
            css : "/ref/SweetAlert/sweetalert.css",
            minjs : "/ref/SweetAlert/sweetalert.min.js"
        },
        colorpicker : ""
    },
    // Swiper 
    Swiper : {
        js : "/ref/Swiper/swiper-3.4.2/dist/js/swiper.min.js",
        css : "/ref/Swiper/swiper-3.4.2/dist/css/swiper.min.css"
    },
    // Animsition
    Animsition : {
        minijs : "/ref/Animsition/animsition.min.js",
        minicss : "/ref/Animsition/animsition.min.css"
    },
    // js template
    doT : "/ref/JSTemplate/doT/doT.js",
    // Mustache
    Mustache : "/ref/JSTemplate/mustache/mustache.js",
    // jquery plugin 
    jqPlugin : {
        nicescroll : "/ref/NiceScroll/jquery.nicescroll-3.5.1.js",
        
    },
    // layui 
    Layui : {
        css : "/ref/Layui/layui-v2.6.8/layui/css/layui.css",
        js : "/ref/Layui/layui-v2.6.8/layui/layui.js"
    }
}

// error constant define
let ErrorConst = {
    Login : [
        {
            errorType : "0",
            text : "密码长度需要大于六位"
        },
        {
            errorType : "1",
            text : "用户名不能为空"
        },
        {
            errorType : "2",
            text : "包含非法字符"
        }
    ],
    // error by different func
    System : [

    ],
    Runtime : [

    ],
    // code of error type ( Login, System, Runtime )
    Code : {
        login : 0,
        system : 1,
        runtime : 2
    },
    // Handler : {
    //     getErrorText : function(errorType, errorCode) {
    //         switch (errorCode) {
    //             case this.Code.login:

    //                 break;
    //         }
    //     }
    // }
};

let ErrorHandler = {
    getErrorText : function(code, errorType) {
        switch (code) {
            case ErrorConst.Code.login: 
                for (var i = 0; i < ErrorConst.Login.length; i++) {
                    if (ErrorConst.Login[i].errorType == errorType) {
                        return ErrorConst.Login[i].text;
                    }
                }
            break;

            case ErrorConst.Code.system: 
                for (var i = 0; i < ErrorConst.System.length; i++) {
                    if (ErrorConst.System[i].errorType == errorType) {
                        return ErrorConst.System[i].text;
                    }
                }
            break;

            case ErrorConst.Code.runtime: 
                for (var i = 0; i < ErrorConst.Runtime.length; i++) {
                    if (ErrorConst.Runtime[i].errorType == errorType) {
                        return ErrorConst.Runtime[i].text;
                    }
                }
            break;
        }

        return "";
    }
};

