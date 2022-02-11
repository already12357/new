window.onload = function () {
    var mockData = {
        "title":"This is the Title I'm Looking Forward to given to the top",
        "contentList":[
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1",
            "Content1"
        ],
        "isHotImgShow":[
            true,
            true,
            false,
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            true,
        ],
        "total" : 12
    };

    load_List1(mockData, ".list-div-1", "#list-tmp1");
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