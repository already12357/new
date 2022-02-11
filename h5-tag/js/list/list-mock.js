/* Mock List1 */
function MOCK_load_List1() {
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



/* Mock List2 */
function MOCK_render_List2() {
    var mockData = {
        "contentList" : [
            "1",
            "2", 
            "3", 
            "4"
        ],
        "total" : 4
    };

    render_List2(mockData, ".list-div-2", "#list-tmp2");
}