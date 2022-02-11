window.onload = function() {
    var mockData = {
        "contentList" : [
            "111111111111111111", 
            "211111111111111", 
            "344444444", 
            "4444444444444", 
            "5",
            "64444444444",
            "7444444444",
            "4444444444448"
        ],
        "total" : 7,
    };

    load_Button6(mockData, ".button-div-6", "#button-tmp6");
}

/********************************
 * Button 6
 */
 function load_Button6(renderData, comJqSelector, tmpJqSelector) {
    // init button
    init_Button6(comJqSelector);

    // render template data
    render_Button6(renderData, comJqSelector, tmpJqSelector);

    // bind event
    bindEvent_Button6();

    // load scroll bar
    listScroll_Button6();
}
function bindEvent_Button6() {
    $(".button-style-6 .button-div").on("mouseover", mouseover_Button6);
    $(".button-style-6 .button-div").on("mouseleave", mouseleave_Button6);
    $(".button-style-6 ul").on("mouseover", mouseover_Button6);
    $(".button-style-6 ul").on("mouseleave", mouseleave_Button6);
}
function mouseover_Button6(e) {
    $(".button-div img").css({'transform' : 'rotate(180deg)'});
    $(".button-style-6 ul").css("display", "block");
}
function mouseleave_Button6(e) {
    $(".button-div img").css({'transform' : 'rotate(90deg)'});
    $(".button-style-6 ul").css("display", "none");
}
function listScroll_Button6() {
    let itemCount = $(".button-style-6 ul li").length;
    if (itemCount > 4) {
        $(".button-style-6 ul").niceScroll({
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
            autohidemode:true,
        });
    }
}
function render_Button6(jsondata, comJqSelector, tmpJqSelector) {
    let templateContent = doT.template($("" + tmpJqSelector).html());
    $("" + comJqSelector).addClass("button-style-6");
    $("" + comJqSelector).addClass("not-allow-select");
    $("" + comJqSelector).empty().html(templateContent(jsondata));
}