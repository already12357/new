window.onload = function() {
    load_Icon4(".icon-style-4", 5, 0.25);
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