'use strict';

$(function () {
    function init() {
        // 配置导入地址
        let cssHref_arr = [ "./h5-tag/css/item/item.css" ];
        let scriptSrc_arr = [ "./h5-tag/js/item/item.js" ];

        let css_item = document.createElement("link");
        let script_item = document.createElement("script");

        // 遍历 js 和 css 路径, 将其拼接到对应的标签后
        for (let i = 0; i < scriptSrc_arr.length; i++) {
            let appendedScript = document.createElement("script");
            appendedScript.type = "text/javascript";
            appendedScript.src = scriptSrc_arr[i];
            $("html").append(appendedScript);
        }

        for (let j = 0; j < cssHref_arr.length; j++) {
            let appendedCss = document.createElement("link");
            appendedCss.href = cssHref_arr[j];
            appendedCss.rel = "stylesheet";
            $("head").append(appendedCss);
        }
    }

    init();
});