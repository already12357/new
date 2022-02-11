window.onload = function() {
    let mockData = {
        dValue : "default Valuedefault Valuedefault Valuedefault Valuedefault Value",
        contentList : [
            "11111111111111111111",
            "211111111111111111", 
            "3111111111111111111111", 
            "中文测试", 
            "5ffffffdasfdsafdasfdasfasf",
            "6f22fdsav#@##@#@#@#dbafad",
            "7312323432",
            "811",
            "中文字体测试 "
        ],
        totalCount : 9
    };

    load_Input5(mockData, ".input-div-5", "#input-tmp5", true);
}


/**
 * 
 * @param {JSON} renderData data for render
 * @param {String} comJqSelector render html element's jq selector string
 * @param {String} tmpJqSelector render template's jq selector string
 * @param {Boolean} slideAnim open the animation
 */
function load_Input5(renderData, comJqSelector, tmpJqSelector, slideAnim) {
    // init
    init_Input5(comJqSelector);
    // render data
    render_Input5(renderData, comJqSelector, tmpJqSelector);
    // bind events
    bindEvents_Input5(comJqSelector, slideAnim);
    // list scroll
    listScroll_Input5(comJqSelector);
}
function init_Input5(comJqSelector) {
    $("" + comJqSelector).addClass("input-style-5");
    $("" + comJqSelector).addClass("not-allow-select");
}
function render_Input5(renderData, comJqSelector, tmpJqSelector) {
    let templateContent = doT.template($("" + tmpJqSelector).html());
    $("" + comJqSelector).empty().html(templateContent(renderData));
}
function bindEvents_Input5(comJqSelector, slideAnim) {
    $(comJqSelector + " .select-button").on("focus", function() {
        displayList_Input5(comJqSelector, slideAnim);
    });
    $(comJqSelector + " .select-button").on("blur", function() {
        hideList_Input5(comJqSelector, slideAnim);
    });
    $(comJqSelector + " .select-list li").on("mousedown", function(e) {
        renderSelect_Input5($(this), comJqSelector);
        hideList_Input5(comJqSelector, false);
    });
}
function listScroll_Input5(comJqSelector) {
    let itemCount = $(comJqSelector + " .select-list li").length;
    if (itemCount > 5) {
        // $(comJqSelector + " .select-list-div").niceScroll({
        $(comJqSelector + " .select-list-div").niceScroll({
            /* 是否是触摸式滚动效果 */
            touchbehavior:true,
            /* 激活拖拽滚动 */
            touchbehavior: false, 
            /* 滚动条的颜色值 */
            cursorcolor:"#333",
            /* 滚动条的透明度值 */
            cursoropacitymax:0.5,   
            /* 滚动条的宽度值 */
            cursorwidth: 5,
            /* 滚动条是否是自动隐藏，默认值为 true */        
            autohidemode:"scroll",
        });
    }
}
function displayList_Input5(comJqSelector, slideAnim) {
    if (slideAnim) {
        $(comJqSelector + " .select-list-div").stop(true, false);
        $(comJqSelector + " .select-button").css("border-bottom-left-radius", "0px");
        $(comJqSelector + " .select-button").css("border-bottom-right-radius", "0px");
        $(comJqSelector + " .select-list-div").slideDown(300);
    }
    else {
        $(comJqSelector + " .select-list-div").stop(true, true);
        $(comJqSelector + " .select-list-div").css("display", "block");
    }
    
}
function hideList_Input5(comJqSelector, slideAnim) {
    if (slideAnim) {
        $(comJqSelector + " .select-list-div").stop(true, false);
        $(comJqSelector + " .select-list-div").slideUp(300, function() {
            $(comJqSelector + " .select-button").css("border-radius", "3px");
        });
    }
    else {
        $(comJqSelector + " .select-list-div").stop(true, true);
        $(comJqSelector + " .select-list-div").css("display", "none");
    }
    
}
function renderSelect_Input5(clickJqObj, comJqSelector) {
    let selectVal = clickJqObj.attr("data-item");
    $(comJqSelector + " .select-button").attr("value", selectVal);
    $(comJqSelector + " .select-value").attr("value", selectVal);
}