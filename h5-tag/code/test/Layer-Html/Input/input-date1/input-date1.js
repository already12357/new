window.onload = function() {
    load_Input_Date1('.div-date');
}

/**
 * Input-Date-1
 */
 function load_Input_Date1(comJqSelector) {
    init_Input1(comJqSelector);
}
function init_Input1(comJqSelector) {
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