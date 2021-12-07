$(function () {
    function init() {
        var css_locations = [];
        var script_locations = [];
        var css_item = document.createElement("link");
        var script_item = document.createElement("script");

        script_item.type = "text/javascript";
        script_item.src = "./item.js";

        $("body").append(script_item);

    }

    init();
})