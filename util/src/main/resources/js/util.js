let Extended;
/**
 * 实现帮助类的 JS
 */
$.extend(Extended, {
    /**
     * 获取 URL 后的参数对
     * @param prop 需要获取的参数名称
     */
    getParamsFromURL: function(prop) {
        // 获取 URL 的参数对后缀, 对参数进行拆分
        var paramUrl = location.search;
        var params = paramUrl.split('&');

        $.each(params, function (item, function() {

        }))

        // location.search
        // split()
        // $.each(arr, func(){...})
        //
    }
})