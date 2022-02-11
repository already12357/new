window.onload = function() {
    load_Icon2('0', './img/choose1-chosen.png', './img/choose1.png');
}


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