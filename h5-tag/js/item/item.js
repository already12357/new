window.onload = function() {
    /* load the component, or onload event */
    MOCK_load_Icon2();
    MOCK_load_Icon4();
    MOCK_load_Button6();
    MOCK_load_Input4();
    MOCK_load_Input5();

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



/********************************
 * Input 4
 */
/**
 * 
 * @param {String} comJqSelector component div's jq selector string
 */
 function load_Input4(comJqSelector) {
    init_Input4(comJqSelector);
}
function init_Input4(comJqSelector) {
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


/********************************
 * Input 5
 */
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