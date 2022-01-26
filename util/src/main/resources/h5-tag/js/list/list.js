window.onload = function() {
    MOCK_load_List1();
    MOCK_render_List2();

    $("#back-btn").click(function (e) {
        window.location.href = "../index.html";
    });
}




// Component
/********************************
 * List 1 
 */
function load_List1(renderData, comJqSelector, tmpJqSelector) {
    $("" + comJqSelector).addClass("list-style-1");
    $("" + comJqSelector).addClass("not-allow-select");

    // render list content
    render_List1(renderData, comJqSelector, tmpJqSelector);
}

function render_List1(renderData, comJqSelector, tmpJqSelector) {
    var templateContent = doT.template($("" + tmpJqSelector).html());
    /* Change Render Id Here */
    $("" + comJqSelector).empty().html(templateContent(renderData));
}



/********************************
 * List 2
 */
function render_List2(renderData, comJqSelector, tmpJqSelector) {
    // var templateContent = doT.template($("#list-tmp2").html());
    var templateContent = doT.template($("" + tmpJqSelector).html());

    /* Change Render Id Here */
    // $(".list-div-2"/*RenderId*/).empty().html(templateContent(renderData));
    $(comJqSelector).empty().html(templateContent(renderData));

    $(".list-style-2").niceScroll({
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
        /* nicescroll可以管理水平滚动 */
        horizrailenabled: true
    });

    /* Render Div Length Here */
    var itemWidth = 150;
    var marginWidth = 10;
    var listFrameWidth = renderData.total * itemWidth + renderData.total * marginWidth;
    $(comJqSelector + " .list-frame").css("width", listFrameWidth + "px");
}