window.onload = function() {
    /* load the component, or onload event */
    MOCK_load_Icon2();
    MOCK_load_Icon4();
    MOCK_load_Button6();

    $("#back-btn").click(function (e) {
        window.location.href = "../index.html";
    });
}




// Component
/********************************
 * Icon 2
 */
function load_Icon2(select, active_img, inactive_img) {
    // init select value
    select_Icon(select, active_img, inactive_img);
    // bind click event
    $(".icon-style-2").on("click", [active_img, inactive_img], click_Icon2);
}
function click_Icon2(img) {
    if ($(".icon-style-2").hasClass("selected")) {
        $(".icon-style-2 img").attr("src", img.data[1]);
        $(".icon2-value").attr("value", "0");
        $(".icon-style-2").removeClass("selected");
    }
    else {
        $(".icon-style-2 img").attr("src", img.data[0]);
        $(".icon2-value").attr("value", "1");
        $(".icon-style-2").addClass("selected");
    }
}
function select_Icon(select, active_img, inactive_img) {
    if (select == '1') {
        $(".icon-style-2 img").attr("src", active_img);
        $(".icon2-value").attr("value", "1");
        $(".icon-style-2").addClass("selected");
    }
    else {
        $(".icon-style-2 img").attr("src", inactive_img);
        $(".icon2-value").attr("value", "0");
    }
}



/********************************
 * Icon 4
 */
function load_Icon4(comJqSelector, dotCount, loadInterval) {
    // add loading dot
    dotAdd_Icon4(dotCount, comJqSelector);
    // put on style
    onStyle_Icon4(dotCount, loadInterval, comJqSelector);
}
function dotAdd_Icon4(dotCount, comJqSelector) {
    let addDotHtml = "";
    for (let i = 0; i < dotCount; i++) {
        addDotHtml += "<span></span>";
    }
    // $(".icon-style-4").empty().html(addDotHtml);
    $("" + comJqSelector).append(addDotHtml);
}
function onStyle_Icon4(dotCount, loadInterval, comJqSelector) {
    $(comJqSelector + " span").css("animation", "icon4-span-load " + ((dotCount + 1) * loadInterval) + "s ease infinite");
    for (let i = 1; i <= dotCount; i++) {
        $(comJqSelector + " span:nth-child(" + i + ")").css("animation-delay", (loadInterval * i) + "s");
        $(comJqSelector + " span:nth-child(" + i + ")").css("-webkit-animation-delay", (loadInterval * i) + "s");
        $(comJqSelector + " span:nth-child(" + i + ")").css("-moz-animation-delay", (loadInterval * i) + "s");
        $(comJqSelector + " span:nth-child(" + i + ")").css("-o-animation-delay", (loadInterval * i) + "s");
    }
}


/********************************
 * Button 6
 */
function load_Button6(renderData, comJqSelector, tmpJqSelector) {

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
function mouseover_Button6() {
    $(".button-div img").css({'transform' : 'rotate(180deg)'});
    $(".button-style-6 ul").css("display", "block");
}
function mouseleave_Button6() {
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

function listDisplay_Button6() {

}
function listHidden_Button6() {

}
function titleRotate_Button6() {

}
function titleNormal_Button6() {

}