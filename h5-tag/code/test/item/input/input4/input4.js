window.onload = function () {
    load_Input4('.input-style-4');
}

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