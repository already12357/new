'use strict';

// contextPath 
let context_Path = "";

/**
 * init method
 */
function init() {
    // // jquery
    // document.getElementsByTagName("head")[0].appendChild(scriptDom(parentPath + "/ref/jQuery/jquery-1.12.min.js", "text/javascript"));

    // // bootstrap3
    // document.getElementsByTagName("head")[0].appendChild(linkDom(parentPath + "/ref/Bootstrap/bootstrap-3.4.1-dist/css/bootstrap.css", "stylesheet"));
    // document.getElementsByTagName("head")[0].appendChild(scriptDom(parentPath + "/ref/Bootstrap/bootstrap-3.4.1-dist/js/bootstrap.js", "text/javascript"));

    // // swiper3 + animsition
    // document.getElementsByTagName("head")[0].appendChild(scriptDom(parentPath + "/ref/Swiper/swiper-3.4.2/dist/js/swiper.min.js", "text/javascript"));
    // document.getElementsByTagName("head")[0].appendChild(linkDom(parentPath + "/ref/Swiper/swiper-3.4.2/dist/css/swiper.min.css", "stylesheet"))
    // document.getElementsByTagName("head")[0].appendChild(scriptDom(parentPath + "/ref/Animsition/animsition.min.js", "text/javascript"));
    // document.getElementsByTagName("head")[0].appendChild(linkDom(parentPath + "/ref/Animsition/animsition.min.css", "stylesheet"));

    // // js template
    // document.getElementsByTagName("head")[0].appendChild(scriptDom(parentPath + "/ref/JSTemplate/doT/doT.js", "text/javascript"));
    // document.getElementsByTagName("head")[0].appendChild(scriptDom(parentPath + "/ref/JSTemplate/mustache/mustache.js", "text/javascript"));

    // // niceScroll
    // document.getElementsByTagName("head")[0].appendChild(scriptDom(parentPath + "/ref/NiceScroll/jquery.nicescroll-3.5.1.js", "text/javascript"));

    // // Layui
    // document.getElementsByTagName("head")[0].appendChild(scriptDom(parentPath + "/ref/Layui/layui-v2.6.8/layui/layui.js", "text/javascript"));
    // document.getElementsByTagName("head")[0].appendChild(linkDom(parentPath + "/ref/Layui/layui-v2.6.8/layui/css/layui.css", "stylesheet"));
}


/**
 * set the contextPath value
 * @param {String} contextPath 
 */
function setContextPath(contextPath) {
    context_Path = contextPath;
}

/**
 * add <link> to the <head> tag
 * @param {String} href 
 * @param {String} rel 
 */
function addLinkTag(href, rel) {
    document.getElementsByTagName("head")[0].appendChild(linkDom(href, rel));
}

/**
 * add <script> after the <body> tag
 * @param {String} src 
 * @param {String} type 
 */
function addScriptTag(src, type) {
    document.getElementsByTagName("body")[0].appendChild(scriptDom(src, type));
}


/**
 * <link> generator with href and rel
 * @param {String} href 
 * @param {String} rel 
 */
function linkDom(href, rel) {
    let cssTag = document.createElement("link");
    if (context_Path == "") {
        cssTag.href = href;
    }
    else {
        cssTag.href = context_Path + href;
    }
    cssTag.rel = rel;
    return cssTag;
}

/**
 * <script> generator with src and type
 * @param {String} src 
 * @param {String} type 
 */
function scriptDom(src, type) {
    let scriptTag = document.createElement("script");
    if (context_Path == "") {
        scriptTag.src = src;
    }
    else {
        scriptTag.src = context_Path + src;
    }
    scriptTag.type = type;
    return scriptTag;
}