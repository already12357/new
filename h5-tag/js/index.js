// $(function() {
// after loading, bind all the function
window.onload = function() {
    /**
     * bind click events
     */
    $("#item-btn").click(function (e) { 
        window.location.href = "html/item.html";
    });
    $("#layout-btn").click(function (e) { 
        window.location.href = "html/layout.html";
    });
    $("#list-btn").click(function (e) { 
        window.location.href = "html/list.html";
    });
    $("#navigator-btn").click(function (e) { 
        window.location.href = "html/navigator.html";
    });
    $("#panel-btn").click(function (e) { 
        window.location.href = "html/panel.html";
    });
    $("#table-btn").click(function (e) { 
        window.location.href = "html/table.html";
    });
    $("#download-btn").click(function (e) { 
        window.location.href = "html/download.html";
    });
    $("#jsapi-btn").click(function(e) {
        window.location.href = "html/jsapi.html"
    });


    // <button id="layerHtml-btn">Layer-Style</button>
    // <button id="bootstrap3Html-btn">Bootstrap3-Style</button>
    // <button id="NativeHtml-btn">Native-Style</button>


    $("#layerHtml-btn").click(function(e) {
        window.location.href = "html/Layer-Html.html";
    });
    $("#bootstrap3Html-btn").click(function(e) {
        window.location.href = "html/Bootstrap3-Html.html";
    });
    $("#NativeHtml-btn").click(function(e) {
        window.location.href = "html/Native-Html.html";
    });

    $("#download-btn-new").click(function(e) {
        window.location.href = "html/Download-New.html";
    });

    // call .exe to package all the file in ./code/package/~
    $("#download-update-btn").click(function (e) { 
        // user layer confirm
        top.layer.confirm(
            'Are you sure to update download package?', 
            {
                title : 'Confirm',
                btn : ['Right now', 'Not this time']
            },
            function() {
                top.layer.closeAll();
                // loading layer page
                let loadMask = layer.load(2, {
                    shade: [0.8, '#fff']
                });

                
                /* call shell to package file in test directory */
                /* Integer 7-zip, using js to call it */
                // new ActiveXObject("")


                // close loading layer page
                layer.close(loadMask);
                // open msg layer
                layer.msg('Finished');
            },
            function() { top.layer.closeAll(); }
        );
    });
}
// });