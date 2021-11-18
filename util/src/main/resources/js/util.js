/**
 * 实现帮助类的 JS
 */
$.extend(Util, {
    getUrlParams: function(prop) {
        var params = {},
            query = location.search.substring(1),
            arr = query.split('&'),
            rt;

        $.each(arr, function(i, item) {
            var tmp = item.split('='),
                key = tmp[0],
                val = tmp[1];

            if (typeof params[key] == 'undefined') {
                params[key] = val;
            } else if (typeof params[key] == 'string') {
                params[key] = [params[key], val];
            } else {
                params[key].push(val);
            }
        });
        rt = prop ? params[prop] : params;
        return rt;
    }
})